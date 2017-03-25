package br.com.youseteste.dagger;

import android.content.Context;

import javax.inject.Singleton;

import br.com.youseteste.application.App;
import dagger.Module;
import dagger.Provides;

/**
 * Created by roque on 24/03/17.
 */

@Module
public class ApplicationModule {

    private App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}