package ru.orbot90.guestbook.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.orbot90.guestbook.dao.UserDao;
import ru.orbot90.guestbook.entities.RoleEntity;
import ru.orbot90.guestbook.entities.UserEntity;
import ru.orbot90.guestbook.exception.UserExistsException;
import ru.orbot90.guestbook.model.SignUpRequest;
import ru.orbot90.guestbook.model.User;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldCreateUser() {
        String name = "Jotaro_Kujo";

        SignUpRequest request = new SignUpRequest();
        request.setUserName(name);
        request.setPassword("OraOraOra");

        when(passwordEncoder.encode("OraOraOra")).thenReturn("topsecretlyencoded");
        UserEntity entityToSave = new UserEntity();
        entityToSave.setUserName(name);
        entityToSave.setPassword("topsecretlyencoded");
        entityToSave.setRoles(Collections.singletonList(new RoleEntity("ROLE_USER")));
        when(userDao.save(eq(entityToSave))).thenReturn(entityToSave);

        User expectedUser = new User();
        expectedUser.setName(name);
        expectedUser.setRoles(Collections.singletonList("ROLE_USER"));
        User returnedUser = userService.createUser(request);

        assertEquals(expectedUser, returnedUser, "Returned user is different from expected");
    }

    @Test
    public void shouldThrowExceptionWhenUsernameExists() {
        assertThrows(UserExistsException.class, () -> {
            String name = "Jotaro_Kujo";

            SignUpRequest request = new SignUpRequest();
            request.setUserName(name);
            request.setPassword("OraOraOra");

            when(userDao.findByUserName(name)).thenReturn(Optional.of(new UserEntity()));
            userService.createUser(request);
        });
    }

}