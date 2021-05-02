package ru.orbot90.guestbook.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.orbot90.guestbook.exception.DataNotFoundException;
import ru.orbot90.guestbook.model.ImageUploadResponse;
import ru.orbot90.guestbook.services.ImageService;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Controller
@RequestMapping(ImageController.IMAGE_CONTEXT)
@CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept")
public class ImageController {

    static final String IMAGE_CONTEXT = "/image";

    private final String imageHost;
    private final ImageService imageService;

    public ImageController(@Value("${guestbook.images.host}") String imageHost,
                           ImageService imageService) {
        this.imageService = imageService;
        this.imageHost = imageHost;
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    public ImageUploadResponse uploadImage(@RequestParam("upload") MultipartFile file) throws IOException {
        String savedName = imageService.saveImage(file.getBytes());

        return new ImageUploadResponse(imageHost + IMAGE_CONTEXT + "/" + savedName);
    }

    @GetMapping
    @RequestMapping(path = {"/{name}"}, produces = "image/webp")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable("name") @NotEmpty String name) {
        try {
            byte[] image = this.imageService.getImageByName(name);
            return new ResponseEntity<>(image, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
