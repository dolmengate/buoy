package info.sroman.entities;

import info.sroman.model.PostForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    private String author;
    private String title;
    private String description;

    // cascade: propagate EntityManager operations to all related entities (works both up and down the chain of relationships)
    @OneToOne(cascade=CascadeType.ALL)
    // @JoinColumn(name="relatedEntity"): this column is a FK to the related entity
    @JoinColumn(name="contentId")
    private Content content;

    // no mappedBy: Comment.post has no @JoinColumn annotation: this relationship is one-way: one to many
    // fetch=FetchType.EAGER: selections will automatically get and construct all contained entities
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Comment> comments;

    private Date lastModified;
    private Date created;

    public Post() { setCreated(); }

    public Post(PostForm form) {
        this.author = form.getAuthor();
        this.title = form.getTitle();
        this.description = form.getDescription();
    }

    public Post(String title, String author, String description, Content content, Comment... comments) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.created = new Date();
        this.lastModified = this.created;
        this.comments = new ArrayList<>(Arrays.asList(comments));
    }

    public Post(String title, String author, String description, Content content) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.created = new Date();
        this.lastModified = this.created;
    }

    public Post(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.created = new Date();
        this.lastModified = this.created;
    }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; this.setLastModified(); }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; this.setLastModified(); }
    public Date getLastModified() { return lastModified; }
    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }
    public void setLastModified() { this.lastModified = new Date(); }
    public Date getCreated() { return created; }
    public void setCreated() { this.created = new Date(); this.setLastModified(); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; this.setLastModified(); }
    public Content getContent() { return content; }
    public void setContent(Content content) { this.content = content; }
    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; this.setLastModified(); }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content=" + content +
                ", comments=" + comments +
                ", lastModified=" + lastModified +
                ", created=" + created +
                '}';
    }
}
