import aop.LoggingExecutionTimeAspect;
import domain.Customer;
import domain.CustomerManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Created by Anna on 03.03.2018.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        CustomerManager customerManager = ctx.getBean(CustomerManager.class);
        int N = 5;
        for (int i = 0; i < N; i++) {
            customerManager.addCustomer(new Customer(Integer.toString(i)));
        }
        for (int i = 0; i < N; i++) {
            customerManager.findCustomer(i);
        }
        for (Map.Entry<String, Long> entry : LoggingExecutionTimeAspect.getSum().entrySet()) {
            String method = entry.getKey();
            Long cntOfCalls = LoggingExecutionTimeAspect.getCnt().get(method);
            System.out.println(method + ":");
            System.out.println("\tNumber of calls: " + cntOfCalls + ", Total time: " + entry.getValue() +
                    " ns, Average time: " + entry.getValue() * 1.0 / cntOfCalls + " ns");
        }
    }
}
