package info.sroman.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PostForm {

    @NotNull
    private String author;

    @NotNull
    private String title;

    private String description;

    @DecimalMin("1.0")
    private Float version;

    private String contentText;

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Float getVersion() { return version; }
    public void setVersion(Float version) { this.version = version; }
    public String getContentText() { return contentText; }
    public void setContentText(String contentText) { this.contentText = contentText; }

    @Override
    public String toString() {
        return "PostForm{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", version=" + version +
                ", contentText='" + contentText + '\'' +
                '}';
    }
}
