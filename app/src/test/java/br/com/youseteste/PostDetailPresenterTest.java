package br.com.youseteste;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;


import br.com.api.general.RestfulApi;
import br.com.api.response.comments.CommentItem;
import br.com.youseteste.presenter.PostDetailPresenter;
import okhttp3.ResponseBody;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by roquebuarque on 27/03/17.
 */

public class PostDetailPresenterTest {

    @Mock
    RestfulApi api;

    @Mock
    Context context;

    @Mock
    ResponseBody responseBody;

    @Mock
    List<CommentItem> posts;

    @Mock
    public PostDetailPresenter.PostDetailView view;

    private PostDetailPresenter presenter;

    @Before
    public void setup() {
        initMocks(this);
        presenter = spy(new PostDetailPresenter(view));
        doNothing().when(presenter).doRequestGetComments("/r/Android/comments/60u5vj/huawei_reportedly_in_talks_with_att_over_us/");
    }

    @Test
    public void when_presenter_call_comment_list() {
        presenter.getComments(anyString());

        verify(view).showLoading(true);
        verify(presenter).doRequestGetComments(anyString());
    }

    @Test
    public void when_presenter_call_comment_list_return_success() {
        presenter.onSuccessListReplies(responseBody);

        verify(view).showLoading(false);
        verify(view).showRepliesList(posts);
    }

    @Test
    public void when_presenter_call_comment_list_return_error() {
        presenter.onFailureReplies();

        verify(view).showLoading(false);
        verify(view).showRetryDialog();
    }
}
