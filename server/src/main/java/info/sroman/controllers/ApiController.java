package info.sroman.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.*;
import info.sroman.model.CommentForm;
import info.sroman.model.PostDTO;
import info.sroman.model.PostForm;
import info.sroman.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PostRepository posts;

    @Autowired
    private AttachmentRepository attachmentsRepo;

    @Autowired
    private CommentRepository comments;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    // todo: pagination and sorting
    @GetMapping(path="/posts") // /posts/page/{pageId}
    @JsonView(PostDTO.MetadataOnlyView.class)
    public List<PostDTO> getAllPosts() {
        List<Post> allPosts = (List<Post>)posts.findAll();
        return allPosts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @GetMapping(path="/posts/{postId}")
    public PostDTO getPostById(@PathVariable String postId) {
        return new PostDTO(posts.findOne(UUID.fromString(postId)));
    }

    @PostMapping(path="/posts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO newPost(@RequestBody @Validated PostForm form) {
        Post p = new Post(form.getTitle(), form.getAuthor(), form.getDescription());
        return new PostDTO(posts.save(p));
    }

    @PostMapping(path="/posts/save/{postId}")
    public PostDTO updateContentAndIncrementVersion(@PathVariable String postId, @RequestBody PostDTO pDTO) {
        pDTO.setVersion(pDTO.getVersion() + 0.1F);
        return new PostDTO(posts.save(new Post(pDTO)));
    }

    @PostMapping(path="/posts/addcomment/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable String postId, @RequestBody CommentForm cForm) {
        Post post = posts.findOne(UUID.fromString(postId));
        if (cForm.getReplyToId() == null) { // post is not a reply: it is a top-level comment
            post.getComments().add(new Comment(cForm));
        } else {
            Comment parentComment = comments.findOne(UUID.fromString(cForm.getReplyToId()));
            post.getComments().add(new Comment(cForm, parentComment));
        }
        posts.save(post);
    }

    @GetMapping(path="/posts/getcomments/{postId}")
    public TreeSet<Comment> getAllCommentsForPost(@PathVariable String postId) {
        return new TreeSet<>(posts.findOne(UUID.fromString(postId)).getComments());
    }
}
