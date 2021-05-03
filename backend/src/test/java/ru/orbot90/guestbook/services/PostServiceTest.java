package ru.orbot90.guestbook.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.orbot90.guestbook.dao.PostDao;
import ru.orbot90.guestbook.dao.UserDao;
import ru.orbot90.guestbook.entities.UserEntity;
import ru.orbot90.guestbook.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.MEDIUM);

    @Mock
    private PostDao postDao;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private PostService postService;

    @Test
    public void shouldCreatePost() {
        String userName = "Dio_Brando";
        String postText = "Muda muda muda muda!";

        Post newPost = new Post();
        newPost.setUserName(userName);
        newPost.setData(postText);

        UserEntity user = new UserEntity();
        user.setUserName(userName);
        user.setId(1L);
        when(userDao.findByUserName(userName)).thenReturn(Optional.of(user));

        when(postDao.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        Post createdPost = postService.createPost(newPost, TimeZone.getTimeZone("CET"));
        assertEquals(0, LocalDateTime.from(DATE_FORMAT.withZone(ZoneId.systemDefault())
                .parse(createdPost.getDate()))
                .toLocalDate().compareTo(LocalDate.now()), "Date is wrong");
        assertEquals(userName, createdPost.getUserName(), "User name is wrong");
        assertEquals(postText, createdPost.getData(), "Post text is wrong");
    }

}