package info.sroman;

public class Message {
    private String text;
    private boolean freshConnect;

    public Message() {}

    public Message(String text) {
        this.text = text;
        this.freshConnect = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFreshConnect() {
        return freshConnect;
    }

    public void setFreshConnect(boolean freshConnect) {
        this.freshConnect = freshConnect;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", freshConnect=" + freshConnect +
                '}';
    }
}
