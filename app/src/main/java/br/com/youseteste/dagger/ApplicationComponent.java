package br.com.youseteste.dagger;

import javax.inject.Singleton;

import br.com.api.dagger.RestClientModule;
import br.com.youseteste.application.App;
import br.com.youseteste.presenter.PostListPresenter;
import dagger.Component;

/**
 * Created by roque on 24/03/17.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        RestClientModule.class
})
public interface ApplicationComponent {
    void inject(App app);

    void inject(PostListPresenter presenter);

}
