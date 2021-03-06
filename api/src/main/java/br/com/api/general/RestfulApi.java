package br.com.api.general;


import java.util.List;

import br.com.api.response.posts.PostListResponse;
import br.com.api.response.comments.CommentItem;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by roque on 24/03/2017.
 */
public interface RestfulApi {

    @GET
    Call<PostListResponse> getPosts(@Url String url);


    @GET
    Call<ResponseBody> getPostDetail(@Url String url);
}