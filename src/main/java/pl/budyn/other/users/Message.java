package pl.budyn.other.users;

import javax.validation.constraints.NotNull;

/**
 * Created by hlibe on 19.12.2016.
 */
public class Message {

    @NotNull
    private String name;
    @NotNull
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
