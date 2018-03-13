package info.sroman.model;

import com.fasterxml.jackson.annotation.JsonView;
import info.sroman.entities.*;

import java.util.Date;
import java.util.List;

public class PostDTO {

    // Jackson serialization view marker interfaces
    public interface MetadataOnlyView {}

    public PostDTO() { }
    public PostDTO(Post p) {
        this.postId = p.getPostId();
        this.author = p.getAuthor();
        this.title = p.getTitle();
        this.description = p.getDescription();
        this.lastModified = p.getLastModified();
        this.created = p.getCreated();

        this.type = p.getContent().getType();
        this.version = p.getContent().getVersion();

        this.comments = p.getComments();

        this.editorId = p.getContent().getEditor().getAttachmentId();
        this.editorText = p.getContent().getEditor().getText();
        if (p.getComments() != null)
            this.numComments = p.getComments().size();
    }

    // Post attributes
    private Long postId;
    private String author;
    private String title;
    private String description;
    private Date lastModified;
    private Date created;

    // Content attributes
    private Type type;
    private Float version;
    private String contentText;

    // Editor attributes
    private Long editorId;
    private String editorText;
    private boolean cachedVersion;

    // Comments
    private Integer numComments;
    private List<Comment> comments;

    // File attributes
    // ...

    @JsonView(MetadataOnlyView.class)
    public Long getPostId() { return postId; }

    @JsonView(MetadataOnlyView.class)
    public String getAuthor() { return author; }

    @JsonView(MetadataOnlyView.class)
    public String getTitle() { return title; }

    @JsonView(MetadataOnlyView.class)
    public String getDescription() { return description; }

    @JsonView(MetadataOnlyView.class)
    public Date getLastModified() { return lastModified; }

    @JsonView(MetadataOnlyView.class)
    public Date getCreated() { return created; }

    @JsonView(MetadataOnlyView.class)
    public Type getType() { return type; }

    @JsonView(MetadataOnlyView.class)
    public Float getVersion() { return version; }

    public String getContentText() { return contentText; }
    public Long getEditorId() { return editorId; }
    public String getEditorText() { return editorText; }
    public boolean isCachedVersion() { return cachedVersion; }
    public List<Comment> getComments() { return comments; }

    @JsonView(MetadataOnlyView.class)
    public Integer getNumComments() { return numComments; }

    public void setPostId(Long postId) { this.postId = postId; }
    public void setAuthor(String author) { this.author = author; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }
    public void setCreated(Date created) { this.created = created; }
    public void setType(Type type) { this.type = type; }
    public void setVersion(Float version) { this.version = version; }
    public void setContentText(String contentText) { this.contentText = contentText; }
    public void setEditorId(Long editorId) { this.editorId = editorId; }
    public void setEditorText(String editorText) { this.editorText = editorText; }
    public void setCachedVersion(boolean cachedVersion) { this.cachedVersion = cachedVersion; }
    public void setComments(List<Comment> comments) { this.comments = comments; this.setNumComments(this.comments.size()); }
    private void setNumComments(Integer numComments) { this.numComments = numComments; }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lastModified=" + lastModified +
                ", created=" + created +
                ", type=" + type +
                ", version=" + version +
                ", contentText='" + contentText + '\'' +
                ", editorText='" + editorText + '\'' +
                ", comments=" + comments.size() +
                '}';
    }
}
