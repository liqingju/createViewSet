package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liqingju on 16/2/20.
 */
public class ShowHtmlActivity extends Activity {
    private EditText mEditeText;
    private Button mButton;
    private TextView mTextView;
    private String html;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showhtmlactivity);
        initView();

    }

    private void initView() {
        mEditeText = (EditText) findViewById(R.id.input_text);
        mButton = (Button) findViewById(R.id.send_fristfragment_show);
        mTextView = (TextView) findViewById(R.id.show_text);
        mEditeText.setText("www.baidu.com");
        final LoadHtml loadHtml = new LoadHtml();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHtml.execute(mEditeText.getText().toString());

            }
        });
    }

    class LoadHtml extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String url = String.format("http://%s", params[0]);
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setConnectTimeout(3 * 1000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                if (httpURLConnection.getResponseCode() == 200) {
                    return StreamToString(httpURLConnection.getInputStream());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return "请求错误";
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);
        }
    }


    public static String StreamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
            byte[] res = baos.toByteArray();
            String tem = new String(res);
            return new String(res);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
