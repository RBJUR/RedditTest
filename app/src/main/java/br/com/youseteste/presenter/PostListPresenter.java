package br.com.youseteste.presenter;

import javax.inject.Inject;

import br.com.api.general.RestfulApi;
import br.com.api.response.posts.PostListResponse;
import br.com.youseteste.application.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostListPresenter {

    private PostListView view;

    @Inject
    RestfulApi api;

    public PostListPresenter(PostListView view) {
        this.view = view;
        if (App.getInstance() != null) {
            App.getInstance().getComponent().inject(this);
        }

    }

    public void getListPost() {

       doRequestListPost();

    }

    public void doRequestListPost() {
        view.showLoading(true);
        Call<PostListResponse> call = api.getPosts("/r/Android/new/.json");

        call.enqueue(new Callback<PostListResponse>() {
            @Override
            public void onResponse(Call<PostListResponse> call, Response<PostListResponse> response) {
                onSuccessListPost(response);
            }

            @Override
            public void onFailure(Call<PostListResponse> call, Throwable t) {
                onFailureRequestList();
                t.printStackTrace();
            }
        });
    }

    public void onFailureRequestList() {
        view.showLoading(false);
        view.showDialogRetry();


    }

    public void onSuccessListPost(Response<PostListResponse> response) {
        view.showLoading(false);
        if (response != null && response.body() != null) {
            PostListResponse postResponse = response.body();
            view.showListPost(postResponse);
        }

    }

    public interface PostListView {

        void showListPost(PostListResponse postResponse);

        void showDialogRetry();

        void showLoading(boolean show);

    }

}
