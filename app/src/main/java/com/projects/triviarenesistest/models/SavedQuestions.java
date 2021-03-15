package com.projects.triviarenesistest.models;

public class SavedQuestions {
    private int id;
    private String question;
    private String yourAns;
    private String correctAns;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getYourAns() {
        return yourAns;
    }

    public void setYourAns(String yourAns) {
        this.yourAns = yourAns;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }
}
