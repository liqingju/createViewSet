package com.example.liqingju.homewordfragment;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by liqingju on 16/2/20.
 */
public class myServer extends Service {
    private BroadcastReceiver mBroadca = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent intent1 = new Intent("android.action.servertoo");
            intent1.putExtra("num", ((int)(Math.random() * 100))+"");
            sendBroadcast(intent1);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.action.server");
        registerReceiver(mBroadca, mIntentFilter);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadca != null) {
            unregisterReceiver(mBroadca);

        }
    }
}
