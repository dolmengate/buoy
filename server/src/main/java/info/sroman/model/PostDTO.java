package info.sroman.model;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.Comment;
import info.sroman.entities.Type;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    // Jackson serialization views
    public interface MetadataOnlyView {};
    public interface FullView extends MetadataOnlyView {};

    public PostDTO() { }
    public PostDTO(String author, String title, String description, Type type, Float version, String contentText, String editorText) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.type = type;
        this.version = version;
        this.contentText = contentText;
        this.editorText = editorText;
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
    public void setComments(List<Comment> comments) { this.comments = comments; }

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
