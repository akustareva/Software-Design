package seachers;

import entities.Result;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Anna on 09.03.2018.
 */
public class GoogleSearcher extends Searcher {
    private static final String SEARCHER_NAME = "Google";
    private static final String GOOGLE_QUERY = "http://www.google.com/search?q=";
    private static final int RESULTS_NUM = 10;
    private String query;

    public GoogleSearcher(String query) {
        this.query = query;
    }

    @Override
    public List<Result> search() throws IOException {
        Elements links = Jsoup.connect(getUrlForSearch(query))
                .userAgent(USER_AGENT)
                .get().select(".g>.r>a");
        return extractLinks(links);
    }

    private String getUrlForSearch(String query) throws UnsupportedEncodingException {
        return GOOGLE_QUERY + URLEncoder.encode(query, "UTF-8") + String.format("&num=%d", RESULTS_NUM);
    }

    public String getSearcherName() {
        return SEARCHER_NAME;
    }
}
