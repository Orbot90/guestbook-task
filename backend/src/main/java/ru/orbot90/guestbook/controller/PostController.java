package ru.orbot90.guestbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orbot90.guestbook.model.Post;
import ru.orbot90.guestbook.services.PostService;

import javax.validation.Valid;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Controller
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody @Valid Post post) {
        this.postService.createPost(post);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
