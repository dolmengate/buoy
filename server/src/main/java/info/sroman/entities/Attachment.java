package info.sroman.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public abstract class Attachment {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "attachment_id")
    private String attachmentId;

    @Column(name = "attachment_type_id")
    @JoinColumn(name = "attachment_type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private AttachmentType attachmentType;

    private float version;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Comment> comments;

    public String getAttachmentId() { return this.attachmentId; }
    public void setAttachmentId(String attachmentId) { this.attachmentId = attachmentId; }
    public AttachmentType getAttachmentType() { return attachmentType; }
    public void setAttachmentType(AttachmentType attachmentType) { this.attachmentType = attachmentType; }
    public float getVersion() { return version; }
    public void setVersion(float version) { this.version = version; }
    public Set<Comment> getComments() { return comments; }
    public void setComments(Set<Comment> comments) { this.comments = comments; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}
