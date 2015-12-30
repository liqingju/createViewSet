package com.example.liqingju.meituancontext;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
private MyListView listView;
    private int i=5;
    private int k=4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (MyListView) findViewById(R.id.listview);

        String[] adapterData = new String[] { "Activity","Service","Content Provider","Intent","BroadcastReceiver","ADT","Sqlite3","HttpClient","DDMS","Android Studio","Fragment","Loader" };

        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, adapterData));

        listView.getHanderImageView().setImageResource(R.drawable.splash01);
        listView.getHanderImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
       int  bottom = listView.getFrameLayout().getBottom();
        float f =k/i;
        Log.e("bottom",bottom+"   "+f);

    }


}
