package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by liqingju on 16/2/20.
 */
public class StartServerActivity extends Activity {
    protected EditText mEditText;
    private Button mButton;
    private BroadcastReceiver mBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mEditText.setText(intent.getStringExtra("num"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondfragment);
        startService(new Intent(this, myServer.class));
        IntentFilter intentFilter  = new IntentFilter();
        intentFilter.addAction("android.action.servertoo");
        registerReceiver(mBroadcast,intentFilter);
        initView();

    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.input_text);
        mButton = (Button) findViewById(R.id.send_fristfragment_show);
        mButton.setText("与server进行数据交互");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent("android.action.server"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcast !=null){
            unregisterReceiver(mBroadcast);
        }
    }
}
