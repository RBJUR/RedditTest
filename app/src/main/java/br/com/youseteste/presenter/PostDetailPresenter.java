package br.com.youseteste.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import br.com.api.general.RestfulApi;
import br.com.api.response.comments.CommentItem;
import br.com.youseteste.application.App;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roquebuarque on 26/03/17.
 */

public class PostDetailPresenter {

    private PostDetailView view;

    @Inject
    RestfulApi api;

    public PostDetailPresenter(PostDetailView view) {
        this.view = view;
        if (App.getInstance() != null) {
            App.getInstance().getComponent().inject(this);
        }

    }

    public void doRequestReplies(String perlink) {

        Call<ResponseBody> call = api.getPostDetail(perlink+".json");

        call.enqueue(new Callback<ResponseBody >() {
            @Override
            public void onResponse(Call< ResponseBody > call, Response< ResponseBody> response) {
                onSuccessListReplies(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showRetryDialog();
                t.printStackTrace();
            }
        });

    }

    private void onSuccessListReplies(ResponseBody  commentResponse){
        if(commentResponse != null){
            try {
                String response = commentResponse.string().replaceAll("\"\"", "null");
                Gson gson = new Gson();
                Type listType = new TypeToken<List<CommentItem>>(){}.getType();
                List<CommentItem> posts = gson.fromJson(response, listType);
                if(posts != null){
                    view.showRepliesList(posts);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public interface PostDetailView {

        void showRepliesList(List<CommentItem> commentResponse);

        void showRetryDialog();
    }
}
