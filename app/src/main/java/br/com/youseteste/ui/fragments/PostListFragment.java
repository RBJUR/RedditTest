package br.com.youseteste.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import br.com.api.response.ChildrenResponse;
import br.com.api.response.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
import br.com.youseteste.helper.ToolbarHelper;
import br.com.youseteste.presenter.PostListPresenter;
import br.com.youseteste.ui.activities.MainActivity;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostListFragment extends Fragment implements PostListPresenter.PostListView {

    private PostListPresenter presenter;
    private PostListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private int bottomNavigationY;

    private BottomNavigationView bottomNavigationView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private PostListResponse postListResponse;

    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_list, container, false);

        bindViews(rootView);
        setupToolbar();
        initPresenter();
        doRequestPostList();

        recycleViewScrollListener();


        return rootView;
    }

    private void setupToolbar() {
        ToolbarHelper.setup((AppCompatActivity) getActivity(), toolbar, false, false, getResources().getString(R.string.app_name));

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
            bottomNavigationView.animate().setDuration(50).translationY(bottomNavigationY + bottomNavigationView.getHeight() * 4).start();
        } else {
            bottomNavigationView.animate().setDuration(1000).translationY(0).start();

        }
    }

    public static Fragment newInstance() {
        return new PostListFragment();

    }

    private void bindViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        bottomNavigationY = (int) bottomNavigationView.getTranslationY();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

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

        mRecyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.getItemAnimator().setAddDuration(600);

        bottomNavigationView.bringToFront();

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

            ChildrenResponse itemList = postListResponse.getDataResponse().getChildrenResponse().get(position);

            String url = "";
            if (itemList.getListItemResponse().getPreview() != null && itemList.getListItemResponse().getPreview().getImages().size() > 0 &&
                    itemList.getListItemResponse().getPreview().getImages().get(0).getSource() != null) {
                url = itemList.getListItemResponse().getPreview().getImages().get(0).getSource().getUrl();
            }
            bundle.putString("url", url);

            postDetailFragment.setArguments(bundle);
            ((MainActivity) getContext()).showFragmentWithTransition(this, postDetailFragment, "postDetail", view, "transition" + position);
        }
    }
}


