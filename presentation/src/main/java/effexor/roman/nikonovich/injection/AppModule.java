package effexor.roman.nikonovich.injection;


import android.content.Context;

import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import effexor.roman.nikonovich.data.repostitory.GetChooseRepositoryImpl;
import effexor.roman.nikonovich.data.repostitory.SearchVehicleRepositoryImpl;
import effexor.roman.nikonovich.data.restApi.RestAPI;
import effexor.roman.nikonovich.data.restApi.RestService;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import effexor.roman.nikonovich.executor.UIThread;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String BASE_URL = "https://firebasestorage.googleapis.com/v0/b/huckster-9dc11.appspot.com/o/";
    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public SearchVehicleRepository getRepositoryVehicle(Context context) {
        return new SearchVehicleRepositoryImpl(context);
    }

    @Provides
    @Singleton
    public PostExecutionThread getExecution() {
        return new UIThread();
    }

    @Provides
    @Singleton
    public GetChooseRepository getRepository(RestService restService) {
        return new GetChooseRepositoryImpl(restService);
    }

    @Provides
    @Singleton
    public RestAPI getRestApi(Retrofit retrofit) {
        return retrofit.create(RestAPI.class);
    }

    @Provides
    @Singleton
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .baseUrl(BASE_URL)
                .build();
    }

}
