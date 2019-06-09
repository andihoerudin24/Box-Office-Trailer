package com.tutorkomputer.youtube.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.tutorkomputer.youtube.R;
import com.tutorkomputer.youtube.adapter.MovieAdapter;
import com.tutorkomputer.youtube.model.Movie;
import com.tutorkomputer.youtube.retrofit.Api;
import com.tutorkomputer.youtube.retrofit.Apiinterface;
import com.tutorkomputer.youtube.retrofit.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getMovie("popular");

    }


    private void getMovie(String category){
        Apiinterface apiinterface = Api.getUrl().create(Apiinterface.class);
        Call<Movie> call =apiinterface.getMovie(category,Constant.KEY,"en-US","1");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                List<Movie.Results> results=movie.getResults();

                recyclerView.setAdapter(new MovieAdapter(results,MainActivity.this));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();

        if (id==R.id.action_popular){
            getSupportActionBar().setTitle("Movie Popular");
            getMovie("popular");
            return true;
        }else if(id==R.id.action_playing){
            getSupportActionBar().setTitle("Movie Now Playing");
            getMovie("now_playing");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
