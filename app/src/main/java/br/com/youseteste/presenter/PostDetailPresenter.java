package br.com.youseteste.presenter;

import java.util.List;

import javax.inject.Inject;

import br.com.api.general.RestfulApi;
import br.com.api.response.comments.ComentItem;
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

    public void doRequestReplies() {

        Call<List<ComentItem>> call = api.getPostDetail("/r/Android/comments/60u8sw/how_to_get_a_vertual_us_phone_no/.json");

        call.enqueue(new Callback< List<ComentItem> >() {
            @Override
            public void onResponse(Call< List<ComentItem> > call, Response< List<ComentItem> > response) {
                onSuccessListReplies(response.body());
            }

            @Override
            public void onFailure(Call< List<ComentItem> > call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    void onSuccessListReplies(List<ComentItem>  commentResponse){
        if(commentResponse != null && commentResponse.size() > 0){

        }

    }
    public interface PostDetailView {

        void showRepliesList(List<ComentItem> commentResponse);
    }
}
