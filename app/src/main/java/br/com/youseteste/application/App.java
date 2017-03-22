package br.com.youseteste.application;

import android.app.Application;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        setupGraph();
    }

    private void setupGraph() {
    }


}
