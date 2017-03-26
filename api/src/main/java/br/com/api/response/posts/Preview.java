package br.com.api.response.posts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by roquebuarque on 22/03/17.
 */

public class Preview {

    @SerializedName("images")
    private List<Images> images;

    public List<Images> getImages() {
        return images;
    }
}
