package effexor.roman.nikonovich.app;


import android.app.Application;

import com.crashlytics.android.Crashlytics;

import effexor.roman.nikonovich.injection.AppComponent;
import effexor.roman.nikonovich.injection.AppModule;
import effexor.roman.nikonovich.injection.DaggerAppComponent;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;


public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

        Realm.init(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
