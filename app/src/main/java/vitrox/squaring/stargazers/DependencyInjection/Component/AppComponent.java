package vitrox.squaring.stargazers.DependencyInjection.Component;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import vitrox.squaring.stargazers.Adapters.StargazersAdapter;
import vitrox.squaring.stargazers.DependencyInjection.Module.ApplicationModule;
import vitrox.squaring.stargazers.Network.ApiService;
import vitrox.squaring.stargazers.Network.ServiceModule;


@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class})
public interface AppComponent {

    Context appContext();

    ApiService apiService();

    OkHttpClient okHttpClient();

    StargazersAdapter stargazersAdapter();
}
