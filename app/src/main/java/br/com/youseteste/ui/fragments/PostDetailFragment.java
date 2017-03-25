package br.com.youseteste.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.youseteste.R;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class PostDetailFragment  extends Fragment{

    private ImageView coverImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        coverImageView = (ImageView) rootView.findViewById(R.id.item_post_img);

        Bundle b = getArguments();
        if (b != null) {
            String transitionName = b.getString("transitionName");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                coverImageView.setTransitionName(transitionName);
            }

            if (!b.getString("url").isEmpty())
                Picasso.with(getActivity()).load(b.getString("url")).into(coverImageView);
            else
                coverImageView.setImageDrawable(null);

        }

        return rootView;
    }


    public static Fragment newInstance() {
        return new PostDetailFragment();

    }
}
