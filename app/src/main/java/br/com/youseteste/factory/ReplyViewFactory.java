package br.com.youseteste.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.youseteste.R;


/**
 * Created by roque on 26/03/16.
 */
public class ReplyViewFactory {

    public static View inflateFutureOptionView(final Context context, String author, String reply) {

        int view = R.layout.item_reply_view_factory;

        View paymentContainerView = LayoutInflater.from(context).inflate(view, null, true);
        ViewGroup furureView = (ViewGroup) paymentContainerView.findViewById(R.id.item_reply_view_factory_container);
        TextView txAuthor = (TextView) furureView.findViewById(R.id.item_reply_view_factory_txt_name_time);
        TextView txReply = (TextView) furureView.findViewById(R.id.item_reply_view_factory_txt_description);

        txAuthor.setText(author);
        txReply.setText(reply);
        return furureView;
    }



}