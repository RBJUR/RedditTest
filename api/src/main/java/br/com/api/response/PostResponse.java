package br.com.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roquebuarque on 20/03/17.
 */

public class PostResponse {

    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private DataResponse  dataResponse;
}
