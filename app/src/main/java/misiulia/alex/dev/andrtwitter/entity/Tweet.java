package misiulia.alex.dev.andrtwitter.entity;

import static misiulia.alex.dev.andrtwitter.entity.Entities.ENTITIES;
import static misiulia.alex.dev.andrtwitter.entity.User.USER;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Tweet {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private Long id;

    @SerializedName("full_text")
    private String text;

    @SerializedName("retweet_count")
    private Long retweetCount;

    @SerializedName("favorite_count")
    private Long favouriteCount;

    @SerializedName(USER)
    private User user;

    @SerializedName(ENTITIES)
    private Entities entities;

    @Nullable
    public String getPhotoUrl() {
        return entities.getFirstMedia() != null ? entities.getFirstMedia().getMediaUrl() : null;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getRetweetCount() {
        return retweetCount;
    }

    public Long getFavouriteCount() {
        return favouriteCount;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (createdAt != null ? !createdAt.equals(tweet.createdAt) : tweet.createdAt != null) {
            return false;
        }
        if (!id.equals(tweet.id)) return false;
        if (text != null ? !text.equals(tweet.text) : tweet.text != null) return false;
        if (retweetCount != null ? !retweetCount.equals(tweet.retweetCount) : tweet.retweetCount != null) {
            return false;
        }
        if (favouriteCount != null ? !favouriteCount.equals(tweet.favouriteCount) : tweet.favouriteCount != null) {
            return false;
        }
        if (user != null ? !user.equals(tweet.user) : tweet.user != null) return false;
        return entities != null ? entities.equals(tweet.entities) : tweet.entities == null;
    }

    @Override
    public int hashCode() {
        int result = createdAt != null ? createdAt.hashCode() : 0;
        result = 31 * result + id.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (retweetCount != null ? retweetCount.hashCode() : 0);
        result = 31 * result + (favouriteCount != null ? favouriteCount.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (entities != null ? entities.hashCode() : 0);
        return result;
    }
}
