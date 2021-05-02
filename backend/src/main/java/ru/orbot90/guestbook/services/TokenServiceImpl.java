package ru.orbot90.guestbook.services;

import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.model.User;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {

        // TODO: implement
        return "token";
    }
}
