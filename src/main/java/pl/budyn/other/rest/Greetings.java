package pl.budyn.other.rest;

/**
 * Created by hlibe on 17.12.2016.
 */
public class Greetings {
    private final long id;
    private final String content;

    public Greetings(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
