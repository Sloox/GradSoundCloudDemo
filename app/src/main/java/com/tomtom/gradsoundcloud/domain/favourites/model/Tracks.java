package com.tomtom.gradsoundcloud.domain.favourites.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.view.favourites.FavouritesErrors;
import com.tomtom.gradsoundcloud.util.StringUtil;

/**
 * Model representing the Track of a SoundCloud User
 */
public final class Tracks {
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("commentable")
    @Expose
    private Boolean commentable;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("original_content_size")
    @Expose
    private Integer originalContentSize;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("sharing")
    @Expose
    private String sharing;
    @SerializedName("tag_list")
    @Expose
    private String tagList;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("streamable")
    @Expose
    private Boolean streamable;
    @SerializedName("embeddable_by")
    @Expose
    private String embeddableBy;
    @SerializedName("downloadable")
    @Expose
    private Boolean downloadable;
    @SerializedName("purchase_url")
    @Expose
    private String purchaseUrl;
    @SerializedName("label_id")
    @Expose
    private String labelId;
    @SerializedName("purchase_title")
    @Expose
    private String purchaseTitle;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("label_name")
    @Expose
    private String labelName;
    @SerializedName("release")
    @Expose
    private String release;
    @SerializedName("track_type")
    @Expose
    private String trackType;
    @SerializedName("key_signature")
    @Expose
    private String keySignature;
    @SerializedName("isrc")
    @Expose
    private String isrc;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("bpm")
    @Expose
    private String bpm;
    @SerializedName("release_year")
    @Expose
    private String releaseYear;
    @SerializedName("release_month")
    @Expose
    private String releaseMonth;
    @SerializedName("release_day")
    @Expose
    private String releaseDay;
    @SerializedName("original_format")
    @Expose
    private String originalFormat;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("artwork_url")
    @Expose
    private String artworkUrl;
    @SerializedName("waveform_url")
    @Expose
    private String waveformUrl;
    @SerializedName("stream_url")
    @Expose
    private String streamUrl;
    @SerializedName("playback_count")
    @Expose
    private Integer playbackCount;
    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;
    @SerializedName("favoritings_count")
    @Expose
    private Integer favoritingsCount;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("attachments_uri")
    @Expose
    private String attachmentsUri;

    /**
     * An unelegant way to propgate errors up to the view
     */
    private FavouritesErrors favouritesErrors;

    /**
     * Error constructir
     *
     * @param errors the errors
     */
    public Tracks(FavouritesErrors errors) {
        favouritesErrors = errors;
    }


    /**
     *Default construct
     *
     */
    public Tracks() {
    }

