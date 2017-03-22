package br.com.youseteste.presenter;

import java.util.List;

import br.com.api.general.ServiceGenerator;
import br.com.api.interfaces.RestfulApi;
import br.com.api.response.PostListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostListPresenter {

    private PostListView view;

    RestfulApi api;

    public PostListPresenter(PostListView view) {
        this.view = view;
        api = ServiceGenerator.createService(RestfulApi.class, "https://www.reddit.com/r/Android/new/");

    }

    public void doRequestListPost() {

        Call<PostListResponse> call = api.getPosts(".json");

        call.enqueue(new Callback<PostListResponse>() {
            @Override
            public void onResponse(Call<PostListResponse> call, Response<PostListResponse> response) {
                onSuccessListPost(response);
            }

            @Override
            public void onFailure(Call<PostListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void onSuccessListPost(Response<PostListResponse> response) {
        if (response != null && response.body() != null) {
            PostListResponse postResponse = response.body();
            view.showListPost(postResponse);
        }

    }

    public interface PostListView {

        void showListPost(PostListResponse postResponse);
    }

}
