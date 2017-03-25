package br.com.youseteste.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import br.com.api.response.ChildrenResponse;
import br.com.api.response.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
import br.com.youseteste.adapter.RecyclerItemClickListener;
import br.com.youseteste.presenter.PostListPresenter;
import br.com.youseteste.ui.activities.MainActivity;
import br.com.youseteste.ui.activities.PostDetailActivity;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.http.POST;

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

        mRecyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        mRecyclerView.getItemAnimator().setAddDuration(600);

       /* mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int viewType) {
                        Log.d("ViewId", view.getId() + "");
                        ImageView imageView = (ImageView) view.findViewById(R.id.item_post_img);
                        if (imageView != null) {
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                                // Pass data object in the bundle and populate details activity.
                                //intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
                                Pair<View, String> pair1 = Pair.create((View) imageView, imageView.getTransitionName());
                                ActivityOptionsCompat options = ActivityOptionsCompat.
                                        makeSceneTransitionAnimation(getActivity(), pair1);
                                startActivity(intent, options.toBundle());
                            }
                        }

                    }
                }));*/

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

        mAdapter.notifyItemInserted(0);

    }


    /**
     * function to open the movie detail fragment
     *
     * @param position Movie list position
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


