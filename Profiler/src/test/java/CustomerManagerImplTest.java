import dao.CustomerInMemoryDao;
import domain.Customer;
import domain.CustomerManager;
import domain.CustomerManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Anna on 03.03.2018.
 */
public class CustomerManagerImplTest {
    private CustomerManager customerManager;

    @Before
    public void init() {
        customerManager = new CustomerManagerImpl(new CustomerInMemoryDao());
    }

    @Test
    public void correctnessTest() {
        int N = 1000;
        for (int i = 0; i < N; i++) {
            int id = customerManager.addCustomer(new Customer(Integer.toString(i)));
            Assert.assertEquals(i, id);
        }
        for (int i = 0; i < N; i++) {
            Customer customer = customerManager.findCustomer(i);
            Assert.assertEquals(Integer.toString(i), customer.getName());
        }
    }

    @After
    public void fin() {
        customerManager = null;
    }
}
