package info.sroman.model;

import info.sroman.entities.Type;

import javax.persistence.Entity;
import java.time.LocalDateTime;

public class PostDTO {

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

    // File attributes
    // ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getEditorText() {
        return editorText;
    }

    public void setEditorText(String editorText) {
        this.editorText = editorText;
    }
}
