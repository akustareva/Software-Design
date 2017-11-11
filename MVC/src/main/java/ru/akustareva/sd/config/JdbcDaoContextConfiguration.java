package ru.akustareva.sd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.akustareva.sd.dao.ToDoJdbcDao;

import javax.sql.DataSource;

public class JdbcDaoContextConfiguration {

    @Bean
    public ToDoJdbcDao productJdbcDao(DataSource dataSource) {
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
