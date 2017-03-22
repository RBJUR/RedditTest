package br.com.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roquebuarque on 20/03/17.
 */

public class ChildrenResponse {
    @SerializedName("kind")
    private String kind;

    @SerializedName("data")
    private ItemResponse listItemResponse;

    public String getKind() {
        return kind;
    }

    public ItemResponse getListItemResponse() {
        return listItemResponse;
    }
}