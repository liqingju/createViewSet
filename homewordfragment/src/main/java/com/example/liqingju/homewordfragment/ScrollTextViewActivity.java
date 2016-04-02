package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by liqingju on 16/2/22.
 */
public class ScrollTextViewActivity extends Activity implements Runnable {
    private Button button;
    private TextView textView;
    private float textleft;
    private Handler mHandler;
    private Button buttonHandler;
    private Button buttonAsy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolltextviewactivity);
        button = (Button) findViewById(R.id.button);
        buttonHandler = (Button) findViewById(R.id.button1);
        textView = (TextView) findViewById(R.id.text);
        buttonAsy = (Button) findViewById(R.id.button2);
        textView.post(new Runnable() {
            @Override
            public void run() {
//                textleft = textView.getLeft();
//                Log.e("===textleft==   ", textleft + "");
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                textleft = layoutParams.leftMargin;

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.post(ScrollTextViewActivity.this);
            }
        });
        buttonHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(1);
            }
        });
        buttonAsy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Myadn myadn = new Myadn();
                myadn.execute();

            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                scrollTextView();
                sendEmptyMessage(1);
            }
        };

    }

    @Override
    public void run() {
        scrollTextView();
        textView.post(this);

    }

    public void scrollTextView() {
        if (textleft > 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.leftMargin = (int) textleft;
            textView.requestLayout();
            textleft = textleft - 1;
        }
        if (textleft <= 0) {
            textleft = getWindowManager().getDefaultDisplay().getWidth();
        }

    }

    class Myadn extends AsyncTask {

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            scrollTextView();
            new Myadn().execute(null);

        }

        @Override
        protected Object doInBackground(Object[] params) {


            return null;
        }
    }

}
