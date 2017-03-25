package br.com.youseteste.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import br.com.api.response.ChildrenResponse;
import br.com.api.response.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
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

    private PostListResponse postListResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_list, container, false);

        bindViews(rootView);

        initPresenter();
        doRequestPostList();

        return rootView;
    }

    public static Fragment newInstance() {
        return new PostListFragment();

    }

    private void bindViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

       /* mRecyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.getItemAnimator().setAddDuration(600);*/



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

       // mAdapter.notifyItemInserted(0);

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

            String url = itemList.getListItemResponse().getPreview().getImages().get(0).getSource().getUrl();
            bundle.putString("url", url);

            postDetailFragment.setArguments(bundle);
            ((MainActivity) getContext()).showFragmentWithTransition(this, postDetailFragment, "postDetail", view, "transition" + position);
        }
    }
}


