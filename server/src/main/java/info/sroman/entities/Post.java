package info.sroman.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String author;
    private String title;
    private String description;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="contentId")
    private Content content;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="commentId")
    private List<Comment> comments;

    private LocalDateTime lastModified;
    private LocalDateTime created;

    public Post() { setCreated(); }

    public Post(String title, String author, String description, Content content) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.created = LocalDateTime.now();
        this.lastModified = this.created;
    }

    public Post(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.created = LocalDateTime.now();
        this.lastModified = this.created;
    }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; this.setLastModified(); }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; this.setLastModified(); }
    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }
    public void setLastModified() { this.lastModified = LocalDateTime.now(); }
    public LocalDateTime getCreated() { return created; }
    public void setCreated() { this.created = LocalDateTime.now(); this.setLastModified(); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; this.setLastModified(); }
    public Content getContent() { return content; }
    public void setContent(Content content) { this.content = content; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; this.setLastModified(); }
}
