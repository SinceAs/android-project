package com.example.angsi.courseproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button toQuestionBankButton;
    Button takeQuizButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents()
    {
        toQuestionBankButton = (Button)findViewById(R.id.buttonQuestionBank);
        toQuestionBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), QuestionBankMain.class);
                startActivity(intent);
            }
        });

        takeQuizButton = (Button)findViewById(R.id.buttonTakeQuiz);
        takeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TakeQuiz.class);
                startActivity(intent);
            }
        });
    }


}

