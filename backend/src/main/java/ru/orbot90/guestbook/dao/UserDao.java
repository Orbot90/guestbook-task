package ru.orbot90.guestbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orbot90.guestbook.entities.UserEntity;

import java.util.Optional;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

}
