package com.tutorkomputer.youtube.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.tutorkomputer.youtube.R;
import com.tutorkomputer.youtube.adapter.VideoAdapter;
import com.tutorkomputer.youtube.model.Video;
import com.tutorkomputer.youtube.retrofit.Api;
import com.tutorkomputer.youtube.retrofit.Apiinterface;
import com.tutorkomputer.youtube.retrofit.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;

   public static YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getVideo();


        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youtubeFragment.initialize("AIzaSyCX7PNwTTk4yyhd8BePmLStn_ecRHbEnhc",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer player, boolean b) {
                       youTubePlayer=player;
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
        getSupportActionBar().setTitle(Constant.MOVIE_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    private void getVideo(){
        Apiinterface apiinterface = Api.getUrl().create(Apiinterface.class);
        Call<Video> call =apiinterface.getVideo(Constant.MOVIE_ID, Constant.KEY);

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
               Log.e("_andi",response.toString());
                  Video video= response.body();
                 List<Video.Results> results =video.getResults();

                recyclerView.setAdapter(new VideoAdapter(results,TrailerActivity.this));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {

            }
        });
    }

}