    /**
     * Instantiates a new Tracks.
     *
     * @param kind                the kind
     * @param id                  the id
     * @param createdAt           the created at
     * @param userId              the user id
     * @param duration            the duration
     * @param commentable         the commentable
     * @param state               the state
     * @param originalContentSize the original content size
     * @param lastModified        the last modified
     * @param sharing             the sharing
     * @param tagList             the tag list
     * @param permalink           the permalink
     * @param streamable          the streamable
     * @param embeddableBy        the embeddable by
     * @param downloadable        the downloadable
     * @param purchaseUrl         the purchase url
     * @param labelId             the label id
     * @param purchaseTitle       the purchase title
     * @param genre               the genre
     * @param title               the title
     * @param description         the description
     * @param labelName           the label name
     * @param release             the release
     * @param trackType           the track type
     * @param keySignature        the key signature
     * @param isrc                the isrc
     * @param videoUrl            the video url
     * @param bpm                 the bpm
     * @param releaseYear         the release year
     * @param releaseMonth        the release month
     * @param releaseDay          the release day
     * @param originalFormat      the original format
     * @param license             the license
     * @param uri                 the uri
     * @param user                the user
     * @param permalinkUrl        the permalink url
     * @param artworkUrl          the artwork url
     * @param waveformUrl         the waveform url
     * @param streamUrl           the stream url
     * @param playbackCount       the playback count
     * @param downloadCount       the download count
     * @param favoritingsCount    the favoritings count
     * @param commentCount        the comment count
     * @param attachmentsUri      the attachments uri
     */
    public Tracks(String kind, Integer id, String createdAt, Integer userId, Integer duration, Boolean commentable, String state, Integer originalContentSize, String lastModified, String sharing, String tagList, String permalink, Boolean streamable, String embeddableBy, Boolean downloadable, String purchaseUrl, String labelId, String purchaseTitle, String genre, String title, String description, String labelName, String release, String trackType, String keySignature, String isrc, String videoUrl, String bpm, String releaseYear, String releaseMonth, String releaseDay, String originalFormat, String license, String uri, User user, String permalinkUrl, String artworkUrl, String waveformUrl, String streamUrl, Integer playbackCount, Integer downloadCount, Integer favoritingsCount, Integer commentCount, String attachmentsUri) {
        this.kind = kind;
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.duration = duration;
        this.commentable = commentable;
        this.state = state;
        this.originalContentSize = originalContentSize;
        this.lastModified = lastModified;
        this.sharing = sharing;
        this.tagList = tagList;
        this.permalink = permalink;
        this.streamable = streamable;
        this.embeddableBy = embeddableBy;
        this.downloadable = downloadable;
        this.purchaseUrl = purchaseUrl;
        this.labelId = labelId;
        this.purchaseTitle = purchaseTitle;
        this.genre = genre;
        this.title = title;
        this.description = description;
        this.labelName = labelName;
        this.release = release;
        this.trackType = trackType;
        this.keySignature = keySignature;
        this.isrc = isrc;
        this.videoUrl = videoUrl;
        this.bpm = bpm;
        this.releaseYear = releaseYear;
        this.releaseMonth = releaseMonth;
        this.releaseDay = releaseDay;
        this.originalFormat = originalFormat;
        this.license = license;
        this.uri = uri;
        this.user = user;
        this.permalinkUrl = permalinkUrl;
        this.artworkUrl = artworkUrl;
        this.waveformUrl = waveformUrl;
        this.streamUrl = streamUrl;
        this.playbackCount = playbackCount;
        this.downloadCount = downloadCount;
        this.favoritingsCount = favoritingsCount;
        this.commentCount = commentCount;
        this.attachmentsUri = attachmentsUri;
    }


    /**
     * Gets kind.
     *
     * @return The  kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets kind.
     *
     * @param kind The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets id.
     *
     * @return The  id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets created at.
     *
     * @return The  createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets user id.
     *
     * @return The  userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets duration.
     *
     * @return The  duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Gets commentable.
     *
     * @return The  commentable
     */
    public Boolean getCommentable() {
        return commentable;
    }

