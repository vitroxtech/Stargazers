package vitrox.squaring.stargazers.DependencyInjection.Component;

import dagger.Component;
import vitrox.squaring.stargazers.DependencyInjection.ActivityScope;
import vitrox.squaring.stargazers.MainActivity;


@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent{

    void inject(MainActivity activity);

}