package server;

import com.mongodb.rx.client.Success;
import db.ReactiveMongoDriver;
import db.entities.Good;
import db.entities.User;
import rx.Observable;

import java.util.*;

/**
 * Created by Anna on 16.03.2018.
 */
public class Methods {
    private static final List<String> USER_PARAMS = Arrays.asList("id", "name", "currency");
    private static final List<String> GOOD_PARAMS = Arrays.asList("id", "name", "eur", "usd", "rub");
    private static final List<String> GET_GOODS_PARAMS = Collections.singletonList("user_id");

    public static Observable<String> addUser(Map<String, List<String>> params) {
        String error = check(params, USER_PARAMS);
        if (!error.endsWith("field(s) ")) {
            return Observable.just(error);
        }
        Integer id = Integer.parseInt(params.get("id").get(0));
        String name = params.get("name").get(0);
        String currency = params.get("currency").get(0);
        User newUser = new User(id, name, currency);
        if (ReactiveMongoDriver.createUser(newUser) == Success.SUCCESS) {
            return Observable.just("NEW USER SUCCESSFULLY ADDED\n" + newUser);
        } else {
            return Observable.just("FAILED");
        }
    }

    public static Observable<String> addGood(Map<String, List<String>> params) {
        String error = check(params, GOOD_PARAMS);
        if (error.contains(",")) {
            return Observable.just(error);
        }
        Integer id = Integer.parseInt(params.get("id").get(0));
        String name = params.get("name").get(0);
        String eur = params.get("eur").get(0);
        String usd = params.get("usd").get(0);
        String rub = params.get("rub").get(0);
        Good newGood = new Good(id, name, eur, usd, rub);
        if (ReactiveMongoDriver.addGoods(newGood) == Success.SUCCESS) {
            return Observable.just("NEW GOOD SUCCESSFULLY ADDED\n" + newGood);
        } else {
            return Observable.just("FAILED");
        }
    }

    public static Observable<String> getGoods(Map<String, List<String>> params) {
        String error = check(params, GET_GOODS_PARAMS);
        if (error.contains(",")) {
            return Observable.just(error);
        }
        Integer id = Integer.parseInt(params.get("user_id").get(0));
        return ReactiveMongoDriver.getAllGoods(id);
    }

    private static String check(Map<String, List<String>> params, List<String> values) {
        StringJoiner error = new StringJoiner(", ", "Please fill in field(s) ", "");
        values.stream().filter(value -> !params.containsKey(value)).forEach(error::add);
        return error.toString();
    }
}
