package br.com.youseteste.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;

import br.com.api.response.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
import br.com.youseteste.application.App;
import br.com.youseteste.presenter.PostListPresenter;
import br.com.youseteste.ui.fragments.PostListFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment mFragment;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplicationContext();

        bindViews();
        replaceListFragment();


    }

    private void bindViews() {
        mFrameLayout = (FrameLayout) findViewById(R.id.main_fragment);
    }

    private void replaceListFragment() {
        FragmentManager manager = getSupportFragmentManager();
        mFragment = PostListFragment.newInstance();
        if (mFragment != null)
            manager.beginTransaction().replace(R.id.main_fragment, mFragment).commit();

    }


}
