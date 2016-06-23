package com.example.angsi.courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.angsi.courseproject.Database.DatabaseHelper;
import com.example.angsi.courseproject.custom.Question;

import java.util.List;

public class EditQuestion extends AppCompatActivity {

    private EditText addQuestionEditText;
    private EditText choiceAEditText, choiceBEditText, choiceCEditText, choiceDEditText;
    private RadioGroup choiceRadioGroup;
    private RadioButton[] radioButton;
    private int selectedButton;
    private Button addQuestionButton;
    private Button back;
    private static DatabaseHelper db;

    private Intent intent;
    private int dataIndex;
    List<Question> questionList;
    Question question;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        initializeComponents();
        initializeQuestion();

    }

    private void initializeQuestion()
    {
        // intent data
        intent = getIntent();
        dataIndex = intent.getBundleExtra("bundle").getInt("index");

        //find question name in database
        questionList = db.showAllQuestions();
        question = questionList.get(dataIndex);

        // set edit text
        addQuestionEditText.setText(question.getQuestion());
        choiceAEditText.setText(question.getChoiceA());
        choiceBEditText.setText(question.getChoiceB());
        choiceCEditText.setText(question.getChoiceC());
        choiceDEditText.setText(question.getChoiceD());


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
                applyQuestionToDatabase();
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

    private void applyQuestionToDatabase()
    {
        Question editedQuestion = new Question(addQuestionEditText.getText().toString(),
                choiceAEditText.getText().toString(), choiceBEditText.getText().toString(),
                choiceCEditText.getText().toString(), choiceDEditText.getText().toString(),
                selectedButton);
        Log.i("edit string", "ok");
        db.editQuestion(editedQuestion, question);
        Toast.makeText(getApplicationContext(),"Modification Applied.",Toast.LENGTH_SHORT ).show();
        Intent questionIntent = new Intent(getApplicationContext(), ViewQuestion.class);
        finish();
        startActivity(questionIntent);


    }

}
