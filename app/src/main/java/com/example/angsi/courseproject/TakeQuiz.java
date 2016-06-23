package com.example.angsi.courseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angsi.courseproject.Database.DatabaseHelper;
import com.example.angsi.courseproject.custom.Question;

import java.util.List;

public class TakeQuiz extends AppCompatActivity {

    TextView textViewTitle,info, resultInfo;
    Button done;
    RadioGroup radioGroup;
    RadioButton radioButton[] = new RadioButton[4];
    List<Question> questionList;
    private static DatabaseHelper db;
    Question question;
    int correct;
    int correctScore;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        initializeComponents();
        ShowQuestion();
    }

    public void ShowQuestion() {

        correctScore = 0;
        questionList = db.showAllQuestions();
        index = 0;

        if(questionList.size()<=0){
            Toast.makeText(getApplicationContext(),"No question available",Toast.LENGTH_LONG).show();
            finish();
        }else{
            AnswerQuestion();
        }

    }

    private void AnswerQuestion() {
        info.setText("" + (index + 1) + " out of " + questionList.size());
        question = questionList.get(index);

        radioButton[0].setChecked(false);
        radioButton[1].setChecked(false);
        radioButton[2].setChecked(false);
        radioButton[3].setChecked(false);
        correct = question.getCorrectAnswerIndex();
        textViewTitle.setText(question.getQuestion());


        if(question.getChoiceA().isEmpty()){
            radioButton[0].setVisibility(View.GONE);
        }else{
            radioButton[0].setVisibility(View.VISIBLE);
            radioButton[0].setText(question.getChoiceA());
        }

        if(question.getChoiceB().isEmpty()){
            radioButton[1].setVisibility(View.GONE);
        }else{
            radioButton[1].setVisibility(View.VISIBLE);
            radioButton[1].setText(question.getChoiceB());
        }

        if(question.getChoiceC().isEmpty()){
            radioButton[2].setVisibility(View.GONE);
        }else{
            radioButton[2].setVisibility(View.VISIBLE);
            radioButton[2].setText(question.getChoiceC());
        }

        if(question.getChoiceD().isEmpty()){
            radioButton[3].setVisibility(View.GONE);
        }else{
            radioButton[3].setVisibility(View.VISIBLE);
            radioButton[3].setText(question.getChoiceD());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int temp = 5;
                switch(checkedId){
                    case R.id.RadioButtonA1:
                        temp = 0;
                        break;
                    case R.id.RadioButtonA2:
                        temp = 1;
                        break;
                    case R.id.RadioButtonA3:
                        temp = 2;
                        break;
                    case R.id.RadioButtonA4:
                        temp = 3;
                        break;
                }

                if(temp == correct){
                    correctScore = correctScore + 1;
                    Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_SHORT).show();
                }



                if(index < questionList.size()){
                    AnswerQuestion();
                }
                else{
                   // Toast.makeText(getApplicationContext(),"Result :" + String.format("%.2f", (float) correctScore / questionList.size() * 100) + " %",Toast.LENGTH_LONG).show();

                    done.setVisibility(View.VISIBLE);

                    resultInfo.setText("Result :" + String.format("%.2f", (float) correctScore / questionList.size() * 100) + " %"
                    + "\n " + correctScore + " out of " + questionList.size());
                }
            }
        });

        index ++;

    }

    private void initializeComponents(){
        db = new DatabaseHelper(getApplicationContext());
        textViewTitle = (TextView)findViewById(R.id.textView2);
        info = (TextView)findViewById(R.id.textView);
        done = (Button)findViewById(R.id.buttonDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        resultInfo = (TextView)findViewById(R.id.textView3);

        radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        radioButton[0] = (RadioButton)findViewById(R.id.RadioButtonA1);
        radioButton[1] = (RadioButton)findViewById(R.id.RadioButtonA2);
        radioButton[2] = (RadioButton)findViewById(R.id.RadioButtonA3);
        radioButton[3] = (RadioButton)findViewById(R.id.RadioButtonA4);
    }

}
