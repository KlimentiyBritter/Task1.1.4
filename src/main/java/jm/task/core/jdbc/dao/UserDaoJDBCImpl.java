package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    private int idUser = 1;
    Util util = new Util();
    Connection connection = util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String mySqlReq = "CREATE TABLE IF NOT EXISTS user(" +
                "id LONG," +
                "name VARCHAR(45)," +
                "lastName VARCHAR(45)," +
                "age INT)";
        try (PreparedStatement preStatement = connection.prepareStatement(mySqlReq)) {
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String mySqlReq = "DROP TABLE IF EXISTS user";
        idUser = 1;
        try (PreparedStatement preStatement = connection.prepareStatement(mySqlReq)) {
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
//        PreparedStatement preStatement = null;
        String mySqlReq = "INSERT INTO user (id, name, lastName, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preStatement = connection.prepareStatement(mySqlReq)){

//        try (PreparedStatement preStatement = util.getConnection().prepareStatement(mySqlReq)) {
            //            PreparedStatement preStatement = util.getConnection().prepareStatement(mySqlReq)
            preStatement.setLong(1, idUser);
            preStatement.setString(2, name);
            preStatement.setString(3, lastName);
            preStatement.setByte(4, age);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idUser++;
    }

    public void removeUserById(long id) {
        String mySqlReq = "DELETE FROM user WHERE id=?";
        try (PreparedStatement preStatement = connection.prepareStatement(mySqlReq)) {
            preStatement.setLong(1, id);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String mySqlReq = "SELECT id, name, lastName, age FROM user";
//        (PreparedStatement preStatement = util.getConnection().prepareStatement(mySqlReq))
//        (PreparedStatement preStatement = (PreparedStatement) util.getConnection().createStatement())

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(mySqlReq);
            while(resultSet.next()) {
                User arrayUser = new User();
                arrayUser.setId(resultSet.getLong("id"));
                arrayUser.setName(resultSet.getString("name"));
                arrayUser.setLastName(resultSet.getString("lastName"));
                arrayUser.setAge(resultSet.getByte("age"));
                userList.add(arrayUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String mySqlReq = "DELETE FROM user";
        try (PreparedStatement preStatement = connection.prepareStatement(mySqlReq)) {
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idUser = 1;
    }
}
