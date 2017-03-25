package br.com.youseteste.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import br.com.youseteste.R;

/**
 * Created by roquebuarque on 24/03/17.
 */

public class PostDetailActivity extends AppCompatActivity {

    private final static String EXTRA_IMAGE = "EXTRA_IMAGE";

    private ImageView coverImageView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        bindViews();
    }

    private void bindViews() {
        coverImageView = (ImageView) findViewById(R.id.item_post_img);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            coverImageView.setTransitionName(getString(R.string.activity_text_trans));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
