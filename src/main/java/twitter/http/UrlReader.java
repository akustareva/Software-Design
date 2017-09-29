package twitter.http;

import oauth.signpost.exception.OAuthException;
import twitter.TwitterClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class UrlReader {

    public String readAsText(String sourceUrl) throws OAuthException {
        try {
            HttpURLConnection connection = (HttpURLConnection) toUrl(sourceUrl).openConnection();
            TwitterClient.getConsumer().sign(connection);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = String.join("\n", bufferedReader.lines().collect(Collectors.toList()));
            bufferedReader.close();
            return response;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private URL toUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed url: " + url);
        }
    }
}
