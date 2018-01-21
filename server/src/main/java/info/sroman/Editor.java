package info.sroman;

public class Editor {
    private String text;

    public Editor() {}
    public Editor(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
