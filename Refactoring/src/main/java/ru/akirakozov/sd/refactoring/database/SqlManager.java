package ru.akirakozov.sd.refactoring.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class SqlManager {
    private static SqlManager instance;
    private Connection connection;
    private Statement stmt;
    private static final String GET_MOST_EXPENSIVE_PRODUCT =
            "SELECT * FROM Product ORDER BY price DESC LIMIT 1";
    private static final String GET_CHEAPEST_PRODUCT =
            "SELECT * FROM Product ORDER BY price LIMIT 1";
    private static final String GET_PRICES_SUM =
            "SELECT SUM(price) FROM Product";
    private static final String GET_PRODUCTS_COUNT =
            "SELECT COUNT(*) FROM Product";
    private static final String GET_ALL_PRODUCTS =
            "SELECT * FROM Product";
    private static final String ADD_PRODUCT =
            "INSERT INTO Product(name, price) VALUES (\"%s\", %d)";

    private SqlManager() {}

    public static SqlManager getInstance() {
        if (instance == null) {
            instance = new SqlManager();
        }
        return instance;
    }

    public void initDatabase() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/database/create-tables.sql"));
        String createTablesQuery = String.join(" ", bufferedReader.lines().collect(Collectors.toList()));
        executeUpdate(createTablesQuery);
    }

    public void addProduct(String name, long prise) {
        executeUpdate(String.format(ADD_PRODUCT, name, prise));
    }

    public Product getMostExpensiveProduct() {
        return getProduct(GET_MOST_EXPENSIVE_PRODUCT);
    }

    public Product getCheapestProduct() {
        return getProduct(GET_CHEAPEST_PRODUCT);
    }

    public int getPricesSum() {
        return getAggregateFuncResult(GET_PRICES_SUM);
    }

    public int getProductsCount() {
        return getAggregateFuncResult(GET_PRODUCTS_COUNT);
    }

    public List<Product> getAllProducts() {
        try {
            ResultSet rs = executeQuery(GET_ALL_PRODUCTS);
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(new Product(rs.getString("name"), rs.getInt("price")));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    private int getAggregateFuncResult(String query) {
        ResultSet rs = executeQuery(query);
        try {
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    private Product getProduct(String query) {
        ResultSet rs = executeQuery(query);
        try {
            return new Product(rs.getString("name"), rs.getInt("price"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    private void executeUpdate(String query) {
        execute(query, (statement, sqlQuery) -> {
            try {
                return statement.executeUpdate(sqlQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close();
            }
        });
    }

    private ResultSet executeQuery(String query) {
        return execute(query, (statement, sqlQuery) -> {
            try {
                return statement.executeQuery(sqlQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private <T> T execute(String query, BiFunction<Statement, String, T> function) {
        String address = "jdbc:sqlite:test.db";
        try {
            connection = DriverManager.getConnection(address);
            stmt = connection.createStatement();
            return function.apply(stmt, query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void close() {
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
