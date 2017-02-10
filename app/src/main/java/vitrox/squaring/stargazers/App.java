package vitrox.squaring.stargazers;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import vitrox.squaring.stargazers.DependencyInjection.Component.AppComponent;
import vitrox.squaring.stargazers.DependencyInjection.Component.DaggerAppComponent;
import vitrox.squaring.stargazers.DependencyInjection.Module.ApplicationModule;


public class App extends Application {
    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}
