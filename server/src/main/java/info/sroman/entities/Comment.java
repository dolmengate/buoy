package info.sroman.entities;

import info.sroman.model.CommentForm;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="comments")
public class Comment implements Comparable<Comment> {

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

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "content_id")
    private String commentId;

    private String text;
    private String author;

    @ManyToOne(fetch=FetchType.LAZY)
    // no @JoinColumn: this relationship is one-way: from the one to the many (post to comment)
    private Post post;

    @ManyToOne(fetch=FetchType.EAGER)
    private Comment replyTo;

    @Column(name = "reply_to_attachment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment replyToAttachment;

    private Date created;

    public String getCommentId() { return commentId; }
    public void setCommentId(String commentId) { this.commentId = commentId; }
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

    // sorts newest to oldest
    @Override
    public int compareTo(Comment c) {
        if (this.created.before(c.created)) {
            return 1;
        } else if (this.created.equals(c.created)) {
            return 0;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(post, comment.post) &&
                Objects.equals(replyTo, comment.replyTo) &&
                Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, text, author, post, replyTo, created);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", post=" + post +
                ", replyTo=" + (replyTo != null ? replyTo.getCommentId() : null) +
                ", created=" + created +
                '}';
    }
}
