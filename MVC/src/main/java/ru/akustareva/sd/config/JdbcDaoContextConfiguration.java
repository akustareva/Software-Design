package ru.akustareva.sd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.akustareva.sd.dao.ToDoDao;
import ru.akustareva.sd.dao.ToDoJdbcDao;

import javax.sql.DataSource;

@Configuration
public class JdbcDaoContextConfiguration {

    @Bean
    public ToDoDao listJdbcDao(DataSource dataSource) {
        return new ToDoJdbcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todolist.db");
        dataSource.setUsername("anna");
        dataSource.setPassword("anna");
        return dataSource;
    }
}
