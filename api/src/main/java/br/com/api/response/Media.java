package br.com.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 23/03/17.
 */

public class Media {

    @SerializedName("type")
    private String type;

    @SerializedName("oembed")
    private Oembed oembed;

    public Oembed getOembed() {
        return oembed;
    }

    public String getType() {
        return type;
    }
}
