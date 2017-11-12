package ru.akustareva.sd.dao;

import ru.akustareva.sd.model.Business;
import ru.akustareva.sd.model.ToDoList;

import java.util.List;

public interface ToDoDao {
    int addToDoList(ToDoList toDoList);
    List<ToDoList> getAllToDoLists();
    int deleteToDoListById(int id);
    ToDoList getListById(int id);

    int addBusinessToList(Business business);
    List getAllListBusiness(ToDoList list);
    int setBusinessDoneStatus(int isDone, int id);
    Business getBusinessById(int id);
}
