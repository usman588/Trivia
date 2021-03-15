package com.projects.triviarenesistest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projects.triviarenesistest.R;
import com.projects.triviarenesistest.Utills.Constants;

public class ResultActivity extends AppCompatActivity {
    private String difficulty;
    private String CategoryName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final TextView cat=(TextView)findViewById(R.id.textcat);
        TextView que=(TextView)findViewById(R.id.textque);
        TextView res=(TextView)findViewById(R.id.textRes);
        final Button cont = (Button) findViewById(R.id.rcontinue);
        final 	Button details =(Button) findViewById(R.id.details);
        Bundle b = getIntent().getExtras();
        int score= b.getInt("Score");
        difficulty = b.getString("difficulty");
        CategoryName = b.getString("CategoryName");



        cat.setText(CategoryName);
        que.setText("Questions : "+10);
        if (difficulty.equals("medium")){
            res.setText("Score : "+score+" out of "+10*2);

        }else if (difficulty.equals("hard")){
            res.setText("Score : "+score+" out of "+10*3);

        }else {
            res.setText("Score : "+score+" out of "+10);

        }

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent intent = new Intent(ResultActivity.this, QuizDetails.class);
                startActivity(intent);

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }

    public void finish_results(View view) {
        finish();
    }
}