package ru.orbot90.guestbook.services;

import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.dao.ImageDao;
import ru.orbot90.guestbook.entities.ImageEntity;
import ru.orbot90.guestbook.exception.GuestBookException;
import ru.orbot90.guestbook.exception.ImageNotFoundException;

/**
 * ImageService implementation that stores the images to a database
 *
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service
public class DatabaseImageServiceImpl implements ImageService {

    private final ImageDao imageDao;
    private final HashService hashService;

    public DatabaseImageServiceImpl(ImageDao imageDao, HashService hashService) {
        this.imageDao = imageDao;
        this.hashService = hashService;
    }

    @Override
    public String saveImage(byte[] image) {
        try {

            // mocking userId before auth is implemented
            Long userId = 42L;

            String hash = hashService.calculateHash(image);

            String imageName = userId + "_" + hash;
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImage(image);
            imageEntity.setName(imageName);

            imageDao.save(imageEntity);
            return imageName;
        } catch (Exception e) {
            throw new GuestBookException("Error while saving image", e);
        }
    }

    @Override
    public byte[] getImageByName(String name) {
        return this.imageDao.findByName(name).map(ImageEntity::getImage)
                .orElseThrow(ImageNotFoundException::new);
    }
}
