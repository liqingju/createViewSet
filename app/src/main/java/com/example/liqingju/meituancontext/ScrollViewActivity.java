package com.example.liqingju.meituancontext;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by liqingju on 16/1/7.
 */
public class ScrollViewActivity extends Activity {
//    LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollviewactivity);
//        ScriptIntrinsicBlur.create()
            Math.random();


        TimeInterpolator time = new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        };
        Interpolator interpolator = new Interpolator() {
            @Override
            public float getInterpolation(float t) {
                t-=1;
                return t*t*t*t*t+1.0f;
            }
        };
        interpolator.getInterpolation(1.1f);
    }
}
