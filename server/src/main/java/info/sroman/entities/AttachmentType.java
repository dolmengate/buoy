package info.sroman.entities;

import javax.persistence.*;

@Entity
@Table(name = "attachment_type")
public class AttachmentType {

    @Id
    @Column(name = "attachment_type_id")
    private Integer attachmentTypeId;

    private String name;

    public Integer getContentId() {
        return attachmentTypeId;
    }
    public void setContentId(Integer attachmentTypeId) {
        this.attachmentTypeId = attachmentTypeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
