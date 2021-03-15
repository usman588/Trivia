package com.projects.triviarenesistest.Utills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.projects.triviarenesistest.models.SavedQuestions;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserQuestions.db";

    // User table name
    private static final String TABLE_QUESTIONS = "User_Questions";

    // User Table Columns names
    private static final String ID = "id";
    private static final String COLUMN_QUESTION = "questions";
    private static final String COLUMN_YOUR_ANS = "your_answer";
    private static final String COLUMN_CORRECT_ANS = "correct_answer";


    // create table sql query
    private String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_QUESTION + " TEXT," +
            COLUMN_YOUR_ANS + " TEXT,"+COLUMN_CORRECT_ANS + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_QUESTIONS;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUESTION_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }

    /**
     * This method is to create questions record
     *
     * @param questions
     */
    public void addQuestion(SavedQuestions questions) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, questions.getQuestion());
        values.put(COLUMN_YOUR_ANS, questions.getYourAns());
        values.put(COLUMN_CORRECT_ANS, questions.getCorrectAns());


        // Inserting Row
        db.insert(TABLE_QUESTIONS, null, values);
        db.close();
    }

    /**
     * This method is to fetch all questions and return the list of questions records
     *
     * @return list
     */
    public List<SavedQuestions> getAllQuestions() {
        // array of columns to fetch
        String[] columns = {
                ID,
                COLUMN_QUESTION,
                COLUMN_YOUR_ANS,
                COLUMN_CORRECT_ANS

        };
        // sorting orders
        String sortOrder = ID + " ASC";
        List<SavedQuestions> questionsList = new ArrayList<SavedQuestions>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_QUESTIONS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        if (cursor.moveToFirst()) {
            do {
                SavedQuestions questions = new SavedQuestions();
                questions.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                questions.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION)));
                questions.setYourAns(cursor.getString(cursor.getColumnIndex(COLUMN_YOUR_ANS)));
                questions.setCorrectAns(cursor.getString(cursor.getColumnIndex(COLUMN_CORRECT_ANS)));

                questionsList.add(questions);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return questionsList;
    }
    public void deleteTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_QUESTIONS,null,null);
        db.close();
    }


}
