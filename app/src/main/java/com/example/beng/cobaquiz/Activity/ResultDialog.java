package com.example.beng.cobaquiz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.EGLExt;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beng.cobaquiz.Model.User;
import com.example.beng.cobaquiz.R;

import java.io.Serializable;

public class ResultDialog extends Activity {

    private TextView textResult;
    private Button okButton;
    private int answerResult;
    private CountDownTimer countDownTimer;
    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);
        this.setFinishOnTouchOutside(false);
        textResult = (TextView) findViewById(R.id.resultText);
        okButton = (Button) findViewById(R.id.okButton);
        Intent intent = getIntent();
        answerResult = intent.getIntExtra("answerByUser", 0);
        userData = (User) intent.getSerializableExtra("userData");
        if(answerResult != 24){
            textResult.setText("Jawaban"+ userData.getNamaUser() +" salah " + answerResult);
        } else {
            textResult.setText("Jawaban"+ userData.getNamaUser() +" benar " + answerResult);
        }

        countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                MoveToQuizActivity(userData);
            }
        };
        countDownTimer.start();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoveToQuizActivity(userData);
            }
        });
    }

    @Override
    public void onBackPressed() {
       MoveToQuizActivity(userData);
        //        super.onBackPressed();
    }


    public void MoveToQuizActivity(User player){
        Intent intent = new Intent(ResultDialog.this, QuizActivity.class);
        Log.i("cekuserdilempar", "MoveToQuizActivity: " + userData.getNamaUser());
        intent.putExtra("answeringUser", userData);
        if(answerResult!=24){
            intent.putExtra("statusAnswer", false);
        }else {
            intent.putExtra("statusAnswer", true);
        }

        startActivity(intent);
    }
}
