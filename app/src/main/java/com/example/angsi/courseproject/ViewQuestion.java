package com.example.angsi.courseproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.angsi.courseproject.Database.DatabaseHelper;
import com.example.angsi.courseproject.custom.Question;

import java.util.List;

public class ViewQuestion extends AppCompatActivity {
    private static DatabaseHelper db;
    LinearLayout linearLayout;
    Question question;
    Button back;
    List<Question> questionList;
    public TextView textView[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);
        initializeComponents();
        showQuestions();
    }

    private void initializeComponents()
    {

        db = new DatabaseHelper(getApplicationContext());
        linearLayout = (LinearLayout)findViewById(R.id.container);
        back = (Button)findViewById(R.id.viewQuestionBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void showQuestions()
    {
        questionList = db.showAllQuestions();
        textView = new TextView[questionList.size()];
        int index = 0;
        String correct="";

        Log.i("question list size", new String(String.valueOf(questionList.size())));
        while (index < questionList.size())
        {
            //textView =  new TextView(this);
            textView[index] = new TextView(this);
            question = questionList.get(index);

            switch(question.getCorrectAnswerIndex())
            {
                case 0:
                    correct = "A";
                    break;
                case 1:
                    correct = "B";
                    break;
                case 2:
                    correct = "C";
                    break;
                case 3:
                    correct = "D";
                    break;
            }

            textView[index].setText(question.getQuestion() + "\n (A) " + question.getChoiceA()
                    + "\n (B) " + question.getChoiceB() + "\n (C) " + question.getChoiceC() + "\n (D) " + question.getChoiceD() + "\n Correct Answer: "
                    + correct + "\n" +
                    "");
            final int tempIndex = index;

            textView[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView[tempIndex].setBackgroundColor(Color.GRAY);
                    Intent questionIntent = new Intent(getApplicationContext(), EditQuestion.class);
                    Bundle questionBundle = new Bundle();
                    questionBundle.putInt("index", tempIndex);
                    questionIntent.putExtra("bundle", questionBundle);
                    finish();
                    startActivity(questionIntent);

                }
            });

            textView[index].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Question question = questionList.get(tempIndex);
                    db.deleteQuestion(question);

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    return true;
                }
            });

            linearLayout.addView(textView[index]);

            index ++;

        }

    }

}
