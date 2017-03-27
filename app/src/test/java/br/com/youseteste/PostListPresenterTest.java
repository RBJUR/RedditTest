package br.com.youseteste;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.api.general.RestfulApi;
import br.com.api.response.posts.PostListResponse;
import br.com.youseteste.presenter.PostListPresenter;
import retrofit2.Response;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by roquebuarque on 27/03/17.
 */

public class PostListPresenterTest {

    @Mock
    RestfulApi api;

    @Mock
    Context context;

    @Mock
    Response<PostListResponse> response;

    @Mock
    PostListResponse postListResponse;

    @Mock
    public PostListPresenter.PostListView view;

    private PostListPresenter presenter;

    @Before
    public void setup() {
        initMocks(this);
        presenter = spy(new PostListPresenter(view));
        doNothing().when(presenter).doRequestListPost();
    }

    @Test
    public void when_presenter_call_list_post() {
        presenter.getListPost();

        verify(view).showLoading(true);
        verify(presenter).doRequestListPost();
    }

    @Test
    public void when_presenter_call_list_post_return_success() {
        presenter.onSuccessListPost(response);

        verify(view).showLoading(false);
        verify(view).showListPost(postListResponse);
    }

    @Test
    public void when_presenter_call_list_post_return_error() {
        presenter.onFailureRequestList();

        verify(view).showLoading(false);
        verify(view).showDialogRetry();
    }
}
