package twitter;

import oauth.signpost.exception.OAuthException;

import java.text.ParseException;
import java.util.List;

public class TwitterManager {
    final static long HOUR_IN_MILLIS = 3_600_000L;
    private final TwitterClient twitterClient;

    public TwitterManager(TwitterClient twitterClient) {
        this.twitterClient = twitterClient;
    }

    public int[] getHashtagFrequency(String hashtag, int numberOfHours) throws OAuthException, ParseException {
        int[] frequencyByHours = new int[numberOfHours];
        List<Tweet> tweets = twitterClient.getHashtagInfo(hashtag);
        long currentTime = System.currentTimeMillis();
        for (Tweet tweet : tweets) {
            long createdAt = tweet.getCreatedAt().getTime();
            long hoursSinceCreation = (currentTime - createdAt) / HOUR_IN_MILLIS;
            if (hoursSinceCreation < numberOfHours) {
                frequencyByHours[(int) hoursSinceCreation]++;
            }
        }
        return frequencyByHours;
    }
}
