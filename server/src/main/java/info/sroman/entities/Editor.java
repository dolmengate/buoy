package info.sroman.entities;

import info.sroman.model.Attachment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Editor implements Attachment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String text;
//    private String language;

    public Editor() {}
    public Editor(String text) { this.text = text; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "Editor{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
