package twitter;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import twitter.http.JsonParser;
import twitter.http.UrlReader;

import java.text.ParseException;
import java.util.List;

public class TwitterClient {
    private final static String HOST = "api.twitter.com";
    private final static String API_VERSION = "1.1";
    private final static String CONSUMER_KEY = "whyoT9mECKbWgEy4dFE7yz0hp";
    private final static String CONSUMER_SECRET = "Ly91PYaIWgIkuxIBJCSNr7SK7xQnopuM0CZij8U3k4tykla5CW";
    private final static String ACCESS_TOKEN = "910950825723392001-BdI7k0suj7EIWCWENzbV7mwz2jkMa3e";
    private final static String ACCESS_TOKEN_SECRET = "vwEh2rzWGsx6lSfa39jLFcluh6XX5q0FrNYlDiJXCV3A6";
    private static OAuthConsumer consumer;
    private UrlReader urlReader;
    private JsonParser jsonParser;

    public TwitterClient() {
        authentication();
        urlReader = new UrlReader();
        jsonParser = new JsonParser();
    }

    public static OAuthConsumer getConsumer() {
        authentication();
        return consumer;
    }

    List<Tweet> getHashtagInfo(String hashtag) throws ParseException, OAuthException {
        String response = urlReader.readAsText(createUrl(hashtag));
        return jsonParser.parseResponse(response);
    }

    private static void authentication() {
        if (consumer == null) {
            consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
            consumer.setTokenWithSecret(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
        }
    }

    private String createUrl(String hashtag) {
        return "https://" + HOST + "/" + API_VERSION + "/search/tweets.json?q=" + hashtag.replace("#", "%23");
    }
}
