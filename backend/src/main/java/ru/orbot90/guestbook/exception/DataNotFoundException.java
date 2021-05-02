package ru.orbot90.guestbook.exception;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
