package misiulia.alex.dev.andrtwitter.entity;

import static misiulia.alex.dev.andrtwitter.Media.MEDIA;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import misiulia.alex.dev.andrtwitter.Media;

public class Entities {
    public static final String ENTITIES = "entities";

    @SerializedName(MEDIA)
    private List<Media> mediaList;

    public List<Media> getMediaList() {
        return mediaList;
    }

    public Media getFirstMedia() {
        return mediaList != null && mediaList.size() > 0 ? mediaList.get(0) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entities entities = (Entities) o;

        return mediaList != null ? mediaList.equals(entities.mediaList) : entities.mediaList == null;
    }

    @Override
    public int hashCode() {
        return mediaList != null ? mediaList.hashCode() : 0;
    }
}
