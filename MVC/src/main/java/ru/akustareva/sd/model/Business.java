package ru.akustareva.sd.model;

public class Business {
    private int id;
    private int listId;
    private String description;
    private int isDone;

    public Business() {}

    public Business(int id, int listId, String description, int isDone) {
        this.id = id;
        this.listId = listId;
        this.description = description;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
}
