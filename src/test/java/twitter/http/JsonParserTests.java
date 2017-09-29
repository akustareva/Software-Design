package twitter.http;

import org.junit.Assert;
import org.junit.Test;
import twitter.Tweet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class JsonParserTests {
    private final static String testResponse =
            "{\"statuses\":" +
                "[{\"created_at\":\"Fri Sep 29 07:59:06 +0000 2017\",\"id\":913674481054384128,\"id_str\":\"913674481054384128\",\"text\":\"test7\n#23868jvjfje3\n#one_more #icandoit\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[6,19]},{\"text\":\"one_more\",\"indices\":[20,29]},{\"text\":\"icandoit\",\"indices\":[30,39]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}," +
                 "{\"created_at\":\"Fri Sep 29 07:43:42 +0000 2017\",\"id\":913670604951363584,\"id_str\":\"913670604951363584\",\"text\":\"test6\n#23868jvjfje3\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[6,19]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}," +
                 "{\"created_at\":\"Fri Sep 29 07:43:29 +0000 2017\",\"id\":913670551671070720,\"id_str\":\"913670551671070720\",\"text\":\"test5\n#23868jvjfje3\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[6,19]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}," +
                 "{\"created_at\":\"Thu Sep 28 20:43:17 +0000 2017\",\"id\":913504406179545089,\"id_str\":\"913504406179545089\",\"text\":\"test4\n#23868jvjfje3\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[6,19]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}," +
                 "{\"created_at\":\"Thu Sep 28 20:34:43 +0000 2017\",\"id\":913502253130055681,\"id_str\":\"913502253130055681\",\"text\":\"test3\n#23868jvjfje3\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[6,19]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}," +
                 "{\"created_at\":\"Thu Sep 28 19:27:02 +0000 2017\",\"id\":913485219952566272,\"id_str\":\"913485219952566272\",\"text\":\"test2\n#23868jvjfje3\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[6,19]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}," +
                 "{\"created_at\":\"Thu Sep 28 19:09:42 +0000 2017\",\"id\":913480854520963072,\"id_str\":\"913480854520963072\",\"text\":\"test1\n#23868jvjfje3\",\"truncated\":false,\"entities\":{\"hashtags\":[{\"text\":\"23868jvjfje3\",\"indices\":[5,18]}],\"symbols\":[],\"user_mentions\":[],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\\" rel=\\\"nofollow\\\"\\u003eTwitter Web Client\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":910950825723392001,\"id_str\":\"910950825723392001\",\"name\":\"Anna Kustareva\",\"screen_name\":\"anna_kustareva\",\"location\":\"\\u0421\\u0430\\u043d\\u043a\\u0442-\\u041f\\u0435\\u0442\\u0435\\u0440\\u0431\\u0443\\u0440\\u0433, \\u0420\\u043e\\u0441\\u0441\\u0438\\u044f\",\"description\":\"\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":0,\"friends_count\":0,\"listed_count\":0,\"created_at\":\"Thu Sep 21 19:36:16 +0000 2017\",\"favourites_count\":1,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":9,\"lang\":\"ru\",\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_image_url_https\":\"https:\\/\\/abs.twimg.com\\/sticky\\/default_profile_images\\/default_profile_normal.png\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":true,\"following\":false,\"follow_request_sent\":false,\"notifications\":false,\"translator_type\":\"none\"},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"}" +
                "]," +
            "\"search_metadata\":{\"completed_in\":0.028,\"max_id\":913674481054384128,\"max_id_str\":\"913674481054384128\",\"query\":\"%2323868jvjfje3\",\"refresh_url\":\"?since_id=913674481054384128&q=%2323868jvjfje3&include_entities=1\",\"count\":15,\"since_id\":0,\"since_id_str\":\"0\"}}";

    @Test
    public void parseResponse() throws ParseException {
        JsonParser jsonParser = new JsonParser();
        List<Tweet> tweets  = jsonParser.parseResponse(testResponse);
        Assert.assertTrue(tweets.size() == 7);
        for (Tweet tweet : tweets) {
            Assert.assertEquals(tweet.getUserId(), "910950825723392001");
            Assert.assertEquals(tweet.getUserName(), "Anna Kustareva");
        }
        DateFormat format = new SimpleDateFormat("EEE MMM dd kk:mm:ss Z yyyy", Locale.ENGLISH);
        Assert.assertEquals(tweets.get(0).getCreatedAt(), format.parse("Fri Sep 29 07:59:06 +0000 2017"));
        Assert.assertEquals(tweets.get(1).getCreatedAt(), format.parse("Fri Sep 29 07:43:42 +0000 2017"));
        Assert.assertEquals(tweets.get(2).getCreatedAt(), format.parse("Fri Sep 29 07:43:29 +0000 2017"));
        Assert.assertEquals(tweets.get(3).getCreatedAt(), format.parse("Thu Sep 28 20:43:17 +0000 2017"));
        Assert.assertEquals(tweets.get(4).getCreatedAt(), format.parse("Thu Sep 28 20:34:43 +0000 2017"));
        Assert.assertEquals(tweets.get(5).getCreatedAt(), format.parse("Thu Sep 28 19:27:02 +0000 2017"));
        Assert.assertEquals(tweets.get(6).getCreatedAt(), format.parse("Thu Sep 28 19:09:42 +0000 2017"));
    }
}
