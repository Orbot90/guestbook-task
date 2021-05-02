package ru.orbot90.guestbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.orbot90.guestbook.exception.DataNotFoundException;
import ru.orbot90.guestbook.model.Post;
import ru.orbot90.guestbook.services.PostService;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<Post> addPost(@RequestBody @Valid Post post) {

        Post newPost = this.postService.createPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public List<Post> getAllPosts() {
        return this.postService.getAllPosts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(@RequestBody Post post, @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.postService.updatePost(id, post), HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        try {
            this.postService.deletePost(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
