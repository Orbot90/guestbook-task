package ru.orbot90.guestbook.services;

/**
 * ImageService implementation that stores images to a local folder
 *
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class LocalFileImageServiceImpl implements ImageService {
    @Override
    public String saveImage(byte[] image) {
        return null;
    }

    @Override
    public byte[] getImageByName(String name) {
        return new byte[0];
    }
}
