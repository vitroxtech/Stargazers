package vitrox.squaring.stargazers.DependencyInjection.Module;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vitrox.squaring.stargazers.Adapters.StargazersAdapter;
import vitrox.squaring.stargazers.App;


@Module
public class ApplicationModule {

    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public Context appContext() {
        return mApp;
    }

    @Provides
    public StargazersAdapter stargazersAdapter(Context context){return new StargazersAdapter(context);}
}
