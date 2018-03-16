package db;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.Success;
import db.entities.Good;
import db.entities.User;
import rx.Observable;

import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Anna on 16.03.2018.
 */
public class ReactiveMongoDriver {
    private static MongoClient client = createMongoClient();

    private static MongoClient createMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    public static Success createUser(User user) {
        return client.getDatabase("rx").getCollection("user")
                .insertOne(user.getDocument()).timeout(10, TimeUnit.SECONDS).toBlocking().single();
    }

    public static Success addGoods(Good good) {
        return client.getDatabase("rx").getCollection("goods")
                .insertOne(good.getDocument()).timeout(10, TimeUnit.SECONDS).toBlocking().single();
    }

    public static Observable<String> getAllGoods(Integer id) {
        return findCurrencyByUserId(id).flatMap(currency -> client.getDatabase("rx").getCollection("goods").find()
                .toObservable().map(d -> new Good(d).toString(currency)).reduce((str1, str2) -> str1 + "\n" + str2));
    }

    private static Observable<String> findCurrencyByUserId(Integer id) {
        return client.getDatabase("rx").getCollection("user")
                .find(eq("id", id)).first().map(d -> new User(d).getCurrency());
    }
}
