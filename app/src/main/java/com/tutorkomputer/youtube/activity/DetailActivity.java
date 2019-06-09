package com.tutorkomputer.youtube.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tutorkomputer.youtube.R;
import com.tutorkomputer.youtube.retrofit.Constant;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle,txtDesc,txtMore;
    Button button;
    ImageView imageView;
    Bundle bundle;

    private int line_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bundle= getIntent().getExtras();

        txtTitle=findViewById(R.id.txtTitle);
        txtDesc=findViewById(R.id.txtDesc);
        txtMore=findViewById(R.id.txtMore);
        button=findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);

        Picasso.get().load(bundle.getString(Constant.INTENT_BACKDROP))
                     .placeholder(R.drawable.ic_launcher_foreground)
                     .into(imageView);

        txtTitle.setText(bundle.getString(Constant.INTENT_TITLE));
        txtDesc.setText(bundle.getString(Constant.INTENT_DESCRIPTION));

        txtDesc.post(new Runnable() {
            @Override
            public void run() {
                line_count =txtDesc.getLineCount();

            }
        });

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtDesc.getMaxLines() <= 2){
                  txtMore.setText("Less");
                  txtDesc.setMaxLines(line_count);
                }else {
                    txtMore.setText(",,,More");
                    txtDesc.setMaxLines(2);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,TrailerActivity.class));
            }
        });


        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
