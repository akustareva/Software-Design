package twitter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterClient {
    private final static String HOST = "api.twitter.com";
    private final static String API_VERSION = "1.1";
    private final static String CONSUMER_KEY = "whyoT9mECKbWgEy4dFE7yz0hp";
    private final static String CONSUMER_SECRET = "Ly91PYaIWgIkuxIBJCSNr7SK7xQnopuM0CZij8U3k4tykla5CW";
    private final static String ACCESS_TOKEN = "910950825723392001-BdI7k0suj7EIWCWENzbV7mwz2jkMa3e";
    private final static String ACCESS_TOKEN_SECRET = "vwEh2rzWGsx6lSfa39jLFcluh6XX5q0FrNYlDiJXCV3A6";
    private final static long HOURS_IN_MILLIS = 3600000L;
    private static OAuthConsumer consumer;

    public TwitterClient() {
        authentication();
    }

    public int[] getHashtagFrequency(String hashtag, int numberOfHours) throws IOException, OAuthException, ParseException {
        int[] frequencyByHours = new int[numberOfHours];
        String response = sendRequest(createUrl(hashtag));
        List<Tweet> tweets = parseResponse(response);
        // tweets.forEach(System.out::println);
        long currentTime = System.currentTimeMillis();
        for (Tweet tweet : tweets) {
            long createdAt = tweet.getCreatedAt().getTime();
            long hoursSinceCreation = (currentTime - createdAt) / HOURS_IN_MILLIS;
            if (hoursSinceCreation <= numberOfHours) {
                frequencyByHours[(int) hoursSinceCreation]++;
            }
        }
        return frequencyByHours;
    }

    private static void authentication() {
        if (consumer == null) {
            consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
            consumer.setTokenWithSecret(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
        }
    }

    private URL createUrl(String hashtag) throws MalformedURLException {
        return new URL("https://" + HOST + "/" + API_VERSION + "/search/tweets.json?q=" + hashtag.replace("#", "%23"));
    }

    private String sendRequest(URL url) throws IOException, OAuthException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        consumer.sign(connection);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = String.join("\n", bufferedReader.lines().collect(Collectors.toList()));
        bufferedReader.close();
        return response;
    }

    private List<Tweet> parseResponse(String response) throws ParseException {
        List<Tweet> result = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonObject statuses = jsonParser.parse(response).getAsJsonObject();
        JsonArray jsonTweets = statuses.getAsJsonArray("statuses");
        for (JsonElement jsonTweet : jsonTweets) {
            JsonObject tweet = jsonTweet.getAsJsonObject();
            result.add(new Tweet(tweet.get("created_at").getAsString(), tweet.get("text").getAsString(),
                    tweet.getAsJsonObject("user").get("id_str").getAsString(), tweet.getAsJsonObject("user").get("name").getAsString()));
        }
        return result;
    }
}
