package seachers;

import entities.Result;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Anna on 09.03.2018.
 */
public class YandexSearcher extends Searcher {
    private static final String SEARCHER_NAME = "Yandex";
    private static final String YANDEX_QUERY = "http://www.yandex.ru/search/?query=";
    private static final int RESULTS_NUM = 10;
    private String query;

    public YandexSearcher(String query) {
        this.query = query;
    }

    @Override
    public List<Result> search() throws IOException {
        return search(getUrlForSearch(query), "a[tabindex=2]");
    }

    private String getUrlForSearch(String query) throws UnsupportedEncodingException {
        return YANDEX_QUERY + URLEncoder.encode(query, "UTF-8") + String.format("&numdoc=%d", RESULTS_NUM) + "&p=0";
    }

    @Override
    public String getSearcherName() {
        return SEARCHER_NAME;
    }
}
