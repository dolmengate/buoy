package info.sroman.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Attachment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long attachmentId;

    public Long getAttachmentId() { return this.attachmentId; }
    public void setAttachmentId(Long attachmentId) { this.attachmentId = attachmentId; }
}
