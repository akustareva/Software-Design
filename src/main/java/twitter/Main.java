package twitter;

import oauth.signpost.exception.OAuthException;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Please, specify hashtag name and number of hours.");
            return;
        }
        String hashtag = args[0];
        int numberOfHours = Integer.parseInt(args[1]);
        TwitterClient twitterClient = new TwitterClient();
        try {
            int[] frequencyByHours = twitterClient.getHashtagFrequency(hashtag, numberOfHours);
            System.out.println("Count of tweets that were created ..");
            for (int i = 0; i < numberOfHours; i++) {
                System.out.println(i + " hours ago: " + frequencyByHours[i]);
            }
        } catch (IOException | OAuthException | ParseException e) {
            e.printStackTrace();
        }
    }
}
