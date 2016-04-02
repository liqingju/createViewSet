package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by liqingju on 16/2/20.
 */
public class StartActivity extends Activity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        initView();
        initEvent();


    }

    private void initEvent() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(getApplicationContext(),OpenUrlActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(getApplicationContext(),StartServerActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(getApplicationContext(),ShowHtmlActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(getApplicationContext(),SendValuesActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(getApplicationContext(),ScrollTextViewActivity.class));
                break;
        }
    }
}
