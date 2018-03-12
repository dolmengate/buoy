package info.sroman.entities;

import info.sroman.model.CommentForm;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="post_comments")
public class Comment {

    private Comment() { }
    public Comment(CommentForm commentForm) {
        this.text = commentForm.getText();
        this.author = commentForm.getAuthor();
        this.created = new Date();
    }

    public Comment(CommentForm commentForm, Comment replyTo) {
        this.text = commentForm.getText();
        this.author = commentForm.getAuthor();
        this.created = new Date();
        this.replyTo =  replyTo;
    }

    public Comment(String author, String text) {
        this.text = text;
        this.author = author;
        this.created = new Date();
    }

    public Comment(String author, String text, Comment replyTo) {
        this.text = text;
        this.author = author;
        this.created = new Date();
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
    private Date created;

    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }
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

