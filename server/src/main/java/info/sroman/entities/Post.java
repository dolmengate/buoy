package info.sroman.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String author;
    private String title;
    private String description;

    private Long contentId;

    private LocalDateTime lastModified;
    private LocalDateTime created;

    public Post() { }

    public Post(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.created = LocalDateTime.now();
        this.lastModified = this.created;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; this.setLastModified(); }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; this.setLastModified(); }
    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }
    public void setLastModified() { this.lastModified = LocalDateTime.now(); }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; this.setLastModified(); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; this.setLastModified(); }
    public Long getContentId() { return contentId; }
    public void setContentId(Long contentId) { this.contentId = contentId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; this.setLastModified(); }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", lastModified=" + lastModified +
                ", created=" + created +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
