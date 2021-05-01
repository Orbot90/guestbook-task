package ru.orbot90.guestbook.exception;

/**
 * Generic exception for the application
 *
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class GuestBookException extends RuntimeException {

    public GuestBookException(String message) {
        super(message);
    }

    public GuestBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
