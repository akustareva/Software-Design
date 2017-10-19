package twitter.http;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import twitter.Tweet;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public List<Tweet> parseResponse(String response) throws ParseException {
        List<Tweet> result = new ArrayList<>();
        com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
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
