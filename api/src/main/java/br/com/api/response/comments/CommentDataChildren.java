package br.com.api.response.comments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class CommentDataChildren {

    @SerializedName("replies")
    private RepliesData repliesData;

    public RepliesData getRepliesData() {
        return repliesData;
    }

    @Override
    public String toString() {
        return "replies=" + repliesData;
    }
}
