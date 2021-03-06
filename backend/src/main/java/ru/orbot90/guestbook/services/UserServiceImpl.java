package ru.orbot90.guestbook.services;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.dao.UserDao;
import ru.orbot90.guestbook.entities.RoleEntity;
import ru.orbot90.guestbook.entities.UserEntity;
import ru.orbot90.guestbook.exception.UserExistsException;
import ru.orbot90.guestbook.model.SignUpRequest;
import ru.orbot90.guestbook.model.User;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User createUser(SignUpRequest user) {

        userDao.findByUserName(user.getUserName())
                .ifPresent(userEntity -> {
                    throw new UserExistsException(user.getUserName());
                    // TODO: Think of a better way to check for user existing in order to inform user properly
                });
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(Collections.singletonList(new RoleEntity("ROLE_USER")));
        userEntity = userDao.save(userEntity);

        User newUser = new User();
        newUser.setName(userEntity.getUserName());
        newUser.setRoles(userEntity.getRoles()
                .stream()
                .map(RoleEntity::getRoleName)
                .collect(Collectors.toList()));

        return newUser;
    }



    @EventListener({ContextRefreshedEvent.class})
    @Transactional
    public void addUsers() {
        // TODO: remove when registration is implemented
        this.userDao.findByUserName("Dio_Brando").orElseGet(() -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("Dio_Brando");
            userEntity.setPassword(passwordEncoder.encode("zawarudo"));
            userEntity.setRoles(Arrays.asList(new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER")));
            return userDao.save(userEntity);
        });

        this.userDao.findByUserName("Jotaro_Kujo").orElseGet(() -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("Jotaro_Kujo");
            userEntity.setPassword(passwordEncoder.encode("oraoraora"));
            userEntity.setRoles(Collections.singletonList(new RoleEntity("ROLE_USER")));
            return userDao.save(userEntity);
        });

        this.userDao.findByUserName("Joseph_Joestar").orElseGet(() -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName("Joseph_Joestar");
            userEntity.setPassword(passwordEncoder.encode("ohmygod"));
            userEntity.setRoles(Collections.singletonList(new RoleEntity("ROLE_USER")));
            return userDao.save(userEntity);
        });
    }
}
