package com.app.gautam.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static String TAG = "Cheat Activity ";
    private static String CHEAT_KEY = "Cheat_key";
    private boolean isCheated = false;
    private static String IS_CHEATED = "IS_CHEATED";
    private TextView cheatAnstxt ;
    private Button showCheatBtn;
   /*
   * This is the method that will be called by the Quizactivity which will then store the
   * value of current index in the intent*/
    public static Intent newIntent(Context context, boolean i){
        Intent intent = new Intent(context,CheatActivity.class);
        intent.putExtra(CHEAT_KEY,i);
        return intent;
    }

    public static boolean wasCheatShown(Intent i){
        return i.getBooleanExtra(IS_CHEATED,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG,"Inside OnCreate()");
        final boolean i = getIntent().getBooleanExtra(CHEAT_KEY,false);
        cheatAnstxt = (TextView) findViewById(R.id.cheat_textView);
        showCheatBtn = (Button) findViewById(R.id.show_cheat_btn);

        showCheatBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(i == true){
                            cheatAnstxt.setText("Ans is TRUE");
                        }
                        else{
                            cheatAnstxt.setText("Ans is FALSE");
                        }
                        setAnswerResult(true);
                    }
                }
        );

        Log.d(TAG,"Received value is " + i);

    }

    private void setAnswerResult(boolean b){
        Intent i = new Intent();
        i.putExtra(IS_CHEATED,b);
        setResult(RESULT_OK,i);
        return;
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
}
