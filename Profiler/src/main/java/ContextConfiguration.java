import aop.LoggingExecutionTimeAspect;
import dao.CustomerInMemoryDao;
import domain.CustomerManager;
import domain.CustomerManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Anna on 03.03.2018.
 */
@Configuration
@EnableAspectJAutoProxy
public class ContextConfiguration {

    @Bean
    public CustomerManager customerManager() {
        return new CustomerManagerImpl(new CustomerInMemoryDao());
    }

    @Bean
    public LoggingExecutionTimeAspect aspect() {
        return new LoggingExecutionTimeAspect();
    }
}
