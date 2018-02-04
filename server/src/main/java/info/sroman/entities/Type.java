package info.sroman.entities;

public enum Type {

    EDITOR("EDITOR"),
    IMAGE("IMAGE"),
    TEXT("TEXT");
    //ZIP("ZIP") TODO: add zip Type to Posts?

    private final String name;

    private Type(final String name) { this.name = name; }
    public String getName() { return name; }
}
