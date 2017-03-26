package br.com.api.response.comments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class CommentDataChildrenItem {

    @SerializedName("data")
    private ChildrenRepliesData dataChildren;

    public ChildrenRepliesData getDataChildren() {
        return dataChildren;
    }
}



