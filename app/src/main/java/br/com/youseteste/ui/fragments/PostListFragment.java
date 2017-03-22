package br.com.youseteste.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.api.response.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
import br.com.youseteste.presenter.PostListPresenter;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostListFragment extends Fragment implements PostListPresenter.PostListView {

    private PostListPresenter presenter;
    private PostListAdapter mAdapter;
    private RecyclerView mRecyclerView;

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

    }

    private void doRequestPostList() {
        presenter.doRequestListPost();
    }

    private void initPresenter() {
        presenter = new PostListPresenter(this);
    }

    @Override
    public void showListPost(PostListResponse postResponse) {
        mAdapter = new PostListAdapter(postResponse);
        mRecyclerView.setAdapter(mAdapter);

    }
}


