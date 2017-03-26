package br.com.youseteste.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import br.com.api.response.posts.ChildrenResponse;
import br.com.api.response.posts.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
import br.com.youseteste.helper.ToolbarHelper;
import br.com.youseteste.presenter.PostListPresenter;
import br.com.youseteste.ui.activities.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostListFragment extends Fragment implements PostListPresenter.PostListView {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.post_list_container_bottom)
    CardView containerBottom;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private PostListResponse postListResponse;
    private PostListAdapter mAdapter;
    private int bottomNavigationY;
    private PostListPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        bindViews();
        swipeRefreshListener();
        setupToolbar();
        initPresenter();
        doRequestPostList();
        recycleViewScrollListener();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
    }



    @OnClick(R.id.post_list_image_reddit)
    void redditBottomClickListener() {
        Toast.makeText(getContext(), "imgReddit", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.post_list_image_email)
    void emailBottomClickListener() {
        Toast.makeText(getContext(), "imgEmail", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.post_list_image_info)
    void infoBottomClickListener() {
        Toast.makeText(getContext(), "imgInfo", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.post_list_image_dialer)
    void dialerBottomClickListener() {
        Toast.makeText(getContext(), "imgDialer", Toast.LENGTH_SHORT).show();
    }


    private void swipeRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRequestPostList();

            }
        });

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                    }
                                }
        );
    }

    private void setupToolbar() {
        ToolbarHelper.setup((AppCompatActivity) getActivity(), toolbar, true, true, getResources().getString(R.string.app_name));

    }

    private void recycleViewScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                bottomNavigationAnimation(false);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    bottomNavigationAnimation(false);
                } else if (dy > 0) {
                    bottomNavigationAnimation(true);
                }
            }
        });
    }

    private void bottomNavigationAnimation(boolean start) {
        if (start) {
            containerBottom.animate().setDuration(50).translationY(bottomNavigationY + containerBottom.getHeight() * 4).start();
        } else {
            containerBottom.animate().setDuration(1000).translationY(0).start();

        }
    }

    public static Fragment newInstance() {
        return new PostListFragment();

    }

    private void bindViews() {

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.getItemAnimator().setAddDuration(600);
        bottomNavigationY = (int) containerBottom.getTranslationY();
        containerBottom.bringToFront();

    }

    private void doRequestPostList() {
        presenter.doRequestListPost();
    }

    private void initPresenter() {
        presenter = new PostListPresenter(this);
    }

    @Override
    public void showListPost(PostListResponse postResponse) {
        postListResponse = postResponse;
        mAdapter = new PostListAdapter(postListResponse, getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyItemInserted(0);

    }


    /**
     * function to open the post detail fragment
     *
     * @param position post list position
     */
    public void openPostDetailFragment(int position, View view) {
        if (getContext() instanceof MainActivity) {

            PostDetailFragment postDetailFragment = (PostDetailFragment) PostDetailFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("transitionName", "transition" + position);

            String permalink = "";

            ChildrenResponse itemList = postListResponse.getDataResponse().getChildrenResponse().get(position);

            if(itemList != null && itemList.getListItemResponse().getPermalink() != null &&
                    !itemList.getListItemResponse().getPermalink().isEmpty()){
                permalink =  itemList.getListItemResponse().getPermalink();
            }

            String url = "";
            if (itemList.getListItemResponse().getPreview() != null && itemList.getListItemResponse().getPreview().getImages().size() > 0 &&
                    itemList.getListItemResponse().getPreview().getImages().get(0).getSource() != null) {
                url = itemList.getListItemResponse().getPreview().getImages().get(0).getSource().getUrl();
            }
            bundle.putString("url", url);
            bundle.putString("permalink", permalink);

            postDetailFragment.setArguments(bundle);
            ((MainActivity) getContext()).showFragmentWithTransition(this, postDetailFragment, "postDetail", view, "transition" + position);
        }
    }
}


