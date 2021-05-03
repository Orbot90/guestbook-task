package ru.orbot90.guestbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.orbot90.guestbook.exception.DataNotFoundException;
import ru.orbot90.guestbook.model.Post;
import ru.orbot90.guestbook.model.PostApproval;
import ru.orbot90.guestbook.services.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Controller
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Post> addPost(@RequestBody @Valid Post post, TimeZone timeZone,
                                        Authentication authentication) {

        post.setUserName(authentication.getName());
        Post newPost = this.postService.createPost(post, timeZone);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Post> getAllPosts(TimeZone timeZone) {
        return this.postService.getAllPosts(timeZone, PostApproval.ALL);
    }

    @GetMapping("/approved")
    @ResponseBody
    public List<Post> getAllApprovedPosts(TimeZone timeZone) {
        return this.postService.getAllPosts(timeZone, PostApproval.APPROVED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Post> editPost(@RequestBody Post post, @PathVariable("id") Long id,
                                         TimeZone timeZone) {
        // TODO custom permission evaluator - is admin or is author of this post
        try {
            return new ResponseEntity<>(this.postService.updatePost(id, post, timeZone), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        // TODO custom permission evaluator - is admin or is author of this post
        try {
            this.postService.deletePost(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // TODO: add approve post for administrator
}
