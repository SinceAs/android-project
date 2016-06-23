package com.example.angsi.courseproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.angsi.courseproject.Database.DatabaseHelper;
import com.example.angsi.courseproject.custom.Question;

public class AddQuestion extends AppCompatActivity {

    private EditText addQuestionEditText;
    private EditText choiceAEditText, choiceBEditText, choiceCEditText, choiceDEditText;
    private RadioGroup choiceRadioGroup;
    private RadioButton [] radioButton;
    private int selectedButton;
    private Button addQuestionButton;
    private Button back;
    private static DatabaseHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        initializeComponents();
    }

    private void initializeComponents()
    {
        addQuestionEditText = (EditText)findViewById(R.id.questionEditText);
        choiceAEditText = (EditText)findViewById(R.id.editTextChoice1);
        choiceBEditText = (EditText)findViewById(R.id.editTextChoice2);
        choiceCEditText = (EditText)findViewById(R.id.editTextChoice3);
        choiceDEditText = (EditText)findViewById(R.id.editTextChoice4);

        choiceRadioGroup = (RadioGroup)findViewById(R.id.choiceRadioGroup);

        // radio buttons
        radioButton = new RadioButton[4];
        radioButton[0] = (RadioButton)findViewById(R.id.radioButton1);
        radioButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRadioButton(0);
            }
        });
        radioButton[1] = (RadioButton)findViewById(R.id.radioButton2);
        radioButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRadioButton(1);
            }
        });
        radioButton[2] = (RadioButton)findViewById(R.id.radioButton3);
        radioButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRadioButton(2);
            }
        });
        radioButton[3] = (RadioButton)findViewById(R.id.radioButton4);
        radioButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerRadioButton(3);
            }
        });





        addQuestionButton = (Button)findViewById(R.id.buttonConfirm);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestionToDatabase();
            }
        });
        back = (Button)findViewById(R.id.addQuestionBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db = new DatabaseHelper(getApplicationContext());

    }

    private void listenerRadioButton( int idNumber)
    {
        for (int i = 0; i < 4; i++)
            radioButton[i].setChecked(false);
        radioButton[idNumber].setChecked(true);
        selectedButton = idNumber;

    }

    private void addQuestionToDatabase()
    {
        Question newQuestion = new Question(addQuestionEditText.getText().toString(),
                choiceAEditText.getText().toString(), choiceBEditText.getText().toString(),
                choiceCEditText.getText().toString(), choiceDEditText.getText().toString(),
                selectedButton);
        db.addQuestion(newQuestion);

        choiceAEditText.setText("");
        choiceBEditText.setText("");
        choiceCEditText.setText("");
        choiceDEditText.setText("");
        addQuestionEditText.setText("");
    }


}
