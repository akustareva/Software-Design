package twitter.http;

import com.xebialabs.restito.server.StubServer;
import oauth.signpost.exception.OAuthException;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.UncheckedIOException;
import java.util.function.Consumer;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.method;

public class UrlReaderWIthStubServerTest {
    private static final int PORT = 1234;
    private static final String PING = "/ping";
    private static final String SOURCE_URL = "http://localhost:" + PORT + PING;
    private final UrlReader urlReader = new UrlReader();

    @Test
    public void readAsTextTest() {
        String successResult = "success";
        withStubServer(PORT, s -> {
            whenHttp(s)
                    .match(method(Method.GET).startsWithUri(PING))
                    .then(stringContent(successResult));
            String result = null;
            try {
                result = urlReader.readAsText(SOURCE_URL);
            } catch (OAuthException e) {
                e.printStackTrace();
                return;
            }
            Assert.assertEquals(successResult, result);
        });
    }

    @Test(expected = UncheckedIOException.class)
    public void readAsTextWithNotFoundError() {
        withStubServer(PORT, s -> {
            whenHttp(s)
                    .match(method(Method.GET).startsWithUri(PING))
                    .then(status(HttpStatus.NOT_FOUND_404));
            try {
                urlReader.readAsText(SOURCE_URL);
            } catch (OAuthException e) {
                e.printStackTrace();
            }
        });
    }

    private void withStubServer(int port, Consumer<StubServer> callback) {
        StubServer stubServer = null;
        try {
            stubServer = new StubServer(port).run();
            callback.accept(stubServer);
        } finally {
            if (stubServer != null) {
                stubServer.stop();
            }
        }
    }
}
