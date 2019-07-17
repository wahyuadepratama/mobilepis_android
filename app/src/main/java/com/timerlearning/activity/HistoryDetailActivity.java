package com.timerlearning.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.timerlearning.R;
import com.timerlearning.model.History;

import java.util.Objects;

public class HistoryDetailActivity extends AppCompatActivity {

    TextView title, date, body;
    ImageView photo;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Detail History");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.detailTitle);
        date = findViewById(R.id.detailDate);
        body = findViewById(R.id.detailBody);
        photo = findViewById(R.id.detailPhoto);

        Intent intent = getIntent();
        if(intent != null){
            History history = intent.getParcelableExtra("data");
            title.setText(history.getTitle());
            date.setText(history.getDate());
            body.setText(history.getBody());

            if(history.getStatus().equals("on")){
                photo.setImageResource(R.drawable.on);
            }else if(history.getStatus().equals("off")){
                photo.setImageResource(R.drawable.off);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int back = item.getItemId();
        if (back == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
