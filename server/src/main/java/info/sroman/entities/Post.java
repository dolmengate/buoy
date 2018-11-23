package info.sroman.entities;

import info.sroman.model.PostDTO;
import info.sroman.model.PostForm;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post implements Comparable<Post> {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "post_id")
    private String postId;

    private String author;
    private String title;
    private String description;
    private float version;
    private Date lastModified;
    private Date created;

    // no mappedBy: Comment.post has no @JoinColumn annotation: this relationship is one-way: one to many
    // fetch=FetchType.EAGER: selections will automatically get and construct all contained entities
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Attachment> attachments;

    public Post() { setCreated(); }

    public Post(PostForm form) {
        this.author = form.getAuthor();
        this.title = form.getTitle();
        this.description = form.getDescription();
    }

    public Post(PostDTO postDto) {
        this.postId = postDto.getPostId();
        this.author = postDto.getAuthor();
        this.title = postDto.getTitle();
        this.description = postDto.getDescription();
        this.lastModified = postDto.getLastModified();
        this.created = postDto.getCreated();
        this.version = postDto.getVersion();
        this.comments = postDto.getComments();
        this.attachments = postDto.getAttachments();
    }

    public Post(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.created = new Date();
        this.lastModified = this.created;
    }

    public String getPostId() { return postId; }
    public void setPostId(String postId) { this.postId = postId; this.setLastModified(); }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; this.setLastModified(); }
    public Date getLastModified() { return lastModified; }
    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }
    public void setLastModified() { this.lastModified = new Date(); }
    public Date getCreated() { return created; }
    public void setCreated() { this.created = new Date(); this.setLastModified(); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; this.setLastModified(); }
    public Set<Comment> getComments() { return comments; }
    public void setComments(Set<Comment> comments) { this.comments = comments; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; this.setLastModified(); }
    public float getVersion() { return version; }
    public void setVersion(float version) { this.version = version; }
    public Set<Attachment> getAttachments() { return attachments; }
    public void setAttachments(Set<Attachment> attachments) { this.attachments = attachments; }

    // sorts late
    @Override
    public int compareTo(Post p) {
        if (this.created.before(p.created)) {
            return 1;
        } else if (this.created.equals(p.created)) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", lastModified=" + lastModified +
                ", created=" + created +
                '}';
    }
}
