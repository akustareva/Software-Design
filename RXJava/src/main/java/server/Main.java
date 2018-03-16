package server;

import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;

import java.util.List;
import java.util.Map;

/**
 * Created by Anna on 16.03.2018.
 */
public class Main {
    private static String UNKNOWN_COMMAND = "Unknown command";

    public static void main(final String[] args) {
        HttpServer.newServer(8081).start((request, response) -> {
            String method = request.getDecodedPath().substring(1);
            Map<String, List<String>> params = request.getQueryParameters();
            if ("addUser".equals(method)) {
                return response.writeString(Methods.addUser(params));
            }
            if ("addGood".equals(method)) {
                return response.writeString(Methods.addGood(params));
            }
            if ("getGoods".equals(method)) {
                return response.writeString(Methods.getGoods(params));
            }
            return response.writeString(Observable.just(UNKNOWN_COMMAND));
        }).awaitShutdown();
    }
}
