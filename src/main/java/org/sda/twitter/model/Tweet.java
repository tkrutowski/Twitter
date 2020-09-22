package org.sda.twitter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Tweet {
    private String message;
    private int authorId;
    private LocalDateTime publishDate;

    public Tweet(String message, int authorId, LocalDateTime publishDate) {
        this.message = message;
        this.authorId = authorId;
        this.publishDate = publishDate;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return authorId == tweet.authorId &&
                message.equals(tweet.message) &&
                publishDate.equals(tweet.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, authorId, publishDate);
    }
}
