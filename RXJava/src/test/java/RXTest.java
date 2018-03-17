import com.mongodb.rx.client.*;
import db.ReactiveMongoDriver;
import db.entities.User;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.*;
import rx.internal.util.ScalarSynchronousObservable;
import server.Methods;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.when;

/**
 * Created by Anna on 16.03.2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReactiveMongoDriver.class })
@SuppressStaticInitializationFor("db.ReactiveMongoDriver")
public class RXTest {

    @Test
    public void addUserCorrectnessTest() throws IOException {
        PowerMockito.mockStatic(ReactiveMongoDriver.class);
        when(ReactiveMongoDriver.createUser(Mockito.anyObject())).thenReturn(Success.SUCCESS);
        Map<String, List<String>> params = new HashMap<>();
        params.put("id", Collections.singletonList("1"));
        rx.Observable<String> res =  Methods.addUser(params);
        Assert.assertEquals("Please fill in field(s) name, currency", ((ScalarSynchronousObservable) res).get());
        params.put("name", Collections.singletonList("Test"));
        res =  Methods.addUser(params);
        Assert.assertEquals("Please fill in field(s) currency", ((ScalarSynchronousObservable) res).get());
        params.put("currency", Collections.singletonList("rub"));
        res =  Methods.addUser(params);
        Assert.assertTrue(((ScalarSynchronousObservable) res).get().toString().startsWith("NEW USER SUCCESSFULLY ADDED"));
    }

    @Test
    public void addGoodCorrectnessTest() throws IOException {
        PowerMockito.mockStatic(ReactiveMongoDriver.class);
        when(ReactiveMongoDriver.addGoods(Mockito.anyObject())).thenReturn(Success.SUCCESS);
        Map<String, List<String>> params = new HashMap<>();
        params.put("id", Collections.singletonList("1"));
        params.put("name", Collections.singletonList("Test"));
        rx.Observable<String> res =  Methods.addGood(params);
        Assert.assertEquals("Please fill in field(s) eur, usd, rub", ((ScalarSynchronousObservable) res).get());
        params.put("eur", Collections.singletonList("5"));
        params.put("usd", Collections.singletonList("8"));
        params.put("rub", Collections.singletonList("100"));
        res =  Methods.addGood(params);
        Assert.assertTrue(((ScalarSynchronousObservable) res).get().toString().startsWith("NEW GOOD SUCCESSFULLY ADDED"));
    }
}
