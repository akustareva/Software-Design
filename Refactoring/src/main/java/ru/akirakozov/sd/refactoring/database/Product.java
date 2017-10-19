package ru.akirakozov.sd.refactoring.database;

public class Product implements Comparable<Product> {
    private String name;
    private int price;

    public Product(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product product) {
        if (price != product.getPrice()) {
            return price < product.getPrice() ? -1 : 1;
        }
        return name.compareTo(product.getName());
    }
}
