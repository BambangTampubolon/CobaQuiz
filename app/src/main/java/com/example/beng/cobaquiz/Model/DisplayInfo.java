package com.example.beng.cobaquiz.Model;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Beng on 4/23/2018.
 */

public class DisplayInfo {
    private int screenHeight;
    private int screenWidth;
    private WindowManager windowManager;
    private DisplayMetrics displayMetrics;

    public DisplayInfo(Context context){
        GetDisplayProperties(context);
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }


    public void GetDisplayProperties(Context context){
        this.windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        this.displayMetrics = new DisplayMetrics();
        this.windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        setScreenHeight(displayMetrics.heightPixels);
        setScreenWidth(displayMetrics.widthPixels);
    }

}
