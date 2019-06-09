package com.tutorkomputer.youtube.retrofit;

import com.tutorkomputer.youtube.model.Movie;
import com.tutorkomputer.youtube.model.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apiinterface {


    @GET("movie/{category}")
    Call<Movie> getMovie(
         @Path("category") String movie_category,
         @Query("api_key")  String key,
         @Query("language") String language,
         @Query("page")     String page
    );


    @GET("movie/{id}/videos")
    Call<Video> getVideo(
        @Path("id")       String movie_id,
        @Query("api_key") String api_key
    );


}
