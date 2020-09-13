package org.sda.twitter.database.dao;


import org.sda.twitter.database.configuration.DatasourceConfiguration;
import org.sda.twitter.model.User;

import java.sql.*;
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

}
