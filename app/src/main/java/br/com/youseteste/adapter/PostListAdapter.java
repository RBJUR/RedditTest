package br.com.youseteste.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;

import br.com.api.response.ChildrenResponse;
import br.com.api.response.ItemResponse;
import br.com.api.response.PostListResponse;
import br.com.component.animation.ZoomAnimation;
import br.com.youseteste.R;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PostListResponse postResponse;
    private Activity activity;

    public PostListAdapter(PostListResponse postData, Activity activity) {
        this.postResponse = postData;
        this.activity = activity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case 0: {

                View itemListTransferView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post, parent, false);
                return new ListDividerViewHolder(itemListTransferView);

            }

            case 1: {

                View itemCardListViewHolder = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post, parent, false);
                return new ItemCardListViewHolder(itemCardListViewHolder);

            }


        }

        return null;

    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder.getItemViewType() == 1) {
            ChildrenResponse itemList = postResponse.getDataResponse().getChildrenResponse().get(position);

            if (itemList != null) {

                TextView txName = ((ItemCardListViewHolder) holder).mNameTime;
                TextView txDescription = ((ItemCardListViewHolder) holder).mDescription;
                ImageView img = ((ItemCardListViewHolder) holder).imgPost;

                txDescription.setText(itemList.getListItemResponse().getTitle());
                txName.setText(itemList.getListItemResponse().getAuthor());
                if (itemList.getListItemResponse().getPreview() != null && itemList.getListItemResponse().getPreview().getImages() != null && itemList.getListItemResponse().getPreview().getImages().size() > 0) {
                    Picasso.with(img.getContext()).load(itemList.getListItemResponse().getPreview().getImages().get(0).getSource().getUrl()).into(img);
                } else {
                    img.setVisibility(View.GONE);
                }

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ZoomAnimation zoomAnimation = new ZoomAnimation(activity);
                        zoomAnimation.zoom(view, 600);
                    }
                });


            }
        }
    }

    @Override
    public int getItemCount() {
        return postResponse != null ? postResponse.getDataResponse().getChildrenResponse().size() : 0;
    }

    public static class ItemCardListViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameTime, mDescription;
        public ImageView imgPost;


        public ItemCardListViewHolder(View itemView) {

            super(itemView);

            mNameTime = ((TextView) itemView.findViewById(R.id.item_post_txt_name_time));
            mDescription = ((TextView) itemView.findViewById(R.id.item_post_txt_description));
            imgPost = (ImageView) itemView.findViewById(R.id.item_post_img);



        }

    }

    public static class ListDividerViewHolder extends RecyclerView.ViewHolder {

        public TextView label;

        public ListDividerViewHolder(View itemView) {
            super(itemView);

            label = ((TextView) itemView.findViewById(R.id.item_post_txt_description));

        }

    }
}