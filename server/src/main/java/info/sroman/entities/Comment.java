package info.sroman.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="post_comments")
public class Comment {

    private Comment() { }
    public Comment(String text, String author) {
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String text;
    private String author;

    @ManyToOne(fetch=FetchType.LAZY)
    // no @JoinColumn: this relationship is one-way: from the one to the many (post to comment)
    private Post post;

    @CreatedDate
    private LocalDateTime created;

    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
}
