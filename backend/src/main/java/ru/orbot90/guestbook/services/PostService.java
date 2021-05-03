package ru.orbot90.guestbook.services;

import org.springframework.stereotype.Service;
import ru.orbot90.guestbook.dao.PostDao;
import ru.orbot90.guestbook.dao.UserDao;
import ru.orbot90.guestbook.entities.PostEntity;
import ru.orbot90.guestbook.entities.UserEntity;
import ru.orbot90.guestbook.exception.DataNotFoundException;
import ru.orbot90.guestbook.model.Post;
import ru.orbot90.guestbook.model.PostApproval;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Service
public class PostService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.MEDIUM);

    private final PostDao postDao;
    private final UserDao userDao;

    public PostService(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public Post createPost(Post post, TimeZone timeZone) {
        PostEntity postEntity = new PostEntity();

        postEntity.setData(post.getData());
        postEntity.setDate(LocalDateTime.now());
        UserEntity user = userDao.findByUserName(post.getUserName()).orElseThrow(() ->
                new DataNotFoundException("User " + post.getUserName() + " does not exist"));
        postEntity.setUser(user);
        postEntity = this.postDao.save(postEntity);
        return this.converToDTO(postEntity, timeZone);
    }

    public List<Post> getAllPosts(TimeZone timeZone, PostApproval approval) {
        Stream<PostEntity> postsStream = this.postDao.findAll().stream();
        if (approval == PostApproval.APPROVED) {
            postsStream = postsStream.filter(PostEntity::isApproved);
        }
                return postsStream.sorted(Comparator.comparing(PostEntity::getDate))
                .map(post -> converToDTO(post, timeZone))
                .collect(Collectors.toList());
    }

    private Post converToDTO(PostEntity entity, TimeZone timeZone) {
        Post dto = new Post();
        dto.setData(entity.getData());
        dto.setDate(DATE_FORMAT.withZone(timeZone.toZoneId()).format(entity.getDate()));
        dto.setId(entity.getId());
        dto.setApproved(entity.isApproved());

        dto.setUserName(entity.getUser().getUserName());
        Optional.ofNullable(entity.getEditedBy())
                .map(UserEntity::getUserName)
                .ifPresent(dto::setEditedBy);

        Optional.ofNullable(entity.getEditedDate())
                .map(date -> DATE_FORMAT.withZone(timeZone.toZoneId()).format(date))
                .ifPresent(dto::setEditedDate);
        return dto;
    }

    public Post updatePost(Long id, Post post, TimeZone timeZone) {
        PostEntity existingPost = this.postDao.findById(id)
                .orElseThrow(DataNotFoundException::new);
        existingPost.setData(post.getData());
        UserEntity editor = userDao.findByUserName(post.getEditedBy())
                .orElseThrow(() -> new DataNotFoundException("User " +
                        post.getEditedBy() + " doesn't exist"));
        existingPost.setEditedBy(editor);
        existingPost.setEditedDate(LocalDateTime.now());
        this.postDao.save(existingPost);
        return this.converToDTO(existingPost, timeZone);
    }

    public void deletePost(Long id) {
        PostEntity existingPost = this.postDao.findById(id)
                .orElseThrow(DataNotFoundException::new);
        this.postDao.delete(existingPost);
    }
}
