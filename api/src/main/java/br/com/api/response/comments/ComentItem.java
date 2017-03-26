package br.com.api.response.comments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class ComentItem {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private ComentData comentData;

    public ComentData getComentData() {
        return comentData;
    }

    public String getKind() {
        return kind;
    }
}
