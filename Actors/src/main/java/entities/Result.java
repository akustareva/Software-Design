package entities;

/**
 * Created by Anna on 09.03.2018.
 */
public class Result {
    private String url;
    private String title;

    public Result(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + ": " + url;
    }
}
