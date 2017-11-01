package misiulia.alex.dev.andrtwitter.entity;

import com.google.gson.annotations.SerializedName;


public class Profile {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("screen_name")
    private String screenName;

    @SerializedName("location")
    private String location;

    @SerializedName("profile_location")
    private Object profileLocation;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("protected")
    private Boolean _protected;

    @SerializedName("followers_count")

    private Integer followersCount;

    @SerializedName("friends_count")
    private Integer friendsCount;

    @SerializedName("listed_count")
    private Integer listedCount;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("favourites_count")
    private Integer favouritesCount;
    @SerializedName("utc_offset")

    private Integer utcOffset;

    @SerializedName("time_zone")
    private String timeZone;

    @SerializedName("geo_enabled")
    private Boolean geoEnabled;

    @SerializedName("verified")
    private Boolean verified;

    @SerializedName("statuses_count")
    private Integer statusesCount;

    @SerializedName("lang")
    private String lang;

    @SerializedName("contributors_enabled")
    private Boolean contributorsEnabled;

    @SerializedName("is_translator")
    private Boolean isTranslator;

    @SerializedName("is_translation_enabled")
    private Boolean isTranslationEnabled;

    @SerializedName("profile_background_color")
    private String profileBackgroundColor;

    @SerializedName("profile_background_image_url")
    private String profileBackgroundImageUrl;

    @SerializedName("profile_background_image_url_https")
    private String profileBackgroundImageUrlHttps;

    @SerializedName("profile_background_tile")
    private Boolean profileBackgroundTile;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    @SerializedName("profile_image_url_https")
    private String profileImageUrlHttps;

    @SerializedName("profile_banner_url")
    private String profileBannerUrl;

    @SerializedName("profile_link_color")
    private String profileLinkColor;

    @SerializedName("profile_sidebar_border_color")
    private String profileSidebarBorderColor;

    @SerializedName("profile_sidebar_fill_color")
    private String profileSidebarFillColor;

    @SerializedName("profile_text_color")
    private String profileTextColor;

    @SerializedName("profile_use_background_image")
    private Boolean profileUseBackgroundImage;

    @SerializedName("has_extended_profile")
    private Boolean hasExtendedProfile;

    @SerializedName("default_profile")
    private Boolean defaultProfile;

    @SerializedName("default_profile_image")
    private Boolean defaultProfileImage;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getLocation() {
        return location;
    }

