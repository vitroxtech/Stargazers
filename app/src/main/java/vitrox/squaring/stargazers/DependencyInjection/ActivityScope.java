package vitrox.squaring.stargazers.DependencyInjection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by miguelgomez on 6/7/16.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope
{

}