package actors;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import akka.routing.RoundRobinPool;
import entities.Response;
import entities.Result;
import scala.concurrent.duration.Duration;
import seachers.GoogleSearcher;
import seachers.Searcher;
import seachers.YandexSearcher;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 09.03.2018.
 */
public class MasterActor extends UntypedActor {
    private final ActorRef childRouter;
    private PrintWriter out;
    private String query;
    private static List<Class<? extends Searcher>> classes = new ArrayList<>();
    static {
        classes.add(GoogleSearcher.class);
        classes.add(YandexSearcher.class);
    }
    private static final int SEARCHERS_NUM = classes.size();
    private List<Response> responses = new ArrayList<>();

    public MasterActor(PrintWriter out, String query) {
        this.out = out;
        this.query = query;
        childRouter = getContext().actorOf(new RoundRobinPool(SEARCHERS_NUM).props(Props.create(ChildActor.class)), "childRouter");
        getContext().setReceiveTimeout(Duration.create("5 seconds"));
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o.equals("/start")) {
            for (int i = 0; i < SEARCHERS_NUM; i++) {
                Constructor constructor = classes.get(i).getConstructor(String.class);
                childRouter.tell(constructor.newInstance(query), getSelf());
            }
        } else if (o instanceof Response) {
            responses.add((Response) o);
            if (responses.size() == SEARCHERS_NUM) {
                for (Response response: responses) {
                    out.println("Got answer from " + response.getName());
                    for (Result result: response.getResults()) {
                        out.println(result);
                    }
                }
                out.close();
                throw new StopException();
            }
        }
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(false, DeciderBuilder
                .match(StopException.class, e -> OneForOneStrategy.stop())
                .build());
    }

    static class StopException extends RuntimeException {
        StopException() {
            super();
        }
    }
}
