package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by liqingju on 16/2/20.
 */
public class showValuesTextActivity extends Activity {
private TextView mTextView;
    private MyApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstfragment);
        application = (MyApplication) getApplication();
        mTextView = (TextView) findViewById(R.id.show_text);
        android.text.ClipboardManager c = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mTextView.setText(String.format("appliction中的数据== %s  \n--- 剪切板中的数据-- %s",application.getText(),c.getText()));
    }
}
