package com.example.angsi.courseproject.custom;

/**
 * Created by angsi on 2016/4/20.
 */
public class Question {

    String question;
    String choiceA, choiceB, choiceC, choiceD;
    int correctAnswerIndex;

    public Question()
    {
        question = "null";
    }

    public Question(String question, String choiceA, String choiceB)
    {
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }

    public Question(String question, String choiceA, String choiceB, String choiceC)
    {
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
    }

    public Question(String question, String choiceA, String choiceB, String choiceC, String choiceD, int correctAnswerIndex)
    {
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