    public Object getProfileLocation() {
        return profileLocation;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Boolean get_protected() {
        return _protected;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getFriendsCount() {
        return friendsCount;
    }

    public Integer getListedCount() {
        return listedCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getFavouritesCount() {
        return favouritesCount;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Integer getStatusesCount() {
        return statusesCount;
    }

    public String getLang() {
        return lang;
    }

    public Boolean getContributorsEnabled() {
        return contributorsEnabled;
    }

    public Boolean getTranslator() {
        return isTranslator;
    }

    public Boolean getTranslationEnabled() {
        return isTranslationEnabled;
    }

    public String getProfileBackgroundColor() {
        return profileBackgroundColor;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBackgroundImageUrl;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return profileBackgroundImageUrlHttps;
    }

    public Boolean getProfileBackgroundTile() {
        return profileBackgroundTile;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public String getProfileLinkColor() {
        return profileLinkColor;
    }

    public String getProfileSidebarBorderColor() {
        return profileSidebarBorderColor;
    }

    public String getProfileSidebarFillColor() {
        return profileSidebarFillColor;
    }

    public String getProfileTextColor() {
        return profileTextColor;
    }

    public Boolean getProfileUseBackgroundImage() {
        return profileUseBackgroundImage;
    }

    public Boolean getHasExtendedProfile() {
        return hasExtendedProfile;
    }

    public Boolean getDefaultProfile() {
        return defaultProfile;
    }

    public Boolean getDefaultProfileImage() {
        return defaultProfileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (!id.equals(profile.id)) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (screenName != null ? !screenName.equals(profile.screenName) : profile.screenName != null) {
            return false;
        }
        if (location != null ? !location.equals(profile.location) : profile.location != null) {
            return false;
        }
        if (profileLocation != null ? !profileLocation.equals(profile.profileLocation) : profile.profileLocation != null) {
            return false;
        }
        if (description != null ? !description.equals(profile.description) : profile.description != null) {
            return false;
        }
        if (url != null ? !url.equals(profile.url) : profile.url != null) return false;
        if (_protected != null ? !_protected.equals(profile._protected) : profile._protected != null) {
            return false;
        }
        if (followersCount != null ? !followersCount.equals(profile.followersCount) : profile.followersCount != null) {
            return false;
        }
        if (friendsCount != null ? !friendsCount.equals(profile.friendsCount) : profile.friendsCount != null) {
            return false;
        }
        if (listedCount != null ? !listedCount.equals(profile.listedCount) : profile.listedCount != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(profile.createdAt) : profile.createdAt != null) {
            return false;
        }
        if (favouritesCount != null ? !favouritesCount.equals(profile.favouritesCount) : profile.favouritesCount != null) {
            return false;
        }
        if (utcOffset != null ? !utcOffset.equals(profile.utcOffset) : profile.utcOffset != null) {
            return false;
        }
        if (timeZone != null ? !timeZone.equals(profile.timeZone) : profile.timeZone != null) {
            return false;
        }
        if (geoEnabled != null ? !geoEnabled.equals(profile.geoEnabled) : profile.geoEnabled != null) {
            return false;
        }
        if (verified != null ? !verified.equals(profile.verified) : profile.verified != null) {
            return false;
        }
        if (statusesCount != null ? !statusesCount.equals(profile.statusesCount) : profile.statusesCount != null) {
            return false;
        }
        if (lang != null ? !lang.equals(profile.lang) : profile.lang != null) return false;
        if (contributorsEnabled != null ? !contributorsEnabled.equals(profile.contributorsEnabled) : profile.contributorsEnabled != null) {
            return false;
        }
        if (isTranslator != null ? !isTranslator.equals(profile.isTranslator) : profile.isTranslator != null) {
            return false;
        }
        if (isTranslationEnabled != null ? !isTranslationEnabled.equals(profile.isTranslationEnabled) : profile.isTranslationEnabled != null) {
            return false;
        }
        if (profileBackgroundColor != null ? !profileBackgroundColor.equals(profile.profileBackgroundColor) : profile.profileBackgroundColor != null) {
            return false;
        }
        if (profileBackgroundImageUrl != null ? !profileBackgroundImageUrl.equals(profile.profileBackgroundImageUrl) : profile.profileBackgroundImageUrl != null) {
            return false;
        }
        if (profileBackgroundImageUrlHttps != null ? !profileBackgroundImageUrlHttps.equals(profile.profileBackgroundImageUrlHttps) : profile.profileBackgroundImageUrlHttps != null) {
            return false;
        }
        if (profileBackgroundTile != null ? !profileBackgroundTile.equals(profile.profileBackgroundTile) : profile.profileBackgroundTile != null) {
            return false;
        }
        if (profileImageUrl != null ? !profileImageUrl.equals(profile.profileImageUrl) : profile.profileImageUrl != null) {
            return false;
        }
        if (profileImageUrlHttps != null ? !profileImageUrlHttps.equals(profile.profileImageUrlHttps) : profile.profileImageUrlHttps != null) {
            return false;
        }
        if (profileBannerUrl != null ? !profileBannerUrl.equals(profile.profileBannerUrl) : profile.profileBannerUrl != null) {
            return false;
        }
        if (profileLinkColor != null ? !profileLinkColor.equals(profile.profileLinkColor) : profile.profileLinkColor != null) {
            return false;
        }
        if (profileSidebarBorderColor != null ? !profileSidebarBorderColor.equals(profile.profileSidebarBorderColor) : profile.profileSidebarBorderColor != null) {
            return false;
        }
        if (profileSidebarFillColor != null ? !profileSidebarFillColor.equals(profile.profileSidebarFillColor) : profile.profileSidebarFillColor != null) {
            return false;
        }
        if (profileTextColor != null ? !profileTextColor.equals(profile.profileTextColor) : profile.profileTextColor != null) {
            return false;
        }
        if (profileUseBackgroundImage != null ? !profileUseBackgroundImage.equals(profile.profileUseBackgroundImage) : profile.profileUseBackgroundImage != null) {
            return false;
        }
        if (hasExtendedProfile != null ? !hasExtendedProfile.equals(profile.hasExtendedProfile) : profile.hasExtendedProfile != null) {
            return false;
        }
        if (defaultProfile != null ? !defaultProfile.equals(profile.defaultProfile) : profile.defaultProfile != null) {
            return false;
        }
        return defaultProfileImage != null ? defaultProfileImage.equals(profile.defaultProfileImage) : profile.defaultProfileImage == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (profileLocation != null ? profileLocation.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (_protected != null ? _protected.hashCode() : 0);
        result = 31 * result + (followersCount != null ? followersCount.hashCode() : 0);
        result = 31 * result + (friendsCount != null ? friendsCount.hashCode() : 0);
        result = 31 * result + (listedCount != null ? listedCount.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (favouritesCount != null ? favouritesCount.hashCode() : 0);
        result = 31 * result + (utcOffset != null ? utcOffset.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + (geoEnabled != null ? geoEnabled.hashCode() : 0);
        result = 31 * result + (verified != null ? verified.hashCode() : 0);
        result = 31 * result + (statusesCount != null ? statusesCount.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (contributorsEnabled != null ? contributorsEnabled.hashCode() : 0);
        result = 31 * result + (isTranslator != null ? isTranslator.hashCode() : 0);
        result = 31 * result + (isTranslationEnabled != null ? isTranslationEnabled.hashCode() : 0);
        result = 31 * result + (profileBackgroundColor != null ? profileBackgroundColor.hashCode() : 0);
        result = 31 * result + (profileBackgroundImageUrl != null ? profileBackgroundImageUrl.hashCode() : 0);
        result = 31 * result + (profileBackgroundImageUrlHttps != null ? profileBackgroundImageUrlHttps.hashCode() : 0);
        result = 31 * result + (profileBackgroundTile != null ? profileBackgroundTile.hashCode() : 0);
        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
        result = 31 * result + (profileImageUrlHttps != null ? profileImageUrlHttps.hashCode() : 0);
        result = 31 * result + (profileBannerUrl != null ? profileBannerUrl.hashCode() : 0);
        result = 31 * result + (profileLinkColor != null ? profileLinkColor.hashCode() : 0);
        result = 31 * result + (profileSidebarBorderColor != null ? profileSidebarBorderColor.hashCode() : 0);
        result = 31 * result + (profileSidebarFillColor != null ? profileSidebarFillColor.hashCode() : 0);
        result = 31 * result + (profileTextColor != null ? profileTextColor.hashCode() : 0);
        result = 31 * result + (profileUseBackgroundImage != null ? profileUseBackgroundImage.hashCode() : 0);
        result = 31 * result + (hasExtendedProfile != null ? hasExtendedProfile.hashCode() : 0);
        result = 31 * result + (defaultProfile != null ? defaultProfile.hashCode() : 0);
        result = 31 * result + (defaultProfileImage != null ? defaultProfileImage.hashCode() : 0);
        return result;
    }
}

