package com.tomtom.gradsoundcloud.domain.profile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tomtom.gradsoundcloud.view.profile.ProfileErrors;
import com.tomtom.gradsoundcloud.util.IntegerUtil;
import com.tomtom.gradsoundcloud.util.StringUtil;

/**
 * Model representing the Profile of a SoundCloud User
 */
public final class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("discogs_name")
    @Expose
    private String discogsName;
    @SerializedName("myspace_name")
    @Expose
    private String myspaceName;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("website_title")
    @Expose
    private String websiteTitle;
    @SerializedName("online")
    @Expose
    private Boolean online;
    @SerializedName("track_count")
    @Expose
    private Integer trackCount;
    @SerializedName("playlist_count")
    @Expose
    private Integer playlistCount;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("followings_count")
    @Expose
    private Integer followingsCount;
    @SerializedName("public_favorites_count")
    @Expose
    private Integer publicFavoritesCount;

    /**
     * An unelegant way to propgate errors up to the view
     */
    private ProfileErrors error;

    /**
     * Constructor to fully construct the class
     *
     * @param id                   the id
     * @param permalink            the permalink
     * @param username             the username
     * @param uri                  the uri
     * @param permalinkUrl         the permalink url
     * @param avatarUrl            the avatar url
     * @param country              the country
     * @param fullName             the full name
     * @param city                 the city
     * @param description          the description
     * @param discogsName          the discogs name
     * @param myspaceName          the myspace name
     * @param website              the website
     * @param websiteTitle         the website title
     * @param online               the online
     * @param trackCount           the track count
     * @param playlistCount        the playlist count
     * @param followersCount       the followers count
     * @param followingsCount      the followings count
     * @param publicFavoritesCount the public favorites count
     */
    public User(Integer id, String permalink, String username, String uri, String permalinkUrl, String avatarUrl, String country, String fullName, String city, String description, String discogsName, String myspaceName, String website, String websiteTitle, Boolean online, Integer trackCount, Integer playlistCount, Integer followersCount, Integer followingsCount, Integer publicFavoritesCount) {
        this.id = id;
        this.permalink = permalink;
        this.username = username;
        this.uri = uri;
        this.permalinkUrl = permalinkUrl;
        this.avatarUrl = avatarUrl;
        this.country = country;
        this.fullName = fullName;
        this.city = city;
        this.description = description;
        this.discogsName = discogsName;
        this.myspaceName = myspaceName;
        this.website = website;
        this.websiteTitle = websiteTitle;
        this.online = online;
        this.trackCount = trackCount;
        this.playlistCount = playlistCount;
        this.followersCount = followersCount;
        this.followingsCount = followingsCount;
        this.publicFavoritesCount = publicFavoritesCount;
    }


    /**
     * Instantiates a new User from string only parameters.
     *
     * @param id                   the id
     * @param permalink            the permalink
     * @param username             the username
     * @param uri                  the uri
     * @param permalinkUrl         the permalink url
     * @param avatarUrl            the avatar url
     * @param country              the country
     * @param fullName             the full name
     * @param city                 the city
     * @param description          the description
     * @param discogsName          the discogs name
     * @param myspaceName          the myspace name
     * @param website              the website
     * @param websiteTitle         the website title
     * @param online               the online
     * @param trackCount           the track count
     * @param playlistCount        the playlist count
     * @param followersCount       the followers count
     * @param followingsCount      the followings count
     * @param publicFavoritesCount the public favorites count
     */
    public User(String id, String permalink, String username, String uri, String permalinkUrl, String avatarUrl, String country, String fullName, String city, String description, String discogsName, String myspaceName, String website, String websiteTitle, Boolean online, String trackCount, String playlistCount, String followersCount, String followingsCount, String publicFavoritesCount) {
        this.id = IntegerUtil.getIntFromString(id);
        this.permalink = StringUtil.checkNull(permalink);
        this.username = StringUtil.checkNull(username);
        this.uri = StringUtil.checkNull(uri);
        this.permalinkUrl = StringUtil.checkNull(permalinkUrl);
        this.avatarUrl = StringUtil.checkNull(avatarUrl);
        this.country = StringUtil.checkNull(country);
        this.fullName = StringUtil.checkNull(fullName);
        this.city = StringUtil.checkNull(city);
        this.description = StringUtil.checkNull(description);
        this.discogsName = StringUtil.checkNull(discogsName);
        this.myspaceName = StringUtil.checkNull(myspaceName);
        this.website = StringUtil.checkNull(website);
        this.websiteTitle = StringUtil.checkNull(websiteTitle);
        this.online = online;
        this.trackCount = IntegerUtil.getIntFromString(trackCount);
        this.playlistCount = IntegerUtil.getIntFromString(playlistCount);
        this.followersCount = IntegerUtil.getIntFromString(followersCount);
        this.followingsCount = IntegerUtil.getIntFromString(followingsCount);
        this.publicFavoritesCount = IntegerUtil.getIntFromString(publicFavoritesCount);
    }



    public User(ProfileErrors errorUser) {
        this.error = errorUser;
    }

    public User() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets permalink.
     *
     * @return the permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets uri.
     *
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Gets permalink url.
     *
     * @return the permalink url
     */
    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    /**
     * Gets avatar url.
     *
     * @return the avatar url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets discogs name.
     *
     * @return the discogs name
     */
    public String getDiscogsName() {
        return discogsName;
    }

    /**
     * Gets myspace name.
     *
     * @return the myspace name
     */
    public String getMyspaceName() {
        return myspaceName;
    }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Gets website title.
     *
     * @return the website title
     */
    public String getWebsiteTitle() {
        return websiteTitle;
    }

    /**
     * Gets online.
     *
     * @return the online
     */
    public Boolean getOnline() {
        return online;
    }

    /**
     * sets Online
     */
    public void setOnline(Boolean flag) {
        this.online = flag;
    }

    /**
     * Gets track count.
     *
     * @return the track count
     */
    public Integer getTrackCount() {
        return trackCount;
    }

    /**
     * Gets playlist count.
     *
     * @return the playlist count
     */
    public Integer getPlaylistCount() {
        return playlistCount;
    }

    /**
     * Gets followers count.
     *
     * @return the followers count
     */
    public Integer getFollowersCount() {
        return followersCount;
    }

    /**
     * Gets followings count.
     *
     * @return the followings count
     */
    public Integer getFollowingsCount() {
        return followingsCount;
    }

    /**
     * Gets public favorites count.
     *
     * @return the public favorites count
     */
    public Integer getPublicFavoritesCount() {
        return publicFavoritesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (permalink != null ? !permalink.equals(user.permalink) : user.permalink != null)
            return false;
        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;
        if (uri != null ? !uri.equals(user.uri) : user.uri != null) return false;
        if (permalinkUrl != null ? !permalinkUrl.equals(user.permalinkUrl) : user.permalinkUrl != null)
            return false;
        if (avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null)
            return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (fullName != null ? !fullName.equals(user.fullName) : user.fullName != null)
            return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (description != null ? !description.equals(user.description) : user.description != null)
            return false;
        if (discogsName != null ? !discogsName.equals(user.discogsName) : user.discogsName != null)
            return false;
        if (myspaceName != null ? !myspaceName.equals(user.myspaceName) : user.myspaceName != null)
            return false;
        if (website != null ? !website.equals(user.website) : user.website != null) return false;
        if (websiteTitle != null ? !websiteTitle.equals(user.websiteTitle) : user.websiteTitle != null)
            return false;
        if (online != null ? !online.equals(user.online) : user.online != null) return false;
        if (trackCount != null ? !trackCount.equals(user.trackCount) : user.trackCount != null)
            return false;
        if (playlistCount != null ? !playlistCount.equals(user.playlistCount) : user.playlistCount != null)
            return false;
        if (followersCount != null ? !followersCount.equals(user.followersCount) : user.followersCount != null)
            return false;
        if (followingsCount != null ? !followingsCount.equals(user.followingsCount) : user.followingsCount != null)
            return false;
        return !(publicFavoritesCount != null ? !publicFavoritesCount.equals(user.publicFavoritesCount) : user.publicFavoritesCount != null);

    }


    /**
     * Gets error for the user.
     * The error allows the presenter & ultimately the view
     * know what went wrong & act accordingly
     *
     * @return the error
     */
    public ProfileErrors getError() {
        return error;
    }


    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (permalink != null ? permalink.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (permalinkUrl != null ? permalinkUrl.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (discogsName != null ? discogsName.hashCode() : 0);
        result = 31 * result + (myspaceName != null ? myspaceName.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (websiteTitle != null ? websiteTitle.hashCode() : 0);
        result = 31 * result + (online != null ? online.hashCode() : 0);
        result = 31 * result + (trackCount != null ? trackCount.hashCode() : 0);
        result = 31 * result + (playlistCount != null ? playlistCount.hashCode() : 0);
        result = 31 * result + (followersCount != null ? followersCount.hashCode() : 0);
        result = 31 * result + (followingsCount != null ? followingsCount.hashCode() : 0);
        result = 31 * result + (publicFavoritesCount != null ? publicFavoritesCount.hashCode() : 0);
        return result;
    }

    /**
     * Gets User City from populated data
     */
    public String getUserCityDescription() {
        String result = "";
        if (StringUtil.isNotNullAndNotEmpty(city)) {
            result = city;
        }

        if (StringUtil.isNotNullAndNotEmpty(country)) {
            if (StringUtil.isNotNullAndNotEmpty(result)) {
                result += ", " + country;
            } else {
                result = country;
            }

        }

        if (StringUtil.isNullorEmpty(result)) {
            result = "No Location given";
        }

        return result;

    }

    /**
     * Gets User Followers from populated data
     */
    public int getFollowersCountDescription() {
        if (followersCount == null) {
            return 0;
        }
        return followersCount;
    }

    /**
     * Gets User playlistCount from populated data
     */
    public int getPlayListCountDescription() {
        if (playlistCount == null) {
            return 0;
        }
        return playlistCount;
    }

    /**
     * Gets User Online status from populated data
     */
    public String getOnlineStatus() {
        if (online == null) {
            return "Unknown";
        }
        return (online) ? "Online" : "Offline";
    }

    /**
     * Gets User Username from populated data
     */
    public String getUsernameDescription() {
        if (StringUtil.isNullorEmpty(username)) {
            return "Unknown username";
        }
        return username;
    }

    /**
     * Gets User fullname from populated data
     */
    public String getFullNameDescription() {
        if (StringUtil.isNullorEmpty(fullName)) {
            return "No Name Provided";
        }
        return fullName;
    }
}
