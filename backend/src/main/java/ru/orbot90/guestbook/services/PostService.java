package ru.orbot90.guestbook.services;

import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.dao.PostDao;
import ru.orbot90.guestbook.entities.PostEntity;
import ru.orbot90.guestbook.exception.DataNotFoundException;
import ru.orbot90.guestbook.model.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service
public class PostService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.LONG);

    private final PostDao postDao;

    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public Post createPost(Post post) {
        // TODO check if is authenticated
        PostEntity postEntity = new PostEntity();

        postEntity.setData(post.getData());
        // mocking userId before auth is implemented
        postEntity.setUserId(42L);
        postEntity.setDate(LocalDateTime.now());
        this.postDao.save(postEntity);
        return this.converToDTO(postEntity);
    }

    public List<Post> getAllPosts() {
        return this.postDao.findAll().stream()
                .sorted(Comparator.comparing(PostEntity::getDate))
                .map(this::converToDTO)
                .collect(Collectors.toList());
    }

    private Post converToDTO(PostEntity entity) {
        Post dto = new Post();
        dto.setData(entity.getData());
        dto.setDate(DATE_FORMAT.format(entity.getDate()));
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setEditedBy(entity.getEditedBy());

        Optional.ofNullable(entity.getEditedDate())
                .map(DATE_FORMAT::format)
                .ifPresent(dto::setEditedDate);
        return dto;
    }

    public Post updatePost(Long id, Post post) {
        // TODO get user id from auth, check if user is allowed to edit
        // mocking before auth implemented
        Long editorId = 42L;

        PostEntity existingPost = this.postDao.findById(id)
                .orElseThrow(DataNotFoundException::new);
        existingPost.setData(post.getData());
        existingPost.setEditedBy(editorId);
        existingPost.setEditedDate(LocalDateTime.now());
        this.postDao.save(existingPost);
        return this.converToDTO(existingPost);
    }

    public void deletePost(Long id) {
        // TODO: check if user is allowed to delete post
        PostEntity existingPost = this.postDao.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.postDao.delete(existingPost);
    }
}
