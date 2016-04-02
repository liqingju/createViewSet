package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by liqingju on 16/2/20.
 */
public class SendValuesActivity extends Activity {

    private Button mButton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondfragment);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.send_fristfragment_show);
        mButton.setText("使用其他的两种传值方式");
        editText = (EditText) findViewById(R.id.input_text);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置到 剪切板
                android.text.ClipboardManager c = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                c.setText(editText.getText());
                //设置dao apple
                ((MyApplication) getApplication()).setText(editText.getText().toString());
                startActivity(new Intent(getApplicationContext(), showValuesTextActivity.class));
            }
        });
    }
}
