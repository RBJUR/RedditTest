package br.com.api.response.comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class CommentData {

    @SerializedName("modhash")
    private String modHash;

    @SerializedName("children")
    private List<CommentDataChildrenItem> comentDataChildrens;

    public List<CommentDataChildrenItem> getComentDataChildrens() {
        return comentDataChildrens;
    }

    public String getModHash() {
        return modHash;
    }
}
