package ru.orbot90.guestbook.services;

import ru.orbot90.guestbook.model.SignUpRequest;
import ru.orbot90.guestbook.model.User;

import javax.transaction.Transactional;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public interface UserService {
    @Transactional
    User createUser(SignUpRequest user);

}
