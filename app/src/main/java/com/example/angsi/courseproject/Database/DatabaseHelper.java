package com.example.angsi.courseproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.angsi.courseproject.custom.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angsi on 2016/4/20.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "questionBank.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  Questions(Question VARCHAR, Answer1 VARCHAR, Answer2 VARCHAR, Answer3 VARCHAR, Answer4 VARCHAR, CorrectAnswer INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CREATE TABLE IF NOT EXISTS Questions(Question VARCHAR, Answer1 VARCHAR, Answer2 VARCHAR, Answer3 VARCHAR, Answer4 VARCHAR, CorrectAnswer INT); ");
        onCreate(db);
    }

    public void addQuestion(Question newQuestion) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put("Question", newQuestion.getQuestion());
        values.put("Answer1", newQuestion.getChoiceA());
        values.put("Answer2", newQuestion.getChoiceB());
        values.put("Answer3", newQuestion.getChoiceC());
        values.put("Answer4", newQuestion.getChoiceD());
        values.put("CorrectAnswer", newQuestion.getCorrectAnswerIndex());

        // insert row in students table

         db.insert("Questions", null, values);
        Log.i("insertQuestion", "Sucessfully added question to database" + newQuestion.getQuestion());
        String testing = "SELECT * FROM QUESTIONS;";


        Cursor cursor = db.rawQuery(testing, null);

        if (cursor.moveToFirst()) {
           // cursor.moveToFirst();

            Log.i("databaseData", cursor.getString(cursor.getColumnIndex("Question")));

            cursor.close();
        }
        db.close();


    }

    public void editQuestion(Question edited, Question raw)
    {
        Log.i("aaa",raw.getQuestion());
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("QUESTIONS", "Question='" + raw.getQuestion() + "'", null);
        addQuestion(edited);
        db.close();

    }

    public void deleteQuestion(Question toDelete)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("QUESTIONS", "Question='" + toDelete.getQuestion() + "'", null);
        db.close();
    }


    public List<Question> showAllQuestions()
    {
        List<Question> questionList = new ArrayList<Question>();
        Question question = new Question();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM QUESTIONS;";
        Cursor cursor = db.rawQuery(query, null);

        // first data

        if (cursor.moveToFirst())
        {
            question.setQuestion(cursor.getString(cursor.getColumnIndex("Question")));
            question.setChoiceA(cursor.getString(cursor.getColumnIndex("Answer1")));
            question.setChoiceB(cursor.getString(cursor.getColumnIndex("Answer2")));
            question.setChoiceC(cursor.getString(cursor.getColumnIndex("Answer3")));
            question.setChoiceD(cursor.getString(cursor.getColumnIndex("Answer4")));
            question.setCorrectAnswerIndex(cursor.getInt(cursor.getColumnIndex("CorrectAnswer")));
            questionList.add(question);
            Log.i("show first",question.getQuestion());
        }



        while (cursor.moveToNext() == true)
        {
            question = new Question();

            question.setQuestion(cursor.getString(cursor.getColumnIndex("Question")));
            question.setChoiceA(cursor.getString(cursor.getColumnIndex("Answer1")));
            question.setChoiceB(cursor.getString(cursor.getColumnIndex("Answer2")));
            question.setChoiceC(cursor.getString(cursor.getColumnIndex("Answer3")));
            question.setChoiceD(cursor.getString(cursor.getColumnIndex("Answer4")));
            question.setCorrectAnswerIndex(cursor.getInt(cursor.getColumnIndex("CorrectAnswer")));
            questionList.add(question);
            Log.i("add to list", question.getQuestion());
        }

        db.close();

        return questionList;
    }

    public Question showFirstQuestions()
    {
        List<Question> questionList = null;
        Question question = new Question();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM QUESTIONS;";
        Cursor cursor = db.rawQuery(query, null);

        // first data
        cursor.moveToFirst();
        question.setQuestion(cursor.getString(cursor.getColumnIndex("Question")));
        question.setChoiceA(cursor.getString(cursor.getColumnIndex("Answer1")));
        question.setChoiceB(cursor.getString(cursor.getColumnIndex("Answer2")));
        question.setChoiceC(cursor.getString(cursor.getColumnIndex("Answer3")));
        question.setChoiceD(cursor.getString(cursor.getColumnIndex("Answer4")));
        question.setCorrectAnswerIndex(cursor.getInt(cursor.getColumnIndex("CorrectAnswer")));
        //questionList.add(question);



        db.close();

        return question;
    }

}
