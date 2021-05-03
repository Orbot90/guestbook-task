package ru.orbot90.guestbook.services;

/**
 * Service for working with images
 *
 * @author Iurii Plevako orbot90@gmail.com
 **/
public interface ImageService {

    /**
     * Save image using the implemented mechanism
     *
     * @param image - the image to be saved
     * @param userName name of the user uploading the image
     * @return path to access the image as a string
     */
    String saveImage(byte[] image, String userName);

    /**
     * Get image from storage by its name
     *
     * @param name - name of the image to find
     * @return image as byte array
     */
    byte[] getImageByName(String name);

}
