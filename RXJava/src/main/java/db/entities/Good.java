package db.entities;

import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 16.03.2018.
 */
public class Good {
    private static final String EUR = "eur";
    private static final String USD = "usd";
    private static final String RUB = "rub";
    private int id;
    private String name;
    private Map<String, String> currencyMap;

    public Good(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), doc.getString(EUR), doc.getString(USD), doc.getString(RUB));
    }

    public Good(int id, String name, String eur, String usd, String rub) {
        this.id = id;
        this.name = name;
        this.currencyMap = new HashMap<>();
        currencyMap.put(EUR, eur);
        currencyMap.put(USD, usd);
        currencyMap.put(RUB, rub);
    }

    public Document getDocument() {
        return new Document("id", id).append("name", name).append(EUR, currencyMap.get(EUR))
                .append(USD, currencyMap.get(USD)).append(RUB, currencyMap.get(RUB));
    }

    public String toString(String currency) {
        String price = "cost " + currency + "=" + currencyMap.get(currency);
        return "Good #" + id + ", name is " + name + ", " + price;
    }

    @Override
    public String toString() {
        StringBuilder pricesBuilder = new StringBuilder();
        for (Map.Entry<String, String> price: currencyMap.entrySet()) {
            pricesBuilder.append(price.getKey()).append("=").append(price.getValue()).append(", ");
        }
        String prices = pricesBuilder.toString();
        return "Good #" + id + ", name is " + name + ", " + prices.substring(0, prices.length() - 2);
    }
}
