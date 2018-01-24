package info.sroman.model;

public class SocketMessage {
    private String text;
    private boolean freshConnect;

    public SocketMessage() {}

    public SocketMessage(String text) {
        this.text = text;
        this.freshConnect = false;
    }

    public String getText() { return this.text; }
    public void setText(String text) { this.text = text; }
    public boolean isFreshConnect() { return this.freshConnect; }
    public void setFreshConnect(boolean freshConnect) { this.freshConnect = freshConnect; }

    @Override
    public String toString() {
        return "SocketSocketMessage{" +
                "text='" + text + '\'' +
                ", freshConnect=" + freshConnect +
                '}';
    }
}
