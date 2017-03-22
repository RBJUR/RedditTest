package br.com.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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



}
