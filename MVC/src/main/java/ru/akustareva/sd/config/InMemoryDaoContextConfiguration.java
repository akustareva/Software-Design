package ru.akustareva.sd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.akustareva.sd.dao.ToDoDao;
import ru.akustareva.sd.dao.ToDoInMemoryDao;

@Configuration
public class InMemoryDaoContextConfiguration {

    @Bean
    public ToDoDao listInMemoryDao() {
        return new ToDoInMemoryDao();
    }
}
