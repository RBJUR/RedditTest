package br.com.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roquebuarque on 20/03/17.
 */

public class DataResponse {

    @SerializedName("children")
    private List<ChildrenResponse> childrenResponse;

    public List<ChildrenResponse> getChildrenResponse() {
        return childrenResponse;
    }
}
