package ru.akustareva.sd.dao;

import ru.akustareva.sd.model.Business;
import ru.akustareva.sd.model.ToDoList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ToDoInMemoryDao implements ToDoDao {
    private final AtomicInteger lastListId = new AtomicInteger(0);
    private final AtomicInteger lastBusinessId = new AtomicInteger(0);
    private List<ToDoList> toDoLists = new CopyOnWriteArrayList<>();
    private List<Business> businesses = new CopyOnWriteArrayList<>();

    @Override
    public int addToDoList(ToDoList toDoList) {
        int id = lastListId.incrementAndGet();
        toDoList.setId(id);
        toDoLists.add(toDoList);
        return 1;
    }

    @Override
    public List<ToDoList> getAllToDoLists() {
        return new ArrayList<>(toDoLists);
    }

    @Override
    public int deleteToDoListById(int id) {
        for (int i = 0; i < toDoLists.size(); i++) {
            if (toDoLists.get(i).getId() == id) {
                toDoLists.remove(i);
                break;
            }
        }
        return 1;
    }

    @Override
    public ToDoList getListById(int id) {
        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getId() == id) {
                return toDoList;
            }
        }
        return null;
    }

    @Override
    public int addBusinessToList(Business business) {
        int id = lastBusinessId.incrementAndGet();
        business.setId(id);
        business.setIsDone(0);
        businesses.add(business);
        return 1;
    }

    @Override
    public synchronized List<Business> getAllListBusinesses(ToDoList list) {
        return businesses.stream().filter(business -> business.getListId() == list.getId()).collect(Collectors.toList());
    }

    @Override
    public int setBusinessDoneStatus(int isDone, int id) {
        businesses.stream().filter(business -> business.getId() == id).forEach(business -> {
            business.setIsDone(isDone);
        });
        return 1;
    }

    @Override
    public Business getBusinessById(int id) {
        for (Business business : businesses) {
            if (business.getId() == id) {
                return business;
            }
        }
        return null;
    }
}
