package com.example.beng.cobaquiz.Activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.beng.cobaquiz.Adapter.PlayerAdapter;
import com.example.beng.cobaquiz.Interface.EditTextOnActionListener;
import com.example.beng.cobaquiz.Model.User;
import com.example.beng.cobaquiz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SettingGameRule extends Activity implements AdapterView.OnItemSelectedListener, EditTextOnActionListener {
    private Spinner spinnerPlayerList;
    private PlayerAdapter playerAdapter;
    private ListView listViewPlayer;
    private List<User> listPlayer = new ArrayList<>();

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_setting_game_rule);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        listViewPlayer = (ListView) findViewById(R.id.list_view_player);
        playerAdapter =  new PlayerAdapter(this, listPlayer, this);
        listViewPlayer.setAdapter(playerAdapter);
        spinnerPlayerList = (Spinner) findViewById(R.id.spinner_option_player);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.listPlayerCount, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlayerList.setAdapter(adapterSpinner);
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        spinnerPlayerList.setOnItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        listPlayer.clear();
        int countSelected = Integer.valueOf((String) adapterView.getItemAtPosition(i));
        for(int count = 0; count<countSelected; count++){
            User newUser = new User();
            newUser.setIdUser(count+1);
            listPlayer.add(newUser);
        }
        playerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void EditTextActionListener(TextView view, int position, int actionEditorInfo) {
        if(actionEditorInfo == EditorInfo.IME_ACTION_NEXT){
            if(view.getText() != null || view.getText().length() >0){
                listPlayer.get(position).setNamaUser(view.getText().toString());
                Log.i("cekdataeditext", "EditTextActionListener: " + view.getText());
            }else {
                Log.i("cekdataeditext", "EditTextActionListener: " + " kosong");
            }
        }
        if(actionEditorInfo == EditorInfo.IME_ACTION_DONE){
            if(view.getText() != null || view.getText().length() >0){
                listPlayer.get(position).setNamaUser(view.getText().toString());
                Log.i("cekdataeditext", "EditTextActionListener: " + view.getText());
            }else {
                Log.i("cekdataeditext", "EditTextActionListener: " + " kosong");
            }
        }
        for(User user : listPlayer){
            Log.i("cekUser", "EditTextActionListener: " + user.getNamaUser());
        }
    }
}
