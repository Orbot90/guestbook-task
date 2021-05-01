package ru.orbot90.guestbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orbot90.guestbook.entities.ImageEntity;

import java.util.Optional;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Repository
public interface ImageDao extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findByName(String name);

}
