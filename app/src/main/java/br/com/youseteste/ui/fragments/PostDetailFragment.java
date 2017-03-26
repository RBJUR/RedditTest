package br.com.youseteste.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.api.response.comments.CommentItem;
import br.com.youseteste.R;
import br.com.youseteste.presenter.PostDetailPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostDetailFragment  extends Fragment implements PostDetailPresenter.PostDetailView {

    @BindView(R.id.item_post_img)
    ImageView coverImageView;

    private Bundle bundle;
    private PostDetailPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        initPresenter();
        initializeBundle();
        setCoverImage();



    }

    private void initPresenter() {
        presenter = new PostDetailPresenter(this);
        presenter.doRequestReplies();

    }

    private void initializeBundle() {
        bundle = getArguments();
    }

    private void setCoverImage() {
        if (bundle != null) {
            String transitionName = bundle.getString("transitionName");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                coverImageView.setTransitionName(transitionName);
            }

            if (!bundle.getString("url").isEmpty())
                Picasso.with(getActivity()).load(bundle.getString("url")).into(coverImageView);
            else
                coverImageView.setImageDrawable(null);

        }
    }

    public static Fragment newInstance() {
        return new PostDetailFragment();

    }

    @Override
    public void showRepliesList(List<CommentItem> commentResponse) {

    }
}
