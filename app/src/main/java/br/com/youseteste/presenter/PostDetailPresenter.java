package br.com.youseteste.presenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import br.com.api.general.RestfulApi;
import br.com.api.response.comments.CommentItem;
import br.com.youseteste.application.App;
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

        Call<Response> call = api.getPostDetail(perlink+".json");

        call.enqueue(new Callback<Response >() {
            @Override
            public void onResponse(Call< Response > call, Response< Response> response) {
                onSuccessListReplies(response);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    void onSuccessListReplies(Response  commentResponse){
        if(commentResponse != null){

        }

    }

  /*  public static String getStringFromRetrofitResponse(Response response) {
        //Try to get response body
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            response.body()
            reader = new BufferedReader(new InputStreamReader(response.body().in()));

            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }*/
    public interface PostDetailView {

        void showRepliesList(List<CommentItem> commentResponse);
    }
}
