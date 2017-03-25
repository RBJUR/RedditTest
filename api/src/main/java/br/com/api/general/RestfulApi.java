package br.com.api.general;


import br.com.api.response.PostListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by roque on 24/03/2017.
 */
public interface RestfulApi {

    @GET
    Call<PostListResponse> getPosts(@Url String url);

}