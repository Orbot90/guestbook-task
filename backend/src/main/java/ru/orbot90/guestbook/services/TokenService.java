package ru.orbot90.guestbook.services;

import ru.orbot90.guestbook.model.User;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public interface TokenService {

    String getToken(User user);
}
