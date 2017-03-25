package br.com.youseteste.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.com.api.response.ChildrenResponse;
import br.com.api.response.PostListResponse;
import br.com.component.animation.ZoomAnimation;
import br.com.youseteste.R;
import br.com.youseteste.ui.fragments.PostListFragment;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PostListResponse postResponse;
    private Activity activity;
    private PostListFragment fragment;

    public PostListAdapter(PostListResponse postData, Activity activity, PostListFragment frag) {
        this.postResponse = postData;
        this.activity = activity;
        this.fragment = frag;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemCardListViewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ItemCardListViewHolder(itemCardListViewHolder);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        final ChildrenResponse itemList = postResponse.getDataResponse().getChildrenResponse().get(position);

        if (itemList != null) {
            ((ItemCardListViewHolder) holder).pos = position;
            TextView txName = ((ItemCardListViewHolder) holder).mNameTime;
            TextView txDescription = ((ItemCardListViewHolder) holder).mDescription;
            final ImageView img = ((ItemCardListViewHolder) holder).imgPost;

            txDescription.setText(itemList.getListItemResponse().getTitle());
            txName.setText(itemList.getListItemResponse().getAuthor());

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    if ((bitmap != null) && (((ItemCardListViewHolder) holder).pos == position))
                        img.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    if ((errorDrawable != null) && (((ItemCardListViewHolder) holder).pos == position))
                        img.setImageDrawable(errorDrawable);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    if ((placeHolderDrawable != null) && (((ItemCardListViewHolder) holder).pos == position))
                        img.setImageDrawable(placeHolderDrawable);
                }
            };

            img.setTag(target);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                img.setTransitionName("transition" + position);
            }

            if (itemList.getListItemResponse().getPreview() != null &&
                    itemList.getListItemResponse().getPreview().getImages() != null &&
                    itemList.getListItemResponse().getPreview().getImages().size() > 0) {
                Picasso.with(img.getContext()).load(itemList.getListItemResponse().getPreview().getImages().get(itemList.getListItemResponse().getPreview().getImages().size() - 1).getSource().getUrl()).into(target);
            } else {
                img.setImageDrawable(null);
                img.setVisibility(View.GONE);
            }

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //fragment.openPostDetailFragment(position, img);
                    ZoomAnimation zoomAnimation = new ZoomAnimation(activity);
                    zoomAnimation.zoom(view, 600);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return postResponse != null ? postResponse.getDataResponse().getChildrenResponse().size() : 0;
    }

    public class ItemCardListViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameTime, mDescription;
        public ImageView imgPost;
        private int pos = -1;


        public ItemCardListViewHolder(View itemView) {

            super(itemView);

            mNameTime = ((TextView) itemView.findViewById(R.id.item_post_txt_name_time));
            mDescription = ((TextView) itemView.findViewById(R.id.item_post_txt_description));
            imgPost = (ImageView) itemView.findViewById(R.id.item_post_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.openPostDetailFragment(getAdapterPosition(), v.findViewById(R.id.item_post_img));
                }
            });


        }

    }


}