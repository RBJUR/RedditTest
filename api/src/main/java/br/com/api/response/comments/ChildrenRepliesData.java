package br.com.api.response.comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.annotation.ElementType;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class ChildrenRepliesData {

    @SerializedName("author")
    private String author;

    @SerializedName("body")
    private String body;

    @SerializedName("title")
    private String title;

    @SerializedName("downs")
    private int downs;

    @SerializedName("num_comments")
    private int numComments;

    @SerializedName("ups")
    private int ups;

    @SerializedName("replies")
    @Expose()
    private RepliesData repliesData;

    public RepliesData getRepliesData() {
        return repliesData;
    }

    @Override
    public String toString() {
        return "replies=" + repliesData;
    }

    public int getDowns() {
        return downs;
    }

    public int getUps() {
        return ups;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public int getNumComments() {
        return numComments;
    }
}
