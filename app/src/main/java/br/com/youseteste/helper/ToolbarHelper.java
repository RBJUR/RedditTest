package br.com.youseteste.helper;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.youseteste.R;

public class ToolbarHelper {

    public static final int WHITE = 0xffffffff;

    /**
     *  Toolbar setup is a method to hel all toolbar being equals
     *
     * @param activity
     * @param toolbar
     * @param backPressedListener
     * @param displayHomeAsUpEnabled
     * @param title
     * @return
     */
    public static Toolbar setup(@NonNull final AppCompatActivity activity, Toolbar toolbar, boolean backPressedListener,
                                boolean displayHomeAsUpEnabled, @NonNull String title) {
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
            assert activity.getSupportActionBar() != null;
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

            if (displayHomeAsUpEnabled)
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (backPressedListener)
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        activity.onBackPressed();
                    }

                });

            TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

            if (toolbarTitle != null) {
                toolbarTitle.setMaxLines(1);
                toolbarTitle.setEllipsize(TextUtils.TruncateAt.END);
                toolbarTitle.setText(title);

                //toolbarTitle.setGravity(Gravity.CENTER);

                Toolbar.LayoutParams params = new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                toolbarTitle.setLayoutParams(params);
            }

            toolbar.setBackgroundColor(WHITE);
        }
        return toolbar;
    }
}