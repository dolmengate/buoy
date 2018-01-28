package info.sroman.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.*;
import info.sroman.model.PostDTO;
import info.sroman.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(path="/posts")
    @JsonView(PostDTO.MetadataOnlyView.class)
    public List<PostDTO> getAllPosts() {
        // get paginated posts
        return new ArrayList<PostDTO>();
    }

    @GetMapping(path="/posts/{postId}")       // consumes="application/json"
    @JsonView(PostDTO.FullView.class)
    public PostDTO getPostById(@PathVariable String postId) {

        PostDTO post;

        Post p = posts.findOne(Long.parseLong(postId));
        Content c = contents.findOne(p.getContentId());
        Editor e = editors.findOne(c.getAttachmentId());
        List<Comment> pComments = comments.findCommentByPostId(p.getId());

        post = new PostDTO(
                p.getAuthor(), p.getTitle(), p.getDescription(), c.getType(), c.getVersion(), c.getText(), e.getText());
        post.setId(p.getId());
        post.setCreated(p.getCreated());
        post.setLastModified(p.getLastModified());
        post.setComments(pComments);

        return post;
    }

    @PostMapping(path="/posts/new") // consumes="application/json"
    @ResponseStatus
    public int newPost(@RequestBody PostDTO post) { //@Valid? @BindingResult result?

        Post p = new Post(post.getTitle(), post.getAuthor(), post.getDescription());
        Editor e = new Editor(post.getEditorText());
        Content c = new Content(post.getType(), post.getContentText(), post.getVersion());

        // establish relationships through generated IDs and save records
        Long editorId = editors.save(e).getId();
        c.setAttachmentId(editorId);
        Long contentId = contents.save(c).getId();
        p.setContentId(contentId);

        return 200;
    }

    // todo: create endpoints for GET initial editor text for CODE posts
}
