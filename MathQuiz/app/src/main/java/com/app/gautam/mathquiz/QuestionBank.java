package com.app.gautam.mathquiz;

/**
 * Created by gautam on 07-08-2016.
 */
public class QuestionBank {

    private int question;
    private boolean trueQuestion;

    public QuestionBank(int question, boolean trueQuestions){
        this.question = question;
        this.trueQuestion = trueQuestions;
    }

    public int getQuestion(){
        return question;
    }

    public void setQuestion(int question){
        this.question = question;
    }

    public boolean isTrueQuestions(){
        return  trueQuestion ;
    }

    public void setTrueQuestion(boolean a){
        trueQuestion = a;
    }


}
