
package com.example.liqingju.meituancontext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

/**
 * Created by liqingju on 15/12/30.
 */
public class mianActivity extends Activity implements View.OnClickListener {
    private Button bt_listView;
    private Button bt_zidingyi;
    private Button bt_myScrollView;
    private Button bt_ScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();


    }

    private void initView() {
        bt_listView = (Button) findViewById(R.id.start_listview);
        bt_zidingyi = (Button) findViewById(R.id.view);
        bt_myScrollView = (Button) findViewById(R.id.my_scrollview);
        bt_ScrollView = (Button) findViewById(R.id.scrollview);

        bt_zidingyi.setOnClickListener(this);
        bt_listView.setOnClickListener(this);
        bt_myScrollView.setOnClickListener(this);
        bt_ScrollView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_listview:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

            case R.id.view:
                startActivity(new Intent(getApplicationContext(), CrilyViewActivity.class));
                break;

            case R.id.my_scrollview:
                startActivity(new Intent(getApplicationContext(), myScrollViewActivity.class));
                break;
            case R.id.scrollview:
                startActivity(new Intent(getApplicationContext(), ScrollViewActivity.class));

        }


    }
}