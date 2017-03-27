package br.com.youseteste.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.api.response.comments.CommentDataChildren;
import br.com.api.response.comments.CommentDataChildrenItem;
import br.com.api.response.comments.CommentItem;
import br.com.api.response.posts.ChildrenResponse;
import br.com.youseteste.R;
import br.com.youseteste.factory.ReplyViewFactory;

public class CommentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommentItem> commentItems;

    public CommentListAdapter(List<CommentItem> items) {
        commentItems = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemCardListViewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reply_view_factory, parent, false);
        return new ItemReplyListViewHolder(itemCardListViewHolder);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        final CommentItem itemList = commentItems.get(position);

        if (itemList != null) {
            TextView txName = ((ItemReplyListViewHolder) holder).mNameTime;
            TextView txDescription = ((ItemReplyListViewHolder) holder).mDescription;
            LinearLayout repliesContainer = ((ItemReplyListViewHolder) holder).mContainerReplies;

            if (itemList.getComentData() != null && itemList.getComentData().getComentDataChildrens() != null &&
                    itemList.getComentData().getComentDataChildrens().size() > 0 &&
                    itemList.getComentData().getComentDataChildrens().get(position) != null) {
                if (itemList.getComentData().getComentDataChildrens().get(position).getDataChildren() != null) {
                    txName.setText(itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getAuthor());
                    if (itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getBody() != null
                            && !itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getBody().isEmpty()) {
                        txDescription.setText(itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getBody());
                    } else if (itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getTitle() != null
                            && !itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getTitle().isEmpty()) {
                        txDescription.setText(itemList.getComentData().getComentDataChildrens().get(position).getDataChildren().getTitle());
                    }
                }
            }

            if (itemList.getComentData().getComentDataChildrens() != null &&
                    itemList.getComentData().getComentDataChildrens().size() > 0) {
                List<CommentDataChildrenItem> dataChildrenItems = itemList.getComentData().getComentDataChildrens();
                for (int pos = 0; pos < dataChildrenItems.size(); pos++) {
                    CommentDataChildrenItem dataItem = dataChildrenItems.get(pos);
                    if (dataItem != null && dataItem.getDataChildren() != null) {
                        String comment = "";
                        if (dataItem.getDataChildren().getBody() != null && !dataItem.getDataChildren().getBody().isEmpty()) {
                            comment = dataItem.getDataChildren().getBody();
                        } else if (dataItem.getDataChildren().getTitle() != null && !dataItem.getDataChildren().getTitle().isEmpty()) {
                            comment = dataItem.getDataChildren().getTitle();
                        }
                        repliesContainer.addView(ReplyViewFactory.inflateFutureOptionView(repliesContainer.getContext(), dataItem.getDataChildren().getAuthor(), comment));
                        if (dataItem.getDataChildren().getRepliesData() != null &&
                                dataItem.getDataChildren().getRepliesData().getComentData() != null) {
                            if (dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens() != null &&
                                    dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().size() > 0) {
                                for (int pos2 = 0; pos2 < dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().size(); pos2++) {
                                    CommentDataChildrenItem replyItem = dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2);

                                    String reply = "";
                                    if (replyItem.getDataChildren().getBody() != null && !replyItem.getDataChildren().getBody().isEmpty()) {
                                        reply = replyItem.getDataChildren().getBody();
                                    } else if (replyItem.getDataChildren().getTitle() != null && !replyItem.getDataChildren().getTitle().isEmpty()) {
                                        reply = replyItem.getDataChildren().getTitle();
                                    }

                                    repliesContainer.addView(ReplyViewFactory.inflateFutureOptionView(repliesContainer.getContext(), replyItem.getDataChildren().getAuthor(), reply));


                                    if (dataItem.getDataChildren().getRepliesData() != null &&
                                            dataItem.getDataChildren().getRepliesData().getComentData() != null) {
                                        if (dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2) != null &&
                                                dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren() != null &&
                                                dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren().getRepliesData() != null &&
                                                dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren().getRepliesData().getComentData() != null &&
                                                dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren().getRepliesData().getComentData().getComentDataChildrens() != null &&
                                                dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren().getRepliesData().getComentData().getComentDataChildrens().size() > 0 ) {
                                            for (int pos3 = 0; pos3 < dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren().getRepliesData().getComentData().getComentDataChildrens().size(); pos3++) {
                                                CommentDataChildrenItem replyItem2 = dataItem.getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos2).getDataChildren().getRepliesData().getComentData().getComentDataChildrens().get(pos3);

                                                String reply3 = "";
                                                if (replyItem2.getDataChildren().getBody() != null && !replyItem.getDataChildren().getBody().isEmpty()) {
                                                    reply3 = replyItem.getDataChildren().getBody();
                                                } else if (replyItem2.getDataChildren().getTitle() != null && !replyItem.getDataChildren().getTitle().isEmpty()) {
                                                    reply3 = replyItem.getDataChildren().getTitle();
                                                }
                                                repliesContainer.addView(ReplyViewFactory.inflateFutureOptionView(repliesContainer.getContext(), replyItem2.getDataChildren().getAuthor(), reply3));
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            }

        }
    }

    @Override
    public int getItemCount() {
        return commentItems != null ? commentItems.size() : 0;
    }

    public class ItemReplyListViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameTime, mDescription;
        private LinearLayout mContainerReplies;


        public ItemReplyListViewHolder(View itemView) {

            super(itemView);

            mNameTime = ((TextView) itemView.findViewById(R.id.item_reply_view_factory_txt_name_time));
            mDescription = ((TextView) itemView.findViewById(R.id.item_reply_view_factory_txt_description));
            mContainerReplies = (LinearLayout) itemView.findViewById(R.id.item_reply_view_factory_container_replies);
        }

    }


}