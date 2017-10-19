package twitter;

import oauth.signpost.exception.OAuthException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import twitter.rule.HostReachableRule;

import java.text.ParseException;
import java.util.List;

@HostReachableRule.HostReachable(TwitterClient.HOST)
public class TwitterClientIntegrationTest {
    @ClassRule
    public static final HostReachableRule rule = new HostReachableRule();

    @Test
    public void getHashtagInfoTest() throws OAuthException, ParseException {
        TwitterClient twitterClient = new TwitterClient();
        List<Tweet> tweets = twitterClient.getHashtagInfo("#87sdlig86wejh");
        Assert.assertTrue(tweets != null);
    }
}
