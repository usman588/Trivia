package com.projects.triviarenesistest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.triviarenesistest.R;
import com.projects.triviarenesistest.Utills.Constants;
import com.projects.triviarenesistest.Utills.DatabaseHelper;
import com.projects.triviarenesistest.apis.ApiCalls;
import com.projects.triviarenesistest.apis.NetworkResponseListener;
import com.projects.triviarenesistest.models.Question;
import com.projects.triviarenesistest.models.QuestionsResponse;
import com.projects.triviarenesistest.models.SavedQuestions;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements NetworkResponseListener {
    int score=0;
    int qid=0;
    int qcount=0;
    TextView txtQuestion;
    AppCompatButton opt1,opt2,opt3,opt4;
    Question currentQ, cansQ;
    ProgressBar progressBar;
    boolean qtype=true;
    ArrayList<Question> question = new ArrayList<Question>();
    ArrayList<Question> cquestion = new ArrayList<Question>();
    private  String difficulty;
    private  String type;
    private int category;
    private String categoryName;
    private DatabaseHelper databaseHelper;
    private SavedQuestions savedQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initViews();

        if (getIntent() != null){
            difficulty = getIntent().getStringExtra("difficulty");
            type = getIntent().getStringExtra("type");
            categoryName = getIntent().getStringExtra("category_name");
            category = getIntent().getIntExtra("category",0);
        }
         callApi();


        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try{
                    handleButtonClick(1);
                    opt1.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }



            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try{
                    handleButtonClick(2);
                    opt2.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }


            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    handleButtonClick(3);
                    opt3.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }




            }

        });

        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    handleButtonClick(4);
                    opt4.setAlpha(1);
                }
                catch (Exception err)
                {
                    networkDialog();
                }

            }
        });





    }
    private void initViews(){
        txtQuestion=(TextView)findViewById(R.id.textView1);
        opt1 =  findViewById(R.id.opt1);
        opt2 =  findViewById(R.id.opt2);
        opt3 =  findViewById(R.id.opt3);
        opt4 =  findViewById(R.id.opt4);
        progressBar =  findViewById(R.id.progress_questions);
        databaseHelper = new DatabaseHelper(QuizActivity.this);
        savedQuestions = new SavedQuestions();
        databaseHelper = new DatabaseHelper(QuizActivity.this);
        databaseHelper.deleteTable();

    }

    @Override
    public void onNetworkResponse(int status, int responseCode, String message, String responseForRequest, Object body) {
        switch (responseForRequest){

            case Constants.QUESTIONS:
                if (status == 200)
                {
                    progressBar.setVisibility(View.GONE);
                    if (body != null) {
                        if (responseCode ==0){
                            Log.e("status", status + "");
                            QuestionsResponse questionsResponses = (QuestionsResponse) body;

                            if (questionsResponses.getResults().size() > 0){

                                Log.e("Questions", questionsResponses.getResults().size()+"");

                                for (int i = 0; i < questionsResponses.getResults().size(); i++) {

                                    String q = questionsResponses.getResults().get(i).getQuestion();
                                    String cans = questionsResponses.getResults().get(i).getCorrectAnswer();

                                    String ians1 = questionsResponses.getResults().get(i).getIncorrectAnswers().get(0);
                                    String ians2;
                                    String ians3;
                                    if (cans.equalsIgnoreCase("True") || cans.equals("False")) {
                                        ians2 = "";
                                        ians3 = "";
                                    } else {
                                        ians2 = questionsResponses.getResults().get(i).getIncorrectAnswers().get(1);
                                        ians3 = questionsResponses.getResults().get(i).getIncorrectAnswers().get(2);
                                    }

                                    Random rand = new Random();

                                    int n = rand.nextInt(4) + 1;

                                    Question que = null;

                                    switch (n) {
                                        case 1:
                                            que = new Question(q, ians1, ians2, ians3, cans);
                                            break;
                                        case 2:
                                            que = new Question(q, ians1, ians2, cans, ians3);
                                            break;
                                        case 3:
                                            que = new Question(q, ians1, cans, ians3, ians2);
                                            break;
                                        case 4:
                                            que = new Question(q, cans, ians2, ians3, ians1);
                                            break;

                                    }
                                    Question cq = new Question(q, cans);

                                    question.add(que);
                                    cquestion.add(cq);

                                }

                            }else {
                                Toast.makeText(this, "No questions available for this category" , Toast.LENGTH_LONG).show();

                            }




                            setQuestionView();
                        }

                        else {
                            Toast.makeText(this, "Something went wrong, try again...", Toast.LENGTH_LONG).show();
                            finish();

                        }

                    }else {
                        Toast.makeText(this, "Something went wrong, try again...", Toast.LENGTH_LONG).show();
                        finish();

                    }
                }
                else
                    {
                        progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                }


                break;

        }



    }

    private void callApi() {
        progressBar.setVisibility(View.VISIBLE);
        ApiCalls.getInstance(this).FetchQuestions(10, category,difficulty, type, this);

    }

    void handleButtonClick(int b)
    {
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);



        savedQuestions.setQuestion(cquestion.get(qid-1).getQUESTION());
        savedQuestions.setCorrectAns(cquestion.get(qid-1).getANSWER());
        if(opt1.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt1.setBackgroundResource(R.drawable.option_r);
        if(opt2.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt2.setBackgroundResource(R.drawable.option_r);
        if(opt3.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt3.setBackgroundResource(R.drawable.option_r);
        if(opt4.getText().toString().equals(cquestion.get(qid-1).getANSWER()))
            opt4.setBackgroundResource(R.drawable.option_r);


        if(b==1)
        {
            savedQuestions.setYourAns(opt1.getText().toString());
            databaseHelper.addQuestion(savedQuestions);
            if (opt1.getText().toString().equals(cquestion.get(qid-1).getANSWER())){
                if (difficulty.equals("medium")){
                    score+=2;

                }else if (difficulty.equals("hard")){
                    score+=3;
                }else {
                    score++;
                }
            }

            else{
                opt1.setBackgroundResource(R.drawable.option_w);

            }
        }

        if(b==2)
        {
            savedQuestions.setYourAns(opt1.getText().toString());
            databaseHelper.addQuestion(savedQuestions);
            if (opt2.getText().toString().equals(cquestion.get(qid-1).getANSWER())){
                if (difficulty.equals("medium")){
                    score+=2;

                }else if (difficulty.equals("hard")){
                    score+=3;
                }else {
                    score++;
                }
            }

            else
            {
                opt2.setBackgroundResource(R.drawable.option_w);
            }
        }
        if(b==3)
        {
            savedQuestions.setYourAns(opt1.getText().toString());
            databaseHelper.addQuestion(savedQuestions);
            if (opt3.getText().toString().equals(cquestion.get(qid-1).getANSWER())){
                if (difficulty.equals("medium")){
                    score+=2;

                }else if (difficulty.equals("hard")){
                    score+=3;
                }else {
                    score++;
                }
            }
            else{
                opt3.setBackgroundResource(R.drawable.option_w);
            }
        }
        if(b==4)
        {
            savedQuestions.setYourAns(opt1.getText().toString());
            databaseHelper.addQuestion(savedQuestions);
            if (opt4.getText().toString().equals(cquestion.get(qid-1).getANSWER())){
                if (difficulty.equals("medium")){
                    score+=2;

                }else if (difficulty.equals("hard")){
                    score+=3;
                }else {
                    score++;
                }
            }
            else{
                opt4.setBackgroundResource(R.drawable.option_w);
            }
        }
        if(qcount==10)
        {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(QuizActivity.this,ResultActivity.class);

                    qcount=0;

                    i.putExtra("Score",score);
                    i.putExtra("difficulty",difficulty);
                    i.putExtra("CategoryName", categoryName);
                    startActivityForResult(i,1);
                    finish();


                }
            }, 1000);


        }
        else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    setQuestionView();

                }
            }, 1000);


        }
    }

    private void setQuestionView()
    {



        currentQ=question.get(qid);
        cansQ = cquestion.get(qid);

        txtQuestion.setText(Html.fromHtml(currentQ.getQUESTION()));
        opt1.setText(Html.fromHtml(currentQ.getOPTA()));
        opt2.setText(Html.fromHtml(currentQ.getOPTB()));
        opt3.setText(Html.fromHtml(currentQ.getOPTC()));
        opt4.setText(Html.fromHtml(currentQ.getANSWER()));

        if(opt1.getText().toString().equals("")) {
            opt1.setVisibility(View.GONE);
            qtype=false;
        }
        else {
            opt1.setVisibility(View.VISIBLE);
        }
        if(opt2.getText().toString().equals("")) {
            opt2.setVisibility(View.GONE);
            qtype=false;
        }
        else {
            opt2.setVisibility(View.VISIBLE);
        }
        if(opt3.getText().toString().equals("")) {
            qtype=false;
            opt3.setVisibility(View.GONE);
        }
        else {
            opt3.setVisibility(View.VISIBLE);

        }
        if(opt4.getText().toString().equals("")) {
            qtype=false;
            opt4.setVisibility(View.GONE);
        }
        else {
            opt4.setVisibility(View.VISIBLE);
        }

        opt1.setAlpha(0.5f);
        opt2.setAlpha(0.5f);
        opt3.setAlpha(0.5f);
        opt4.setAlpha(0.5f);
        opt1.setBackgroundResource(R.drawable.option_bg);
        opt2.setBackgroundResource(R.drawable.option_bg);
        opt3.setBackgroundResource(R.drawable.option_bg);
        opt4.setBackgroundResource(R.drawable.option_bg);

        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
        qcount++;
        qid++;
    }

    private void networkDialog() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setMessage("Network error occured: Retry?").setPositiveButton("Yes",dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }



    public void finish_quiz(View view) {
        finish();
    }
}