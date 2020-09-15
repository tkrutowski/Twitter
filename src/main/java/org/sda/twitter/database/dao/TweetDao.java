package org.sda.twitter.database.dao;

import org.sda.twitter.database.configuration.DatasourceConfiguration;
import org.sda.twitter.model.Tweet;

import java.sql.*;
import java.time.LocalDateTime;


public class TweetDao {
    private DatasourceConfiguration datasourceConfiguration;

    public TweetDao() {
        this.datasourceConfiguration = DatasourceConfiguration.getInstance();
    }

    public boolean publishTweet(Tweet tweet) {
        try (Connection connection = datasourceConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into tweets(author_id, message, published) values (?, ?, ?);")) {
            statement.setInt(1, tweet.getAuthorId());
            statement.setString(2, tweet.getMessage());
            statement.setTimestamp(3, Timestamp.valueOf(tweet.getPublishDate()));
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
