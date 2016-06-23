package com.example.angsi.courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionBankMain extends AppCompatActivity {

    Button toAddQuestionButton;
    Button toViewQuestionButton;
    Button previewQuizButton;
    Button back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank_main);
        initializeComponents();
    }

    private void initializeComponents()
    {
        toAddQuestionButton = (Button)findViewById(R.id.buttonAddQuestion);
        toAddQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddQuestion.class);
                startActivity(intent);
            }
        });

        toViewQuestionButton = (Button)findViewById(R.id.buttonViewQuestion);
        toViewQuestionButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ViewQuestion.class);
                startActivity(intent);
            }
        });

        back = (Button)findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        previewQuizButton = (Button)findViewById(R.id.buttonAnswer);
        previewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TakeQuiz.class);
                startActivity(intent);
            }
        });

    }
}
