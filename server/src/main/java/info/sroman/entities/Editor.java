package info.sroman.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Editor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    // hash id for url and non-generated id?
    private String text;

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
