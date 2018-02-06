package info.sroman.entities;

import javax.persistence.*;

@Entity
@Table(name="editors")
public class Editor extends Attachment {

    @OneToOne(fetch=FetchType.EAGER, mappedBy="editor")
    private Content content;

    private String text;
//    private String language;

    public Editor() { super(); }
    public Editor(String text) { super(); this.text = text; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Content getContent() { return content; }
    public void setContent(Content content) { this.content = content; }

    @Override
    public String toString() {
        return "Editor{" +
                "content=" + content +
                ", text='" + text + '\'' +
                ", attachmentId=" + attachmentId +
                '}';
    }
}
