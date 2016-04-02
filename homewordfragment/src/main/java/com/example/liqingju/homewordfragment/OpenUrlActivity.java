package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by liqingju on 16/2/20.
 */
public class OpenUrlActivity extends Activity {
    protected EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondfragment);
        initView();

    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.input_text);
        mEditText.setText("www.baidu.com");
        mButton = (Button) findViewById(R.id.send_fristfragment_show);
        mButton.setText("浏览器隐式打开");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStart(mEditText.getText().toString());
            }
        });
    }

    public void openStart(String url){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(String.format("http://%s",url));
        intent.setData(content_url);
        startActivity(intent);
    }

}
