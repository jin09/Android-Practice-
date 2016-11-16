package com.app.gautam.mathquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button truebtn;
    private Button falsebtn;
    private Button nextbtn;
    private Button prevbtn;
    private TextView txtv;
    private Button cheatbtn;
    public static String TAG = "Inside QuizActivity";
    private static int REQUEST_CODE_CHEAT = 1;
    private boolean hasCheated = false;

    private QuestionBank mQuestionBank[] = new QuestionBank[]{
            new QuestionBank(R.string.ques1,true),
            new QuestionBank(R.string.ques2,false),
            new QuestionBank(R.string.ques3,true),
            new QuestionBank(R.string.ques4,true),
            new QuestionBank(R.string.ques5,false)
    };
    private int currentIndex = 0;
    public static String KEY_STRING = "index";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_STRING);
        }
        Log.d(TAG,"Inside OnCreate()");
        truebtn = (Button)findViewById(R.id.btn1);
        falsebtn = (Button)findViewById(R.id.btn2);
        nextbtn = (Button) findViewById(R.id.btn3);
        txtv = (TextView) findViewById(R.id.ques);
        prevbtn = (Button) findViewById(R.id.btn4);
        cheatbtn = (Button) findViewById(R.id.btn5);
        int question = mQuestionBank[currentIndex].getQuestion();
        txtv.setText(question);
        truebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckAnswer(true);
                    }
                }
        );

        falsebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckAnswer(false);
                    }
                }
        );
        nextbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex = (currentIndex+1)%mQuestionBank.length;
                        updateQuestion();
                        hasCheated = false;
                    }
                }
        );
        prevbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentIndex = (currentIndex-1);
                        if(currentIndex < 0){
                            currentIndex = mQuestionBank.length-1;
                        }
                        updateQuestion();
                        hasCheated = false;
                    }
                }
        );

        cheatbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG,"Cheat btn pressed !!");
                        /* when no data is to passed to the child activity */
                        //Intent i = new Intent(getApplicationContext(),CheatActivity.class);
                        //startActivity(i);
                        /* When data is passed to child activity but no data is recieved from the child activity */
                        //Intent i = CheatActivity.newIntent(getApplicationContext(),currentIndex);
                        //startActivity(i);
                        /* data is sent to the child activity and data is also received from the child activity */
                        boolean ans = mQuestionBank[currentIndex].isTrueQuestions();
                        Intent i = CheatActivity.newIntent(getApplicationContext(),ans);
                        startActivityForResult(i,REQUEST_CODE_CHEAT);

                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode != RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            else{
                hasCheated = CheatActivity.wasCheatShown(data);
                return;
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"Inside onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"Inside onPause()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"Inside onResume()");
        Log.d(TAG,"HAS Cheated ? " + hasCheated);
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"Inside onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside onDestroy()");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_STRING,currentIndex);
    }
    private void updateQuestion(){
        int question = mQuestionBank[currentIndex].getQuestion();
        txtv.setText(question);
    }

    private void CheckAnswer(boolean userAns){
        boolean correctAns = mQuestionBank[currentIndex].isTrueQuestions();
        int messageId = 0;
        if(hasCheated == true){
            messageId = R.id.cheatedToast;

        }
        else{
            if(userAns == correctAns){
                messageId = R.string.correctToast;
            }
            else{
                messageId= R.string.wrongToast;
            }
        }
        Toast.makeText(this.getApplicationContext(),messageId,Toast.LENGTH_SHORT).show();
    }
}
