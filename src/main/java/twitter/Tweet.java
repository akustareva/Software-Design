package twitter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tweet {
    private Date createdAt;
    private String text;
    private String userId;
    private String userName;

    public Tweet(String createdAt, String text, String userId, String userName) throws ParseException {
        DateFormat format = new SimpleDateFormat("EEE MMM dd kk:mm:ss Z yyyy", Locale.ENGLISH);
        this.createdAt = format.parse(createdAt);
        this.text = text;
        this.userId = userId;
        this.userName = userName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return String.join("\n", text, userName, createdAt.toString());
    }
}
