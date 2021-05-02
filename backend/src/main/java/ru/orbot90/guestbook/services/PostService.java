package ru.orbot90.guestbook.services;

import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.dao.PostDao;
import ru.orbot90.guestbook.entities.PostEntity;
import ru.orbot90.guestbook.model.Post;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service
public class PostService {

    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void createPost(Post post) {
        PostEntity postEntity = new PostEntity();

        postEntity.setData(post.getData());
        // mocking userId before auth is implemented
        postEntity.setUserId(42L);

        this.postDao.save(postEntity);
    }
}
