package org.sda.twitter.database.dao;

import org.sda.twitter.database.configuration.DatasourceConfiguration;
import org.sda.twitter.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowersDao {

    private DatasourceConfiguration datasourceConfiguration;

    public FollowersDao() {
        this.datasourceConfiguration = DatasourceConfiguration.getInstance();
    }


    public List<Integer> findFollowedByUserId(int userId) {
        List<Integer> followerList = new ArrayList<>();
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("select followed_id from followers where follower_id=?")){
            statement.setInt(1,userId);
                 ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                    followerList.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return followerList;
    }

    public boolean add(int followerId , int followedId) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into followers VALUES (?,?)")) {
            statement.setInt(1, followerId);
            statement.setInt(2, followedId);
            int update = statement.executeUpdate();
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean remove(int followerId , int followedId) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from followers where follower_id =? and followed_id=?")) {
            statement.setInt(1, followerId);
            statement.setInt(2, followedId);
            int update = statement.executeUpdate();
            return update > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
