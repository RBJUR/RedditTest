package br.com.api.response.posts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 22/03/17.
 */

public class Source {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}
