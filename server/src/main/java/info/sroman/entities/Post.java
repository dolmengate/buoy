package info.sroman.entities;

import info.sroman.model.PostDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    public void setCreated() { this.created = LocalDateTime.now(); this.setLastModified(); }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(author, post.author) &&
                Objects.equals(title, post.title) &&
                Objects.equals(description, post.description) &&
                Objects.equals(contentId, post.contentId) &&
                Objects.equals(lastModified, post.lastModified) &&
                Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, description, contentId, lastModified, created);
    }
}
