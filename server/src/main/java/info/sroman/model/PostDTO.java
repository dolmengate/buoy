package info.sroman.model;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    // Jackson serialization view marker interfaces
    public interface MetadataOnlyView {}
    public interface FullView extends MetadataOnlyView {}

    public PostDTO() { }
    public PostDTO(Post p) {
        this.id = p.getPostId();
        this.author = p.getAuthor();
        this.title = p.getTitle();
        this.description = p.getDescription();
        this.lastModified = p.getLastModified();
        this.created = p.getCreated();

        this.type = p.getContent().getType();
        this.version = p.getContent().getVersion();

        this.editorText = p.getContent().getEditor().getText();
    }

    // Post attributes
    private Long id;
    private String author;
    private String title;
    private String description;
    private LocalDateTime lastModified;
    private LocalDateTime created;

    // Content attributes
    private Type type;
    private Float version;
    private String contentText;

    // Editor attributes
    private String editorText;

    // Comments
    private Integer numComments;
    private List<Comment> comments;

    // File attributes
    // ...

    @JsonView(MetadataOnlyView.class)
    public Long getId() { return id; }

    @JsonView(MetadataOnlyView.class)
    public String getAuthor() { return author; }

    @JsonView(MetadataOnlyView.class)
    public String getTitle() { return title; }

    @JsonView(MetadataOnlyView.class)
    public String getDescription() { return description; }

    @JsonView(MetadataOnlyView.class)
    public LocalDateTime getLastModified() { return lastModified; }

    @JsonView(MetadataOnlyView.class)
    public LocalDateTime getCreated() { return created; }

    @JsonView(MetadataOnlyView.class)
    public Type getType() { return type; }

    @JsonView(MetadataOnlyView.class)
    public Float getVersion() { return version; }

    @JsonView(FullView.class)
    public String getContentText() { return contentText; }

    @JsonView(FullView.class)
    public String getEditorText() { return editorText; }

    @JsonView(FullView.class)
    public List<Comment> getComments() { return comments; }

    @JsonView(MetadataOnlyView.class)
    public Integer getNumComments() { return numComments; }

    public void setId(Long id) { this.id = id; }
    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public void setType(Type type) { this.type = type; }
    public void setVersion(Float version) { this.version = version; }
    public void setContentText(String contentText) { this.contentText = contentText; }
    public void setEditorText(String editorText) { this.editorText = editorText; }
    public void setComments(List<Comment> comments) { this.comments = comments; this.setNumComments(this.comments.size()); }
    private void setNumComments(Integer numComments) { this.numComments = numComments; }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastModified=" + lastModified +
                ", created=" + created +
                ", type=" + type +
                ", version=" + version +
                ", contentText='" + contentText + '\'' +
                ", editorText='" + editorText + '\'' +
                ", comments=" + comments.size() +
                '}';
    }
}
