package actors;

import akka.actor.UntypedActor;
import entities.Response;
import seachers.Searcher;

/**
 * Created by Anna on 09.03.2018.
 */
public class ChildActor extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Searcher) {
            Searcher searcher = (Searcher) o;
            getSender().tell(new Response(searcher.getSearcherName(), searcher.search()), self());
        }
    }
}
