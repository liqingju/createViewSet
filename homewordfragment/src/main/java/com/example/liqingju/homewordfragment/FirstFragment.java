package com.example.liqingju.homewordfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by liqingju on 16/2/20.
 */
public class FirstFragment extends Fragment {
    private View mView;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.firstfragment,container,false);
         initView();


        return mView;
    }

    private void initView() {
        mTextView = (TextView) mView.findViewById(R.id.show_text);

    }

    public void setmTextViewText(final  String showStr){
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               mTextView.setText(showStr);
           }
       });


    }
}
