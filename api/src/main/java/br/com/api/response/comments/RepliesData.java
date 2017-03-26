package br.com.api.response.comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class RepliesData {

    @SerializedName("data")
    private ComentData comentData;

    public ComentData getComentData() {
        return comentData;
    }

}
