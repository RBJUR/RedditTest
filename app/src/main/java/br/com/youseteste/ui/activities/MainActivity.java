package br.com.youseteste.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import br.com.api.response.PostListResponse;
import br.com.youseteste.R;
import br.com.youseteste.adapter.PostListAdapter;
import br.com.youseteste.application.App;
import br.com.youseteste.presenter.PostListPresenter;

public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        replaceListFragment();


    }

    private void bindViews() {
        
    }

    private void replaceListFragment() {

    }


}
