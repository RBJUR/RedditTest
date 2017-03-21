package br.com.api.interfaces;

import br.com.api.request.PostListRequest;
import br.com.api.response.PostListResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by roquebuarque on 20/03/17.
 */

public interface RestfulApi {

    @POST
    Call<PostListResponse> getPosts(@Url String url, @Body PostListRequest postRequest);

    /*
      api.getUniqueGoal(url, goalUnique)
                .enqueue(new CustomCallback<GetUniqueGoalResponse>() {
                    @Override
                    public void onSuccess(GetUniqueGoalResponse body) {
                        String goalJson = gson.toJson(body.getGoal());

                        if (type == GOAL_COMPLETED) {
                            Intent intentGoalCompleted = GoalCompletedActivity.getIntent(getContext(), goalJson);
                            startActivity(intentGoalCompleted);
                        } else if (type == GOAL_DETAIL) {
                            Intent intentGoalDetail = GoalDetailActivity.getIntent(getContext(), goalJson);
                            startActivity(intentGoalDetail);
                        }
                    }

                    @Override
                    public void onFailure(int responseCode, Call<GetUniqueGoalResponse> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
     */
}
