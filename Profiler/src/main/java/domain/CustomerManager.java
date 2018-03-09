package domain;

/**
 * Created by Anna on 03.03.2018.
 */
public interface CustomerManager {
    int addCustomer(Customer customer);
    Customer findCustomer(int id);
}
