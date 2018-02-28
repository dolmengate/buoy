package info.sroman.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="post_comments")
public class Comment {

    private Comment() { }
    public Comment(String author, String text) {
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
    }
    public Comment(String author, String text, Comment replyTo) {
        this.text = text;
        this.author = author;
        this.created = LocalDateTime.now();
        this.replyTo = replyTo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String text;
    private String author;

    @ManyToOne(fetch=FetchType.LAZY)
    // no @JoinColumn: this relationship is one-way: from the one to the many (post to comment)
    private Post post;

    @ManyToOne(fetch=FetchType.EAGER)
    private Comment replyTo;

    @CreatedDate
    private LocalDateTime created;

    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }
    public Comment getReplyTo() { return replyTo; }
    public void setReplyTo(Comment replyTo) { this.replyTo = replyTo; }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", post=" + post +
                ", replyTo=" + replyTo +
                ", created=" + created +
                '}';
    }
}

