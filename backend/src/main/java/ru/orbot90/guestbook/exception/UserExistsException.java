package ru.orbot90.guestbook.exception;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public class UserExistsException extends RuntimeException {
    public UserExistsException(String userName) {
        super("User " + userName + " already exists");
    }
}
