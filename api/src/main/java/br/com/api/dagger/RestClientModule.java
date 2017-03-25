package br.com.api.dagger;

import android.content.Context;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import br.com.api.general.RestfulApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by roque on 24/03/2017.
 */
@Module
public class RestClientModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {

        return new Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.reddit.com/r/Android/new/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(final Context context) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Response response = chain.proceed(chain.request().newBuilder()
                                .build());

                        return response;
                    }
                })
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        return client.build();
    }

    @Provides
    @Singleton
    public RestfulApi provideRestfullApi(Retrofit retrofit) {
        return retrofit.create(RestfulApi.class);
    }
}
