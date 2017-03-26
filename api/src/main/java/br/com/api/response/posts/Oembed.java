package br.com.api.response.posts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 23/03/17.
 */

public class Oembed {

    @SerializedName("provider_url")
    private String providerUrl;

    @SerializedName("version")
    private String version;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail_width")
    private int thumbnailsWidth;

    @SerializedName("height")
    private int height;

    @SerializedName("width")
    private int width;

    @SerializedName("html")
    private String html;

    @SerializedName("author_name")
    private String author_name;

    @SerializedName("thumbnail_height")
    private int thumbnailHeight;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("provider_name")
    private String providerName;

    @SerializedName("author_url")
    private String author_url;


    public String getProviderUrl() {
        return providerUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnailsWidth() {
        return thumbnailsWidth;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getHtml() {
        return html;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getAuthor_url() {
        return author_url;
    }
}
