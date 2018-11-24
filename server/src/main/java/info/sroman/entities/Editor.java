package info.sroman.entities;

import javax.persistence.*;

//@Entity
public class Editor extends Attachment {

    private String text;
//    private String language;

    public Editor() { super(); }
    public Editor(String text) { super(); this.text = text; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @Override
    public String toString() {
        return "Editor{" +
                ", text='" + text + '\'' +
                '}';
    }
}
