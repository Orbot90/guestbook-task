package ru.orbot90.guestbook.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.dao.UserDao;
import ru.orbot90.guestbook.entities.RoleEntity;
import ru.orbot90.guestbook.entities.UserEntity;
import ru.orbot90.guestbook.exception.DataNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service("guestbookUserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userDao.findByUserName(username)
                .orElseThrow(DataNotFoundException::new);
        List<GrantedAuthority> grantedAuthorities = userEntity.getRoles()
                .stream()
                .map(RoleEntity::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(userEntity.getUserName(), userEntity.getPassword(), grantedAuthorities);
    }
}
