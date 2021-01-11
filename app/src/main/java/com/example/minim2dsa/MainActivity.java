package com.example.minim2dsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    APIInterface APIIface;
    private String username;
    private GithubUser user;
    private List<Repository> repositories = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final Intent getUser_intent = getIntent();
        username = getUser_intent.getStringExtra("username");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        APIIface = APIClient.getClient().create(APIInterface.class);
        getUser(username);
        getRepos(username);
    }

    public void getUser(final String username){
        Call<GithubUser> call = APIIface.getUser(username);
        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    TextView usernametext = findViewById(R.id.textView3);
                    TextView following = findViewById(R.id.textView4);
                    TextView followers = findViewById(R.id.textView5);
                    ImageView userImage = findViewById(R.id.imageView);

                    user = response.body();
                    String Username = "Username: " + user.getLogin();
                    String Followers = "Followers: " + user.getFollowers();
                    String Following = "Following: " + user.getFollowing();

                    usernametext.setText(Username);
                    followers.setText(Followers);
                    following.setText(Following);
                    Picasso.get().load(user.getAvatar_url()).into(userImage);
                }
                if (!response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable throwable) {
                call.cancel();
                Toast.makeText(getApplicationContext(), "USER NOT FOUND", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getRepos(final String username) {
        Call<List<Repository>> call = APIIface.getRepositories(username);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (!response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "USER NOT FOUND", Toast.LENGTH_LONG).show();
                }
                if (response.isSuccessful()) {
                    repositories = response.body();
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    myAdapter = new MyRecyclerViewAdapter(repositories);
                    recyclerView.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable throwable) {
                call.cancel();
                Toast.makeText(getApplicationContext(), "USER NOT FOUND", Toast.LENGTH_LONG).show();
            }
        });
    }
}