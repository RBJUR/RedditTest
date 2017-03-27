package br.com.youseteste.ui.fragments;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.api.response.comments.CommentItem;
import br.com.component.animation.ZoomAnimation;
import br.com.youseteste.R;
import br.com.youseteste.adapter.CommentListAdapter;
import br.com.youseteste.helper.ToolbarHelper;
import br.com.youseteste.presenter.PostDetailPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostDetailFragment extends Fragment implements PostDetailPresenter.PostDetailView {

    @BindView(R.id.item_post_img)
    ImageView coverImageView;

    @BindView(R.id.item_post_txt_description)
    TextView txtTitle;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.detail_post_recycler_view_replies)
    RecyclerView recyclerView;

    @BindView(R.id.post_detail_progress)
    ProgressBar progress;


    private CommentListAdapter adapter;
    private String permalink, title, url;

    private Bundle bundle;
    private PostDetailPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        initializeBundle();
        setCoverImage();
        initPresenter();

        initializeRecycleView();

        ToolbarHelper.setup((AppCompatActivity) getActivity(), toolbar, true, true, getResources().getString(R.string.empty));

        toolbar.bringToFront();

    }

    private void initializeRecycleView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void initPresenter() {
        presenter = new PostDetailPresenter(this);
        presenter.getComments(permalink);

    }

    private void initializeBundle() {
        bundle = getArguments();
    }

    private void setCoverImage() {
        if (bundle != null) {


            permalink = bundle.getString("permalink");
            title = bundle.getString("title");
            url = bundle.getString("postUrl");
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

            if (!title.isEmpty()) {
                txtTitle.setText(title);
            }

        }
    }

    public static Fragment newInstance() {
        return new PostDetailFragment();

    }

    @OnClick(R.id.detail_post_fab)
    void chromeTabPost() {
        if (url != null && !url.isEmpty()) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(getContext(), Uri.parse(url));
        } else {
            Toast.makeText(getContext(), "Unable to open", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.item_post_img)
    void coverImageClick() {
        ZoomAnimation zoomAnimation = new ZoomAnimation(getActivity());
        zoomAnimation.zoom(coverImageView, 600);
    }

    @Override
    public void showRepliesList(List<CommentItem> commentResponse) {
        adapter = new CommentListAdapter(commentResponse);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showRetryDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                .title("sorry")
                .content("unable to get post comments")
                .positiveText("retry")
                .negativeText("cancel").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        presenter.getComments(permalink);
                    }
                });

        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    @Override
    public void showLoading(boolean show) {
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
