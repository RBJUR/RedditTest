package br.com.api.response.posts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 20/03/17.
 */


public class ItemResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("selftext_html")
    private String selftextHtml;

    @SerializedName("selftext")
    private String selftext;

    @SerializedName("likes")
    private String likes;

    @SerializedName("suggested_sort")
    private String suggestedSort;

  /*  @SerializedName("secure_media")
    private String secureMedia;*/

    @SerializedName("link_flair_text")
    private String linkFlairText;

    @SerializedName("gilded")
    private int gilded;

    @SerializedName("clicked")
    private boolean clicked;

    @SerializedName("score")
    private int score;

    @SerializedName("report_reasons")
    private String reportReasons;

    @SerializedName("author")
    private String author;

    @SerializedName("saved")
    private boolean saved;

    @SerializedName("name")
    private String name;

    @SerializedName("subreddit_name_prefixed")
    private String subredditNamePrefixed;

    @SerializedName("approved_by")
    private String approvedBy;

    @SerializedName("over_18")
    private boolean over18;

    @SerializedName("domain")
    private String domain;

    @SerializedName("hidden")
    private boolean hidden;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("subreddit_id")
    private String subredditId;

    @SerializedName("edited")
    private String edited;

    @SerializedName("link_flair_css_class")
    private String linkFlairCssClass;

    @SerializedName("authorFlairCssClass")
    private String authorFlairCssClass;

    @SerializedName("downs")
    private int downs;

    @SerializedName("brand_safe")
    private boolean brandSafe;

    @SerializedName("archived")
    private boolean archived;

    @SerializedName("removal_reason")
    private String removalReason;

    @SerializedName("is_self")
    private boolean isSelf;

    @SerializedName("hide_score")
    private boolean hideScore;

    @SerializedName("spoiler")
    private boolean spoiler;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("num_reports")
    private String numReports;

    @SerializedName("locked")
    private boolean locked;

    @SerializedName("stickied")
    private boolean stickied;

    @SerializedName("created")
    private long created;

    @SerializedName("url")
    private String url;

    @SerializedName("author_flair_text")
    private String authorFlairText;

    @SerializedName("quarantine")
    private boolean quarantine;

    @SerializedName("title")
    private String title;

    @SerializedName("created_utc")
    private long createdUtc;

    @SerializedName("distinguished")
    private String distinguished;

    /*@SerializedName("media")
    private String media;*/

    @SerializedName("num_comments")
    private int numComments;

    @SerializedName("visited")
    private boolean visited;

    @SerializedName("subreddit_type")
    private String subredditType;

    @SerializedName("ups")
    private int ups;

    @SerializedName("preview")
    private Preview preview;


    public String getId() {
        return id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getSelftextHtml() {
        return selftextHtml;
    }

    public String getSelftext() {
        return selftext;
    }

    public String getLikes() {
        return likes;
    }

    public String getSuggestedSort() {
        return suggestedSort;
    }

    public String getLinkFlairText() {
        return linkFlairText;
    }

    public int getGilded() {
        return gilded;
    }

    public boolean isClicked() {
        return clicked;
    }

    public int getScore() {
        return score;
    }

    public String getReportReasons() {
        return reportReasons;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isSaved() {
        return saved;
    }

    public String getName() {
        return name;
    }

    public String getSubredditNamePrefixed() {
        return subredditNamePrefixed;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public boolean isOver18() {
        return over18;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public String getEdited() {
        return edited;
    }

    public String getLinkFlairCssClass() {
        return linkFlairCssClass;
    }

    public String getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    public int getDowns() {
        return downs;
    }

    public boolean isBrandSafe() {
        return brandSafe;
    }

    public boolean isArchived() {
        return archived;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public boolean isHideScore() {
        return hideScore;
    }

    public boolean isSpoiler() {
        return spoiler;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getNumReports() {
        return numReports;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isStickied() {
        return stickied;
    }

    public long getCreated() {
        return created;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthorFlairText() {
        return authorFlairText;
    }

    public boolean isQuarantine() {
        return quarantine;
    }

    public String getTitle() {
        return title;
    }

    public long getCreatedUtc() {
        return createdUtc;
    }

    public String getDistinguished() {
        return distinguished;
    }

    public int getNumComments() {
        return numComments;
    }

    public boolean isVisited() {
        return visited;
    }

    public String getSubredditType() {
        return subredditType;
    }

    public int getUps() {
        return ups;
    }

    public Preview getPreview() {
        return preview;
    }
}
