package dao;

import domain.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anna on 03.03.2018.
 */
public class CustomerInMemoryDao {
    private static AtomicInteger currentId = new AtomicInteger(0);
    private Map<Integer, Customer> customers = new HashMap<>();

    public int addCustomer(Customer customer) {
        int id = currentId.getAndIncrement();
        customers.put(id, customer);
        return id;
    }

    public Customer findCustomer(int id) {
        if (customers.containsKey(id)) {
            return customers.get(id);
        } else {
            throw new EntityNotFoundException("Customer couldn't be found by id: " + id);
        }
    }
}
