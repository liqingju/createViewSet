package com.example.liqingju.aidl;

import android.os.RemoteException;

/**
 * Created by liqingju on 16/3/7.
 */
public class MyAIDL extends IMyAidlInterface.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void getList() throws RemoteException {

    }
}
