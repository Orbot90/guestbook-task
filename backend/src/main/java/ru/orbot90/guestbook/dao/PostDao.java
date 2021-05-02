package ru.orbot90.guestbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orbot90.guestbook.entities.PostEntity;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Repository
public interface PostDao extends JpaRepository<PostEntity, Long> {
}
