package entities;

import java.util.List;

/**
 * Created by Anna on 09.03.2018.
 */
public class Response {
    private String name;
    private List<Result> results;

    public Response(String name, List<Result> results) {
        this.name = name;
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public List<Result> getResults() {
        return results;
    }
}
