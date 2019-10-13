package cdn.retrofito;

import java.util.List;

import cdn.retrofito.model.Repos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("/users/{user}/repos")
    Call<List<Repos>> reposForUser(@Path("user") String user);
}
