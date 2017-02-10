package vitrox.squaring.stargazers.Network;


import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import vitrox.squaring.stargazers.Model.GitResponse;


public interface ApiService {

    @GET("/repos/{owner}/{repo}/stargazers")
    Observable<List<GitResponse>> getJsonStargazers(@Path("owner") String owner,@Path("repo") String repo);

}
