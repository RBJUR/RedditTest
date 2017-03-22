package br.com.youseteste.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.api.response.ChildrenResponse;
import br.com.api.response.ItemResponse;
import br.com.api.response.PostListResponse;
import br.com.youseteste.R;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PostListResponse postResponse;

    public PostListAdapter(PostListResponse postData) {
        this.postResponse = postData;
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

                View itemListTransferView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_post, parent, false);
                return new ListDividerViewHolder(itemListTransferView);

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


        if (holder.getItemViewType() == 0) {
            ChildrenResponse itemList = postResponse.getDataResponse().getChildrenResponse().get(position);

            if (itemList != null) {

                TextView txName = ((ItemCardListViewHolder) holder).mNameTime;
                TextView txDescription = ((ItemCardListViewHolder) holder).mDescription;
                ImageView img = ((ItemCardListViewHolder) holder).imgPost;


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