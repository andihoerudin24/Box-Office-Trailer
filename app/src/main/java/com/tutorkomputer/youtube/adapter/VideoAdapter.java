package com.tutorkomputer.youtube.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorkomputer.youtube.R;
import com.tutorkomputer.youtube.activity.TrailerActivity;
import com.tutorkomputer.youtube.model.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video.Results> results;
    private Context context;

    public VideoAdapter(List<Video.Results> results,Context context){
        this.results=results;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_video,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         final Video.Results model =results.get(i);
         viewHolder.textView.setText(model.getName());
         viewHolder.textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 TrailerActivity.youTubePlayer.loadVideo(model.getId());
             }
         });


         TrailerActivity.youTubePlayer.cueVideo(results.get(0).getId());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
        }
    }
}
