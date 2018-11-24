package info.sroman.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

//@MappedSuperclass
@Entity
@Table(name = "attachment")
public class Attachment {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Type(type="uuid-char")
    @Column(name = "attachment_id", columnDefinition = "char")
    private UUID attachmentId;

    @JoinColumn(name = "attachment_type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private AttachmentType attachmentType;

    private float version;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "reply_to_attachment_id")
    private Set<Comment> comments;

    public UUID getAttachmentId() { return this.attachmentId; }
    public void setAttachmentId(UUID attachmentId) { this.attachmentId = attachmentId; }
    public AttachmentType getAttachmentType() { return attachmentType; }
    public void setAttachmentType(AttachmentType attachmentType) { this.attachmentType = attachmentType; }
    public float getVersion() { return version; }
    public void setVersion(float version) { this.version = version; }
    public Set<Comment> getComments() { return comments; }
    public void setComments(Set<Comment> comments) { this.comments = comments; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}
