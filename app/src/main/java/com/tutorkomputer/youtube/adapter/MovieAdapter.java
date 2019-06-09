package com.tutorkomputer.youtube.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tutorkomputer.youtube.activity.DetailActivity;
import com.tutorkomputer.youtube.R;
import com.tutorkomputer.youtube.model.Movie;
import com.tutorkomputer.youtube.retrofit.Constant;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public List<Movie.Results> movies;
    public Context context;

    public MovieAdapter(List<Movie.Results> movies,Context context){
        this.movies  =movies;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_movie,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         final Movie.Results model= movies.get(i);

         viewHolder.txtTitle.setText(model.getTitle());
         viewHolder.txtDate.setText(model.getRelease_date());

         Picasso.get().load(Constant.BACKDROP_PATH + model.getBackdrop_path())
                      .placeholder(R.drawable.ic_launcher_background)
                      .fit().centerCrop()
                      .into(viewHolder.imgBackdrop);

         viewHolder.imgBackdrop.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(context, DetailActivity.class);
                 intent.putExtra(Constant.INTENT_TITLE,model.getTitle());
                 intent.putExtra(Constant.INTENT_BACKDROP,Constant.BACKDROP_PATH + model.getBackdrop_path());
                 intent.putExtra(Constant.INTENT_DESCRIPTION,model.getOverview());
                 context.startActivity(intent);

                 Constant.MOVIE_ID=String.valueOf(model.getId());
                 Constant.MOVIE_TITLE=model.getTitle();
             }
         });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDate;
        ImageView imgBackdrop;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDate=itemView.findViewById(R.id.txtDate);
            imgBackdrop=itemView.findViewById(R.id.imgBackdrop);
        }
    }
}
