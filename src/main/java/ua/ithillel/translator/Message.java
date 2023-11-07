package ua.ithillel.translator;

import java.util.Date;

public class Message {
    private String content;
    private Date date = new Date();

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
