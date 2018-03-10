package seachers;

import entities.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 09.03.2018.
 */
public abstract class Searcher {
    private static final String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";

    public abstract List<Result> search() throws IOException;

    public abstract String getSearcherName();

    protected List<Result> search(String query, String selectRule) throws IOException {
        List<Result> results = new ArrayList<>();
//        System.out.println(query);
        Elements links = Jsoup.connect(query)
                .userAgent(USER_AGENT)
                .get().select(selectRule);
        for (Element link : links) {
            String url = link.absUrl("href");
            if (url.startsWith("http://www.google.ru/url?q=")) {
                url = url.substring(url.indexOf('=') + 1);
            }
            url = URLDecoder.decode(url, "UTF-8");
            if (url.startsWith("http") || url.startsWith("https")) {
                results.add(new Result(url, link.text()));
            }
            if (results.size() == 5) {
                break;
            }
        }
        return results;
    }
}