    /**
     * Sets commentable.
     *
     * @param commentable The commentable
     */
    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }

    /**
     * Gets state.
     *
     * @return The  state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets original content size.
     *
     * @return The  originalContentSize
     */
    public Integer getOriginalContentSize() {
        return originalContentSize;
    }

    /**
     * Sets original content size.
     *
     * @param originalContentSize The original_content_size
     */
    public void setOriginalContentSize(Integer originalContentSize) {
        this.originalContentSize = originalContentSize;
    }

    /**
     * Gets last modified.
     *
     * @return The  lastModified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * Sets last modified.
     *
     * @param lastModified The last_modified
     */
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets sharing.
     *
     * @return The  sharing
     */
    public String getSharing() {
        return sharing;
    }

    /**
     * Sets sharing.
     *
     * @param sharing The sharing
     */
    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    /**
     * Gets tag list.
     *
     * @return The  tagList
     */
    public String getTagList() {
        return tagList;
    }

    /**
     * Sets tag list.
     *
     * @param tagList The tag_list
     */
    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    /**
     * Gets permalink.
     *
     * @return The  permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Sets permalink.
     *
     * @param permalink The permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * Gets streamable.
     *
     * @return The  streamable
     */
    public Boolean getStreamable() {
        return streamable;
    }

    /**
     * Sets streamable.
     *
     * @param streamable The streamable
     */
    public void setStreamable(Boolean streamable) {
        this.streamable = streamable;
    }

    /**
     * Gets embeddable by.
     *
     * @return The  embeddableBy
     */
    public String getEmbeddableBy() {
        return embeddableBy;
    }

    /**
     * Sets embeddable by.
     *
     * @param embeddableBy The embeddable_by
     */
    public void setEmbeddableBy(String embeddableBy) {
        this.embeddableBy = embeddableBy;
    }

    /**
     * Gets downloadable.
     *
     * @return The  downloadable
     */
    public Boolean getDownloadable() {
        return downloadable;
    }

    /**
     * Sets downloadable.
     *
     * @param downloadable The downloadable
     */
    public void setDownloadable(Boolean downloadable) {
        this.downloadable = downloadable;
    }

    /**
     * Gets purchase url.
     *
     * @return The  purchaseUrl
     */
    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    /**
     * Sets purchase url.
     *
     * @param purchaseUrl The purchase_url
     */
    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    /**
     * Gets label id.
     *
     * @return The  labelId
     */
    public String getLabelId() {
        return labelId;
    }

    /**
     * Sets label id.
     *
     * @param labelId The label_id
     */
    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    /**
     * Gets purchase title.
     *
     * @return The  purchaseTitle
     */
    public String getPurchaseTitle() {
        return purchaseTitle;
    }

    /**
     * Sets purchase title.
     *
     * @param purchaseTitle The purchase_title
     */
    public void setPurchaseTitle(String purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    /**
     * Gets genre.
     *
     * @return The  genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre The genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets title.
     *
     * @return The  title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return The  description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets label name.
     *
     * @return The  labelName
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * Sets label name.
     *
     * @param labelName The label_name
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * Gets release.
     *
     * @return The  release
     */
    public String getRelease() {
        return release;
    }

    /**
     * Sets release.
     *
     * @param release The release
     */
    public void setRelease(String release) {
        this.release = release;
    }

    /**
     * Gets track type.
     *
     * @return The  trackType
     */
    public String getTrackType() {
        return trackType;
    }

    /**
     * Sets track type.
     *
     * @param trackType The track_type
     */
    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    /**
     * Gets key signature.
     *
     * @return The  keySignature
     */
    public String getKeySignature() {
        return keySignature;
    }

    /**
     * Sets key signature.
     *
     * @param keySignature The key_signature
     */
    public void setKeySignature(String keySignature) {
        this.keySignature = keySignature;
    }

    /**
     * Gets isrc.
     *
     * @return The  isrc
     */
    public String getIsrc() {
        return isrc;
    }

    /**
     * Sets isrc.
     *
     * @param isrc The isrc
     */
    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    /**
     * Gets video url.
     *
     * @return The  videoUrl
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Sets video url.
     *
     * @param videoUrl The video_url
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * Gets bpm.
     *
     * @return The  bpm
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * Sets bpm.
     *
     * @param bpm The bpm
     */
    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    /**
     * Gets release year.
     *
     * @return The  releaseYear
     */
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets release year.
     *
     * @param releaseYear The release_year
     */
    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Gets release month.
     *
     * @return The  releaseMonth
     */
    public String getReleaseMonth() {
        return releaseMonth;
    }

    /**
     * Sets release month.
     *
     * @param releaseMonth The release_month
     */
    public void setReleaseMonth(String releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    /**
     * Gets release day.
     *
     * @return The  releaseDay
     */
    public String getReleaseDay() {
        return releaseDay;
    }

    /**
     * Sets release day.
     *
     * @param releaseDay The release_day
     */
    public void setReleaseDay(String releaseDay) {
        this.releaseDay = releaseDay;
    }

    /**
     * Gets original format.
     *
     * @return The  originalFormat
     */
    public String getOriginalFormat() {
        return originalFormat;
    }

    /**
     * Sets original format.
     *
     * @param originalFormat The original_format
     */
    public void setOriginalFormat(String originalFormat) {
        this.originalFormat = originalFormat;
    }

    /**
     * Gets license.
     *
     * @return The  license
     */
    public String getLicense() {
        return license;
    }

    /**
     * Sets license.
     *
     * @param license The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Gets uri.
     *
     * @return The  uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets uri.
     *
     * @param uri The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets user.
     *
     * @return The  user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets permalink url.
     *
     * @return The  permalinkUrl
     */
    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    /**
     * Sets permalink url.
     *
     * @param permalinkUrl The permalink_url
     */
    public void setPermalinkUrl(String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    /**
     * Gets artwork url.
     *
     * @return The  artworkUrl
     */
    public String getArtworkUrl() {
        return artworkUrl;
    }

    /**
     * Sets artwork url.
     *
     * @param artworkUrl The artwork_url
     */
    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    /**
     * Gets waveform url.
     *
     * @return The  waveformUrl
     */
    public String getWaveformUrl() {
        return waveformUrl;
    }

    /**
     * Sets waveform url.
     *
     * @param waveformUrl The waveform_url
     */
    public void setWaveformUrl(String waveformUrl) {
        this.waveformUrl = waveformUrl;
    }

    /**
     * Gets stream url.
     *
     * @return The  streamUrl
     */
    public String getStreamUrl() {
        return streamUrl;
    }

    /**
     * Sets stream url.
     *
     * @param streamUrl The stream_url
     */
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    /**
     * Gets playback count.
     *
     * @return The  playbackCount
     */
    public Integer getPlaybackCount() {
        if (playbackCount == null) {
            return 0;
        }
        return playbackCount;
    }

    /**
     * Sets playback count.
     *
     * @param playbackCount The playback_count
     */
    public void setPlaybackCount(Integer playbackCount) {
        this.playbackCount = playbackCount;
    }

    /**
     * Gets download count.
     *
     * @return The  downloadCount
     */
    public Integer getDownloadCount() {
        if (downloadCount == null) {
            return 0;
        }
        return downloadCount;
    }

    /**
     * Sets download count.
     *
     * @param downloadCount The download_count
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * Gets favoritings count.
     *
     * @return The  favoritingsCount
     */
    public Integer getFavoritingsCount() {
        if (favoritingsCount == null) {
            return 0;
        }
        return favoritingsCount;
    }

    /**
     * Sets favoritings count.
     *
     * @param favoritingsCount The favoritings_count
     */
    public void setFavoritingsCount(Integer favoritingsCount) {
        this.favoritingsCount = favoritingsCount;
    }

    /**
     * Gets comment count.
     *
     * @return The  commentCount
     */
    public Integer getCommentCount() {
        if (commentCount == null) {
            return 0;
        }
        return commentCount;
    }

    /**
     * Sets comment count.
     *
     * @param commentCount The comment_count
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * Gets attachments uri.
     *
     * @return The  attachmentsUri
     */
    public String getAttachmentsUri() {
        return attachmentsUri;
    }

    /**
     * Sets attachments uri.
     *
     * @param attachmentsUri The attachments_uri
     */
    public void setAttachmentsUri(String attachmentsUri) {
        this.attachmentsUri = attachmentsUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracks tracks = (Tracks) o;

        if (kind != null ? !kind.equals(tracks.kind) : tracks.kind != null) return false;
        if (id != null ? !id.equals(tracks.id) : tracks.id != null) return false;
        if (createdAt != null ? !createdAt.equals(tracks.createdAt) : tracks.createdAt != null)
            return false;
        if (userId != null ? !userId.equals(tracks.userId) : tracks.userId != null) return false;
        if (duration != null ? !duration.equals(tracks.duration) : tracks.duration != null)
            return false;
        if (commentable != null ? !commentable.equals(tracks.commentable) : tracks.commentable != null)
            return false;
        if (state != null ? !state.equals(tracks.state) : tracks.state != null) return false;
        if (originalContentSize != null ? !originalContentSize.equals(tracks.originalContentSize) : tracks.originalContentSize != null)
            return false;
        if (lastModified != null ? !lastModified.equals(tracks.lastModified) : tracks.lastModified != null)
            return false;
        if (sharing != null ? !sharing.equals(tracks.sharing) : tracks.sharing != null)
            return false;
        if (tagList != null ? !tagList.equals(tracks.tagList) : tracks.tagList != null)
            return false;
        if (permalink != null ? !permalink.equals(tracks.permalink) : tracks.permalink != null)
            return false;
        if (streamable != null ? !streamable.equals(tracks.streamable) : tracks.streamable != null)
            return false;
        if (embeddableBy != null ? !embeddableBy.equals(tracks.embeddableBy) : tracks.embeddableBy != null)
            return false;
        if (downloadable != null ? !downloadable.equals(tracks.downloadable) : tracks.downloadable != null)
            return false;
        if (purchaseUrl != null ? !purchaseUrl.equals(tracks.purchaseUrl) : tracks.purchaseUrl != null)
            return false;
        if (labelId != null ? !labelId.equals(tracks.labelId) : tracks.labelId != null)
            return false;
        if (purchaseTitle != null ? !purchaseTitle.equals(tracks.purchaseTitle) : tracks.purchaseTitle != null)
            return false;
        if (genre != null ? !genre.equals(tracks.genre) : tracks.genre != null) return false;
        if (title != null ? !title.equals(tracks.title) : tracks.title != null) return false;
        if (description != null ? !description.equals(tracks.description) : tracks.description != null)
            return false;
        if (labelName != null ? !labelName.equals(tracks.labelName) : tracks.labelName != null)
            return false;
        if (release != null ? !release.equals(tracks.release) : tracks.release != null)
            return false;
        if (trackType != null ? !trackType.equals(tracks.trackType) : tracks.trackType != null)
            return false;
        if (keySignature != null ? !keySignature.equals(tracks.keySignature) : tracks.keySignature != null)
            return false;
        if (isrc != null ? !isrc.equals(tracks.isrc) : tracks.isrc != null) return false;
        if (videoUrl != null ? !videoUrl.equals(tracks.videoUrl) : tracks.videoUrl != null)
            return false;
        if (bpm != null ? !bpm.equals(tracks.bpm) : tracks.bpm != null) return false;
        if (releaseYear != null ? !releaseYear.equals(tracks.releaseYear) : tracks.releaseYear != null)
            return false;
        if (releaseMonth != null ? !releaseMonth.equals(tracks.releaseMonth) : tracks.releaseMonth != null)
            return false;
        if (releaseDay != null ? !releaseDay.equals(tracks.releaseDay) : tracks.releaseDay != null)
            return false;
        if (originalFormat != null ? !originalFormat.equals(tracks.originalFormat) : tracks.originalFormat != null)
            return false;
        if (license != null ? !license.equals(tracks.license) : tracks.license != null)
            return false;
        if (uri != null ? !uri.equals(tracks.uri) : tracks.uri != null) return false;
        if (user != null ? !user.equals(tracks.user) : tracks.user != null) return false;
        if (permalinkUrl != null ? !permalinkUrl.equals(tracks.permalinkUrl) : tracks.permalinkUrl != null)
            return false;
        if (artworkUrl != null ? !artworkUrl.equals(tracks.artworkUrl) : tracks.artworkUrl != null)
            return false;
        if (waveformUrl != null ? !waveformUrl.equals(tracks.waveformUrl) : tracks.waveformUrl != null)
            return false;
        if (streamUrl != null ? !streamUrl.equals(tracks.streamUrl) : tracks.streamUrl != null)
            return false;
        if (playbackCount != null ? !playbackCount.equals(tracks.playbackCount) : tracks.playbackCount != null)
            return false;
        if (downloadCount != null ? !downloadCount.equals(tracks.downloadCount) : tracks.downloadCount != null)
            return false;
        if (favoritingsCount != null ? !favoritingsCount.equals(tracks.favoritingsCount) : tracks.favoritingsCount != null)
            return false;
        if (commentCount != null ? !commentCount.equals(tracks.commentCount) : tracks.commentCount != null)
            return false;
        return !(attachmentsUri != null ? !attachmentsUri.equals(tracks.attachmentsUri) : tracks.attachmentsUri != null);

    }

    @Override
    public int hashCode() {
        int result = kind != null ? kind.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (commentable != null ? commentable.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (originalContentSize != null ? originalContentSize.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (sharing != null ? sharing.hashCode() : 0);
        result = 31 * result + (tagList != null ? tagList.hashCode() : 0);
        result = 31 * result + (permalink != null ? permalink.hashCode() : 0);
        result = 31 * result + (streamable != null ? streamable.hashCode() : 0);
        result = 31 * result + (embeddableBy != null ? embeddableBy.hashCode() : 0);
        result = 31 * result + (downloadable != null ? downloadable.hashCode() : 0);
        result = 31 * result + (purchaseUrl != null ? purchaseUrl.hashCode() : 0);
        result = 31 * result + (labelId != null ? labelId.hashCode() : 0);
        result = 31 * result + (purchaseTitle != null ? purchaseTitle.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (labelName != null ? labelName.hashCode() : 0);
        result = 31 * result + (release != null ? release.hashCode() : 0);
        result = 31 * result + (trackType != null ? trackType.hashCode() : 0);
        result = 31 * result + (keySignature != null ? keySignature.hashCode() : 0);
        result = 31 * result + (isrc != null ? isrc.hashCode() : 0);
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        result = 31 * result + (bpm != null ? bpm.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (releaseMonth != null ? releaseMonth.hashCode() : 0);
        result = 31 * result + (releaseDay != null ? releaseDay.hashCode() : 0);
        result = 31 * result + (originalFormat != null ? originalFormat.hashCode() : 0);
        result = 31 * result + (license != null ? license.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (permalinkUrl != null ? permalinkUrl.hashCode() : 0);
        result = 31 * result + (artworkUrl != null ? artworkUrl.hashCode() : 0);
        result = 31 * result + (waveformUrl != null ? waveformUrl.hashCode() : 0);
        result = 31 * result + (streamUrl != null ? streamUrl.hashCode() : 0);
        result = 31 * result + (playbackCount != null ? playbackCount.hashCode() : 0);
        result = 31 * result + (downloadCount != null ? downloadCount.hashCode() : 0);
        result = 31 * result + (favoritingsCount != null ? favoritingsCount.hashCode() : 0);
        result = 31 * result + (commentCount != null ? commentCount.hashCode() : 0);
        result = 31 * result + (attachmentsUri != null ? attachmentsUri.hashCode() : 0);
        return result;
    }

    /**
     * Gets favourites errors.
     *
     * @return the favourites errors
     */
    public FavouritesErrors getFavouritesErrors() {
        return favouritesErrors;
    }

    public String getTitleDescription() {
        if (StringUtil.isNullorEmpty(title)) {
            return "Track Title unavailable";
        }
        return getTitle();
    }


    public String getDescriptionFormatted() {
        if (StringUtil.isNullorEmpty(description)) {
            return "Track Description unavailable";
        }
        return description;
    }

    public String getTrackPicture() {
        if (StringUtil.isNotNullAndNotEmpty(artworkUrl)) {
            return artworkUrl;
        }
        if (StringUtil.isNotNullAndNotEmpty(user.getAvatarUrl())) {
            return user.getAvatarUrl();
        }
        return "http://dummyimage.com/160x160/ffffff/473c47.jpg&text=Picture+unavailable";
    }
}
