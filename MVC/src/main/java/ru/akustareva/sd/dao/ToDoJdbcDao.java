package ru.akustareva.sd.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.lang.Nullable;
import ru.akustareva.sd.model.Business;
import ru.akustareva.sd.model.ToDoList;

import javax.sql.DataSource;
import java.util.List;

public class ToDoJdbcDao extends JdbcDaoSupport implements ToDoDao {

    public ToDoJdbcDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
        initDatabase();
    }

    @Override
    public int addToDoList(ToDoList toDoList) {
        String sql = "INSERT INTO ToDoLists (name, description) VALUES (?, ?)";
        return updateByQuery(sql, toDoList.getName(), toDoList.getDescription());
    }

    @Override
    public List<ToDoList> getAllToDoLists() {
        String sql = "SELECT * FROM ToDoLists";
        return getListsByQuery(sql);
    }

    @Override
    public int deleteToDoListById(int id) {
        String sql = "DELETE FROM ToDoLists WHERE id = ?";
        return updateByQuery(sql, id);
    }


    @Override
    public ToDoList getListById(int id) {
        String sql = "SELECT * FROM ToDoLists WHERE id = ?";
        List<ToDoList> lists = getListsByQuery(sql, id);
        if (!lists.isEmpty()) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    public int addBusinessToList(Business business) {
        String sql = "INSERT INTO Business (listId, description, isDone) VALUES (?, ?, 0)";
        return updateByQuery(sql, business.getListId(), business.getDescription());
    }

    @Override
    public List<Business> getAllListBusinesses(ToDoList list) {
        String sql = "SELECT * FROM Business WHERE listId = ?";
        return getBusinessesByQuery(sql, list.getId());
    }

    @Override
    public int setBusinessDoneStatus(int isDone, int id) {
        String sql = "UPDATE Business SET isDone = ? WHERE id = ?";
        return updateByQuery(sql, isDone, id);
    }

    public Business getBusinessById(int id) {
        String sql = "SELECT * FROM Business WHERE id = ?";
        List<Business> businesses = getBusinessesByQuery(sql, id);
        if (!businesses.isEmpty()) {
            return businesses.get(0);
        }
        return null;
    }

    private List<ToDoList> getListsByQuery(String sql, @Nullable Object... args) {
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ToDoList.class), args);
    }

    private List<Business> getBusinessesByQuery(String sql, @Nullable Object... args) {
        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Business.class), args);
    }

    private int updateByQuery(String sql, @Nullable Object... args) {
        return getJdbcTemplate().update(sql, args);
    }

    private int initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS ToDoLists(" +
                        "id             INTEGER   PRIMARY KEY AUTOINCREMENT," +
                        "name           TEXT      NOT NULL," +
                        "description    TEXT      NOT NULL" +
                     ");" +
                     "CREATE TABLE IF NOT EXISTS Business(" +
                        "id             INTEGER   PRIMARY KEY AUTOINCREMENT," +
                        "listId         INTEGER   NOT NULL," +
                        "description    TEXT      NOT NULL," +
                        "isDone         INTEGER   NOT NULL" +
                     ");";
        return getJdbcTemplate().update(sql);
    }
}
