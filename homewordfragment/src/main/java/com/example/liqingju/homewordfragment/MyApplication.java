package com.example.liqingju.homewordfragment;

import android.app.Application;

/**
 * Created by liqingju on 16/2/20.
 */
public class MyApplication extends Application {
    private String text;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
