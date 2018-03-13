package info.sroman.model;

import info.sroman.entities.Editor;

public class SocketMessage {

    private String text;
    private boolean freshConnect;
    private Long postId;
    private Long editorId;

    public SocketMessage() {}
    public SocketMessage(String text) {
        this.text = text;
        this.freshConnect = false;
    }

    public SocketMessage(Editor editor) {
        this.text = editor.getText();
        this.editorId = editor.getAttachmentId();
        this.postId = editor.getContent().getPost().getPostId();
        this.freshConnect = false;
    }

    public String getText() { return this.text; }
    public void setText(String text) { this.text = text; }
    public boolean isFreshConnect() { return this.freshConnect; }
    public void setFreshConnect(boolean freshConnect) { this.freshConnect = freshConnect; }
    public Long getEditorId() { return editorId; }
    public void setEditorId(Long editorId) { this.editorId = editorId; }
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "text='" + text + '\'' +
                ", freshConnect=" + freshConnect +
                ", postId=" + postId +
                ", editorId=" + editorId +
                '}';
    }
}
