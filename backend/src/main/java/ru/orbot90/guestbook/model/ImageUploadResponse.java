package ru.orbot90.guestbook.model;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class ImageUploadResponse {
    private final String url;
    private String error;

    public ImageUploadResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
