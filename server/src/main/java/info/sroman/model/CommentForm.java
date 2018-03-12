package info.sroman.model;

public class CommentForm {

    public CommentForm() {}

    private String text;
    private String author;
    private Long replyToId;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Long getReplyToId() { return replyToId; }
    public void setReplyToId(Long replyToId) { this.replyToId = replyToId; }

    @Override
    public String toString() {
        return "CommentForm{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", replyToId=" + replyToId +
                '}';
    }
}
