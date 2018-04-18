package com.example.beng.cobaquiz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beng.cobaquiz.R;

public class HomeActivity extends Activity {

    private Button playButton, settingButton, tutorialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playButton = (Button) findViewById(R.id.playButton);
        settingButton = (Button) findViewById(R.id.settingButton);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);

        final Intent mainActivityIntent = new Intent(this, MainActivity.class);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivityIntent);

            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PopUpStart.class));
            }
        });

        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, QuizActivity.class));
            }
        });
    }
}
