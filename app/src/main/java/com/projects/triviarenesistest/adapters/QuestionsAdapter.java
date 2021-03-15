package com.projects.triviarenesistest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.projects.triviarenesistest.R;
import com.projects.triviarenesistest.models.SavedQuestions;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private List<SavedQuestions> questionsList;
    public QuestionsAdapter(List<SavedQuestions> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_details_item, parent, false);
        return new QuestionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionsViewHolder holder, int position) {
        holder.question.setText(questionsList.get(position).getQuestion());
        holder.yourAns.setText(questionsList.get(position).getYourAns());
        holder.correctAns.setText(questionsList.get(position).getCorrectAns());

    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView question;
        public AppCompatTextView yourAns;
        public AppCompatTextView correctAns;


        public QuestionsViewHolder(View view) {
            super(view);
            question = (AppCompatTextView) view.findViewById(R.id.textViewQuestion);
            yourAns = (AppCompatTextView) view.findViewById(R.id.textViewYourAns);
            correctAns = (AppCompatTextView) view.findViewById(R.id.textViewCorrectAns);

        }
    }
}


