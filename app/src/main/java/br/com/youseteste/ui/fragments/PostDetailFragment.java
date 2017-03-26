package br.com.youseteste.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.api.response.comments.CommentItem;
import br.com.youseteste.R;
import br.com.youseteste.helper.ToolbarHelper;
import br.com.youseteste.presenter.PostDetailPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostDetailFragment extends Fragment implements PostDetailPresenter.PostDetailView {

    @BindView(R.id.item_post_img)
    ImageView coverImageView;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    private String permalink;

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

        initializeBundle();
        setCoverImage();
        initPresenter();


        ToolbarHelper.setup((AppCompatActivity) getActivity(), toolbar, true, true, getResources().getString(R.string.empty));

        toolbar.bringToFront();

    }

    private void initPresenter() {
        presenter = new PostDetailPresenter(this);
        presenter.doRequestReplies(permalink);

    }

    private void initializeBundle() {
        bundle = getArguments();
    }

    private void setCoverImage() {
        if (bundle != null) {


            permalink = bundle.getString("permalink");
            String transitionName = bundle.getString("transitionName");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                coverImageView.setTransitionName(transitionName);
            }

            if (!bundle.getString("url").isEmpty()) {
                Picasso.with(getActivity()).load(bundle.getString("url")).into(coverImageView);
            } else {
                coverImageView.setVisibility(View.GONE);
                coverImageView.setImageDrawable(null);
            }

        }
    }

    public static Fragment newInstance() {
        return new PostDetailFragment();

    }

    @Override
    public void showRepliesList(List<CommentItem> commentResponse) {

    }
}
