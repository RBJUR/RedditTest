package br.com.api.response.comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class ComentData {

    @SerializedName("modhash")
    private String modHash;

    @SerializedName("children")
    private List<ComentDataChildrenItem> comentDataChildrens;

    public List<ComentDataChildrenItem> getComentDataChildrens() {
        return comentDataChildrens;
    }

    public String getModHash() {
        return modHash;
    }
}
