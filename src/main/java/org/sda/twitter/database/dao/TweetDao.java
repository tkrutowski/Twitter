package org.sda.twitter.database.dao;

import org.sda.twitter.database.configuration.DatasourceConfiguration;
import org.sda.twitter.model.Tweet;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    public List<Tweet> getFollowedTweet(List<Integer> followedUsers){
        List<Tweet> tweetList=new ArrayList<>();

        for (int idUser:followedUsers ) {
            //pobieranie po kolei do listy tweety kolejnych użytkowników
            tweetList.addAll(getTweets(idUser));
        }
        //posortowanie wg daty

        return tweetList;
    }

    private List<Tweet> getTweets(int idUser){
        List<Tweet> tweetList=new ArrayList<>();
        try (Connection connection= datasourceConfiguration.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from tweets where author_id = ?")){
            statement.setInt(1,idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                tweetList.add(new Tweet(resultSet.getString("message"),resultSet.getInt("author_id"), resultSet.getTimestamp("published").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tweetList;
    }
}
