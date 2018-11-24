package info.sroman.entities;

import info.sroman.model.CommentForm;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="comment")
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
    @Type(type="uuid-char")
    @Column(name = "comment_id", columnDefinition = "char")
    private UUID commentId;

    private String text;
    private String author;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "reply_to_comment_id")
    private Comment replyTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to_attachment_id")
    private Attachment replyToAttachment;

    private Date created;

    public UUID getCommentId() { return commentId; }
    public void setCommentId(UUID commentId) { this.commentId = commentId; }
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
    public Attachment getReplyToAttachment() { return replyToAttachment; }
    public void setReplyToAttachment(Attachment replyToAttachment) { this.replyToAttachment = replyToAttachment; }

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
