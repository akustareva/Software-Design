package twitter.http;

import oauth.signpost.exception.OAuthException;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import twitter.rule.HostReachableRule;

@HostReachableRule.HostReachable("api.twitter.com")
public class UrlReaderTest {
    @ClassRule
    public static final HostReachableRule rule = new HostReachableRule();

    @Test
    public void readTest() throws OAuthException {
        String result = new UrlReader().readAsText("https://api.twitter.com/1.1/search/tweets.json?q=%2323868jvjfje3");
        Assert.assertTrue(result.length() > 0);
    }
}
