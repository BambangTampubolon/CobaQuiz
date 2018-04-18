package com.example.beng.cobaquiz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beng.cobaquiz.R;

public class ResultDialog extends Activity {

    private TextView textResult;
    private Button okButton;
    private int answerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);
        this.setFinishOnTouchOutside(false);
        textResult = (TextView) findViewById(R.id.resultText);
        okButton = (Button) findViewById(R.id.okButton);
        Intent intent = getIntent();
        answerResult = intent.getIntExtra("answerByUser", 0);
        if(answerResult != 24){
            textResult.setText("Jawaban anda salah " + answerResult);
        } else {
            textResult.setText("Jawaban anda benar " + answerResult);
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToQuizBack = new Intent(ResultDialog.this, QuizActivity.class);
                startActivity(intentToQuizBack);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResultDialog.this, QuizActivity.class);
        startActivity(intent);
        //        super.onBackPressed();
    }
}
