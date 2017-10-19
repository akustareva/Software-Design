package ru.akirakozov.sd.refactoring.database;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class SqlManagerTest {
    private SqlManager sqlManager = SqlManager.getInstance();

    @Test
    public void getProductsCountTest() {
        List<Product> products = sqlManager.getAllProducts();
        int productsCount = sqlManager.getProductsCount();
        Assert.assertTrue(productsCount == products.size());
    }

    @Test
    public void getPricesSumTest() {
        List<Product> products = sqlManager.getAllProducts();
        int pricesSum = sqlManager.getPricesSum();
        int actualSum = 0;
        for (Product product : products) {
            actualSum += product.getPrice();
        }
        Assert.assertTrue(pricesSum == actualSum);
    }

    @Test
    public void getMostExpensiveProductTest() {
        List<Product> products = sqlManager.getAllProducts();
        Product mostExpansiveProduct = sqlManager.getMostExpensiveProduct();
        Product actualMostExpensive = Collections.max(products);
        Assert.assertTrue(mostExpansiveProduct.getPrice() == actualMostExpensive.getPrice());
    }

    @Test
    public void getCheapestProductTest() {
        List<Product> products = sqlManager.getAllProducts();
        Product cheapestProduct = sqlManager.getCheapestProduct();
        Product actualCheapest = Collections.min(products);
        Assert.assertTrue(cheapestProduct.getPrice() == actualCheapest.getPrice());
    }
}
