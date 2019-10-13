package cdn.retrofito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdn.retrofito.adapters.RepoAdapter;
import cdn.retrofito.model.Repos;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String API_BASE_URL = "https://api.github.com/";

    private Retrofit retrofit;
    private List<Repos> reposList;
    private RecyclerView.Adapter adapter;
    private TextView authorName;
    private ImageView authorImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        authorName = findViewById(R.id.authorName);
        authorImg = findViewById(R.id.authorImg);

        setupRetrofit();


        GithubClient client = retrofit.create(GithubClient.class);

        Call<List<Repos>> call = client.reposForUser("anburocky3");

        call.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                reposList = response.body();

                adapter = new RepoAdapter(reposList, getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {

            }
        });

    }

    private void setupRetrofit() {
        OkHttpClient.Builder  httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();



    }
}
