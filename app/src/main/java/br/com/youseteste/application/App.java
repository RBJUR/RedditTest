package br.com.youseteste.application;

import android.app.Application;

import br.com.api.dagger.RestClientModule;
import br.com.youseteste.dagger.ApplicationComponent;
import br.com.youseteste.dagger.ApplicationModule;
import br.com.youseteste.dagger.DaggerApplicationComponent;

/**
 * Created by roquebuarque on 21/03/17.
 */

public class App extends Application {


    private static App sInstance;
    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        setupGraph();
    }

    /**
     * Method to inject module to daggerApplication
     */
    private void setupGraph() {

            component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .restClientModule(new RestClientModule())
                    .build();

            component.inject(this);

    }


    public static synchronized App getInstance() {
        return sInstance;
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
