package info.sroman.model;

import info.sroman.entities.Type;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PostForm {

    @NotNull
    private String author;

    @NotNull
    private String title;

    private String description;

    private LocalDateTime lastModified;
    private LocalDateTime created;

    // Content attributes
    @NotNull
    private Type type;

    @DecimalMin("1.0")
    private Float version;

    private String contentText;

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public Float getVersion() { return version; }
    public void setVersion(Float version) { this.version = version; }
    public String getContentText() { return contentText; }
    public void setContentText(String contentText) { this.contentText = contentText; }

    @Override
    public String toString() {
        return "PostForm{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastModified=" + lastModified +
                ", created=" + created +
                ", type=" + type +
                ", version=" + version +
                ", contentText='" + contentText + '\'' +
                '}';
    }
}
