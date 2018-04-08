package effexor.roman.nikonovich.app;


import android.app.Application;

import effexor.roman.nikonovich.huckster.injection.AppComponent;
import effexor.roman.nikonovich.huckster.injection.AppModule;
import effexor.roman.nikonovich.huckster.injection.DaggerAppComponent;
import io.realm.Realm;


public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
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
