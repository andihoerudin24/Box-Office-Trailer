package com.tutorkomputer.youtube;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainModel> models;
    private Context context;

    public MainAdapter(List<MainModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_main,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

          final MainModel model = models.get(i);
          viewHolder.textView.setText(model.getTitle());
          viewHolder.textView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context,YoutubeActivity.class);
                  intent.putExtra("KEY",model.getKey());
                  intent.putExtra("TITLE",model.getTitle());
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textView =itemView.findViewById(R.id.textView);
        }
    }
}
