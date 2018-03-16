package db.entities;

import org.bson.Document;

/**
 * Created by Anna on 16.03.2018.
 */
public class User {
    private int id;
    private String name;
    private String currency;

    public User(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), doc.getString("currency"));
    }

    public User(int id, String name, String currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public Document getDocument() {
        return new Document("id", id).append("name", name).append("currency", currency);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "User #" + id + ", name is " + name + ", currency=" + currency;
    }
}
