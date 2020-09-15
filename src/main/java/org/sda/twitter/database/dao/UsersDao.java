package org.sda.twitter.database.dao;


import org.sda.twitter.database.configuration.DatasourceConfiguration;
import org.sda.twitter.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    private DatasourceConfiguration datasourceConfiguration;

    public UsersDao() {
        this.datasourceConfiguration = DatasourceConfiguration.getInstance();
    }

    public boolean createUser(User user) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into users (login,password) VALUES (?,?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            int update = statement.executeUpdate();
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public int hasUser(String login, String password) {
        int id=-1;
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM users WHERE login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                id=resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public List<User> findAll(){
        List<User> userList=new ArrayList<>();
        String sql = "select * from users";
        try(Connection connection=datasourceConfiguration.getConnection();
        Statement statement=connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                userList.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
