package com.app.gautam.assignment1primes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    Button truebtn  ;
    Button falsebtn ;
    Button nextbtn ;
    private  int num ;
    private String TAG = "MainActivity";


    private void updateText(){
        num = (int)Math.ceil(Math.random()*100);
        String s ;
        int temp = num;
        s = "Is " + Integer.toString(temp) + " a prime number ?";
        tv.setText(s);
    }

    private void checkAns(boolean input){
        boolean actual = true;
        if(num < 2){
            actual = false;
        }
        for(int i=2;i<=num/2;i++){
            if(num%i == 0){
                actual = false;
                break;
            }
        }
        if(actual == input){
            Toast.makeText(this.getApplicationContext(),R.string.correct,Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this.getApplicationContext(),R.string.wrong,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"inside onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv = (TextView) findViewById(R.id.textView1);
         truebtn = (Button) findViewById(R.id.truebtn);
         falsebtn = (Button) findViewById(R.id.falsebtn);
        nextbtn = (Button) findViewById(R.id.nextbtn);
        updateText();
        truebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAns(true);
                    }
                }
        );

        falsebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkAns(false);

                    }
                }
        );

        nextbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateText();
                    }
                }
        );
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
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"Inside onDestroy()");
    }
}
