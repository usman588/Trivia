package com.projects.triviarenesistest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.projects.triviarenesistest.R;
import com.projects.triviarenesistest.Utills.DatabaseHelper;
import com.projects.triviarenesistest.adapters.QuestionsAdapter;
import com.projects.triviarenesistest.models.SavedQuestions;

import java.util.ArrayList;
import java.util.List;

public class QuizDetails extends AppCompatActivity {
    private RecyclerView recyclerViewQuestions;
    private List<SavedQuestions> questionsList;
    private QuestionsAdapter questionsAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);
        recyclerViewQuestions = findViewById(R.id.recyclerViewDetails);
        initObjects();


    }
    private void initObjects() {
        questionsList = new ArrayList<>();
        questionsAdapter = new QuestionsAdapter(questionsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewQuestions.setLayoutManager(mLayoutManager);
        recyclerViewQuestions.setItemAnimator(new DefaultItemAnimator());
        recyclerViewQuestions.setHasFixedSize(true);
        recyclerViewQuestions.setAdapter(questionsAdapter);
        databaseHelper = new DatabaseHelper(QuizDetails.this);

        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                questionsList.clear();
                questionsList.addAll(databaseHelper.getAllQuestions());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                questionsAdapter.notifyDataSetChanged();
            }
        }.execute();

    }

    public void finish_details(View view) {
        finish();
    }
}