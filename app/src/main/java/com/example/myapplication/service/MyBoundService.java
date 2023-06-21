package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBoundService extends Service {

    private MyBinder myBinder = new MyBinder();

    private int count = 0;

    public class MyBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        count = intent.getIntExtra("START", 2);
        count ++;
        return myBinder;
    }

    public int getCount() {
        return count;
    }
}