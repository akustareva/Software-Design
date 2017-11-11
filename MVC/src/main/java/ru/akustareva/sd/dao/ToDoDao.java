package ru.akustareva.sd.dao;

import ru.akustareva.sd.model.Business;
import ru.akustareva.sd.model.ToDoList;

import java.util.List;

public interface ToDoDao {
    int addToDOList(ToDoList toDoList);
    List<ToDoList> getAllToDoLists();
    ToDoList getListById(int id);

    int addBusinessToList(Business business);
    List<Business> getAllListBusiness(ToDoList list);
}
