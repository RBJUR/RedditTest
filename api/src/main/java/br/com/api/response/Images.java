package br.com.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 22/03/17.
 */

public class Images {

    @SerializedName("source")
    private Source source;

    public Source getSource() {
        return source;
    }
}
