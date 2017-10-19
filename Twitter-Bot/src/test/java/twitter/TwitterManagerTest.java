package twitter;

import oauth.signpost.exception.OAuthException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static twitter.TwitterManager.HOUR_IN_MILLIS;

public class TwitterManagerTest {
    private TwitterManager twitterManager;
    @Mock
    private TwitterClient twitterClient;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        twitterManager = new TwitterManager(twitterClient);
    }

    @Test
    public void getHashtagFrequencyTest() throws OAuthException, ParseException {
        String hashtag = "#23868jvjfje3";
        int numberOfHours = 12;
        when(twitterClient.getHashtagInfo(hashtag))
                .thenReturn(createAnswer());
        int[] frequencyByHours = twitterManager.getHashtagFrequency(hashtag, numberOfHours);
        Assert.assertTrue(frequencyByHours.length == numberOfHours);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 0, 1}, frequencyByHours);
    }

    private List<Tweet> createAnswer() throws ParseException {
        String userId = "910950825723392001";
        String userName = "Anna Kustareva";
        return Arrays.asList(
            new Tweet(new Date(System.currentTimeMillis()), "test7\n#23868jvjfje3\n#one_more #icandoit",userId, userName),
            new Tweet(new Date(System.currentTimeMillis() - HOUR_IN_MILLIS), "test6\n#23868jvjfje3", userId, userName),
            new Tweet(new Date(System.currentTimeMillis() - 2 * HOUR_IN_MILLIS), "test5\n#23868jvjfje3", userId, userName),
            new Tweet(new Date(System.currentTimeMillis() - 3 * HOUR_IN_MILLIS), "test4\n#23868jvjfje3", userId, userName),
            new Tweet(new Date(System.currentTimeMillis() - 8 * HOUR_IN_MILLIS), "test3\n#23868jvjfje3", userId, userName),
            new Tweet(new Date(System.currentTimeMillis() - 8 * HOUR_IN_MILLIS), "test2\n#23868jvjfje3", userId, userName),
            new Tweet(new Date(System.currentTimeMillis() - 11 * HOUR_IN_MILLIS), "test\n#23868jvjfje3", userId, userName)
        );
    }
}
