package info.sroman.entities;

import javax.persistence.*;

@Entity
@Table(name="contents")
public class Content {

    public Content() { }

    public Content(Type type, String text, Float version) {
        this.type = type;
        this.text = text;
        this.version = version;
    }

    public Content(Editor editor, Type type, String text, Float version) {
        this.editor = editor;
        this.type = type;
        this.text = text;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long contentId;

    // fetch=FetchType.EAGER: load post immediately (don't wait for the .getPost() method to be called)
    // mappedBy="relatedColumn": column or attribute in related entity -- creates two-way relationship
    @OneToOne(fetch=FetchType.EAGER, mappedBy="content")
    private Post post;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="editorId")
    private Editor editor;
    // other Attachment types will have nullable fields

    private Type type;
    private String text;    // used if type=text otherwise null
    private Float version;

    public Long getContentId() { return contentId; }
    public void setContentId(Long contentId) { this.contentId = contentId; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    public Editor getEditor() { return editor; }
    public void setEditor(Editor editor) { this.editor = editor; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Float getVersion() { return version; }
    public void setVersion(Float version) { this.version = version; }
}
