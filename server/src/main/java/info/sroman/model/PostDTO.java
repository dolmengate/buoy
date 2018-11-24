package info.sroman.model;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.*;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class PostDTO {

    // Jackson serialization view marker interfaces
    public interface MetadataOnlyView {}

    public PostDTO() { }
    public PostDTO(Post p) {
        this.postId = p.getPostId().toString();
        this.author = p.getAuthor();
        this.title = p.getTitle();
        this.description = p.getDescription();
        this.lastModified = p.getLastModified();
        this.created = p.getCreated();
        this.version = p.getVersion();
        this.comments = new TreeSet<>(p.getComments());
        this.attachments = new TreeSet<>(p.getAttachments());
    }

    private String postId;
    private String author;
    private String title;
    private String description;
    private Date lastModified;
    private Date created;
    private float version;

    private Set<Attachment> attachments;

    private TreeSet<Comment> comments;

    @JsonView(MetadataOnlyView.class)
    public String getPostId() { return postId; }

    @JsonView(MetadataOnlyView.class)
    public String getAuthor() { return author; }

    @JsonView(MetadataOnlyView.class)
    public String getTitle() { return title; }

    @JsonView(MetadataOnlyView.class)
    public String getDescription() { return description; }

    @JsonView(MetadataOnlyView.class)
    public Date getLastModified() { return lastModified; }

    @JsonView(MetadataOnlyView.class)
    public Date getCreated() { return created; }

    @JsonView(MetadataOnlyView.class)
    public Float getVersion() { return version; }

    public TreeSet<Comment> getComments() { return comments; }

    public Set<Attachment> getAttachments() { return attachments; }

    public void setPostId(String postId) { this.postId = postId; }
    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }
    public void setCreated(Date created) { this.created = created; }
    public void setVersion(Float version) { this.version = version; }
    public void setComments(TreeSet<Comment> comments) { this.comments = comments; }
    public void setAttachments(Set<Attachment> attachments) { this.attachments = attachments; }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastModified=" + lastModified +
                ", created=" + created +
                ", version=" + version +
                ", comments=" + comments.size() +
                '}';
    }
}
