package info.sroman.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Type type;
    private String author;

    @LastModifiedDate
    private LocalDateTime lastModified;

    @CreatedDate
    private LocalDateTime created;

    private String description;
    private String title;
    private Float version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Float getVersion() { return version; }
    public void setVersion(Float version) { this.version = version; }
}
