package misiulia.alex.dev.andrtwitter;

import com.google.gson.annotations.SerializedName;

public class Media {
    public static final String MEDIA = "media";

    private Long id;

    @SerializedName("media_url")
    private String mediaUrl;


    public Long getId() {
        return id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        if (!id.equals(media.id)) return false;
        return mediaUrl.equals(media.mediaUrl);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + mediaUrl.hashCode();
        return result;
    }
}
