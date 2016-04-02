package com.example.liqingju.homewordfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by liqingju on 16/2/20.
 */
public class SecondFrament extends Fragment  implements  View.OnClickListener{
    protected EditText mEditText;
    private Button mButton;
    private View mRootView;
    private onClickLis mOnClickLis;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.secondfragment, container, false);
        initView();

        return mRootView;
    }

    private void initView() {
        mEditText = (EditText) mRootView.findViewById(R.id.input_text);
        mButton = (Button) mRootView.findViewById(R.id.send_fristfragment_show);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mOnClickLis.buttonOnClick(mEditText);
    }

    public void setmOnClickLin(onClickLis mOnClickLin) {
        this.mOnClickLis = mOnClickLin;
    }

    interface onClickLis {
        void buttonOnClick(EditText v);
    }
}
