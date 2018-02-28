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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    PostRepository posts;

    @Autowired
    EditorRepository editors;

    @Autowired
    CommentRepository comments;

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    // todo: pagination and sorting
    @GetMapping(path="/posts") // /posts/page/{pageId}
    @JsonView(PostDTO.MetadataOnlyView.class)
    public List<PostDTO> getAllPosts() {
        List<Post> allPosts = (List<Post>)posts.findAll();
        return allPosts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @GetMapping(path="/posts/{postId}")
//    @JsonView(PostDTO.MetadataOnlyView.class)
    public PostDTO getPostById(@PathVariable String postId) {
        return new PostDTO(posts.findOne(Long.parseLong(postId)));
    }

    @PostMapping(path="/posts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void newPost(@RequestBody @Validated PostForm form) {

        Post p = new Post(form.getTitle(), form.getAuthor(), form.getDescription());
        Content c = new Content(form.getType(), form.getContentText(), 1.0F);
        if (form.getType() == Type.EDITOR) {
            Editor e = new Editor();
            c.setEditor(e);
        }
        p.setContent(c);
        posts.save(p);
    }

    @PostMapping(path="/posts/save/{postId}")
    public PostDTO updateContentAndIncrementVersion(@PathVariable String postId, @RequestBody PostDTO pDTO) {
        Post post = posts.findOne(Long.parseLong(postId));

        // increment Content version
        post.getContent().setVersion(post.getContent().getVersion() + 0.1F);

        // update Editor text
        post.getContent().getEditor().setText(pDTO.getEditorText());

        return new PostDTO(posts.save(post));
    }
}
