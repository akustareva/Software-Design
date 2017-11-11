package ru.akustareva.sd.model;

public class Business {
    private int listId;
    private String description;

    public Business() {}

    public Business(int listId, String description) {
        this.listId = listId;
        this.description = description;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
