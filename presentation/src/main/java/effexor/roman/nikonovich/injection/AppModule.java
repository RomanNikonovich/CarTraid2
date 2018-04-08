package effexor.roman.nikonovich.injection;


import android.content.Context;

import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import effexor.roman.nikonovich.data.repostitory.GetChooseRepositoryImpl;
import effexor.roman.nikonovich.data.restApi.RestAPI;
import effexor.roman.nikonovich.data.restApi.RestService;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import effexor.roman.nikonovich.executor.UIThread;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private static final String BASE_URL = "https://api.backendless.com/BF19A022-69A2-3ED3-FFD7-F67EA8461100/98E8FC21-EF0E-72F6-FF48-A89A5B17B600/";
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

 /*   @Provides
    public Realm getRealm(){
        return Realm.getDefaultInstance();
    }*/


}
