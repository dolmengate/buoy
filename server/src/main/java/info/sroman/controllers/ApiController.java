package info.sroman.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.*;
import info.sroman.model.PostDTO;
import info.sroman.model.PostForm;
import info.sroman.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    PostRepository posts;

    @Autowired
    ContentRepository contents;

    @Autowired
    EditorRepository editors;

    @Autowired
    CommentRepository comments;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(path="/posts") // /posts/page/{pageId}
    @JsonView(PostDTO.MetadataOnlyView.class)
    public List<PostDTO> getAllPosts() {
        List<Post> allPosts = (List<Post>)posts.findAll();
        List<PostDTO> allPostDTOs = new LinkedList<>();
        for (Post post : allPosts) {
            Content c = contents.findOne(post.getContentId());
            Editor e;
            if (c.getType() == Type.CODE) {
                e = editors.findOne(c.getAttachmentId());
                allPostDTOs.add(new PostDTO(post, c, e));
            } else {
                allPostDTOs.add(new PostDTO(post, c));
            }
        }
        // todo: pagination and sorting
        return allPostDTOs;
    }

    @GetMapping(path="/posts/{postId}")       // consumes="application/json"
    @JsonView(PostDTO.FullView.class)
    public PostDTO getPostById(@PathVariable String postId) {

        PostDTO post;

        Post p = posts.findOne(Long.parseLong(postId));
        Content c = contents.findOne(p.getContentId());
        Editor e;
        if (c.getType() == Type.CODE) {
            e = editors.findOne(c.getAttachmentId());
            post = new PostDTO(p, c, e);
        } else {
            post = new PostDTO(p, c);
        }
        post.setComments(comments.findCommentByPostId(p.getId()));
        return post;
    }

    @PostMapping(path="/posts/new") // consumes="application/json"
    @ResponseStatus(HttpStatus.CREATED)
    public void newPost(@RequestBody @Validated PostForm post) {

        Post p = new Post(post.getTitle(), post.getAuthor(), post.getDescription());
        p.setCreated();
        Content c = new Content(post.getType(), post.getContentText(), post.getVersion());
        if (post.getType() == Type.CODE) {
            Editor e = new Editor();
            c.setAttachmentId(editors.save(e).getId());
            p.setContentId(contents.save(c).getId());
        } else if (post.getType() == Type.TEXT) {
            p.setContentId(contents.save(c).getId());
        }
        posts.save(p);
    }

    // todo: create endpoints for GET initial editor text for CODE posts
}
