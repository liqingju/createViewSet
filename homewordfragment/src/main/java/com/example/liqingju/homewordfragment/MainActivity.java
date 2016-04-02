package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private FirstFragment mFristFrament;
    private SecondFrament mSecondFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        FragmentManager mFragmentManager = getFragmentManager();
        mFristFrament = (FirstFragment) mFragmentManager.findFragmentById(R.id.first_fragment);
        mSecondFrament = (SecondFrament) mFragmentManager.findFragmentById(R.id.second_fragment);
        mSecondFrament.setmOnClickLin(new SecondFrament.onClickLis() {
            @Override
            public void buttonOnClick(EditText v) {
                mFristFrament.setmTextViewText(v.getText().toString());
            }
        });
    }
}
