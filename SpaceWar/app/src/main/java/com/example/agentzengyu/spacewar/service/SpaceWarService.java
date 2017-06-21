package com.example.agentzengyu.spacewar.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.agentzengyu.spacewar.application.Config;
import com.example.agentzengyu.spacewar.application.SpaceWarApp;
import com.example.agentzengyu.spacewar.entity.BasicData;
import com.example.agentzengyu.spacewar.util.BasicDataHandler;
import com.example.agentzengyu.spacewar.util.DataHandlerCallBack;
import com.example.agentzengyu.spacewar.util.UserDataHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 太空大战服务
 */
public class SpaceWarService extends Service {
    private SpaceWarApp app = null;
    private BasicData data = new BasicData();

    @Override
    public void onCreate() {
        super.onCreate();
        app = (SpaceWarApp) getApplication();
        app.setService(this);
        initBasicData();
        initUserData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initBasicData() {
        BasicDataHandler handler = new BasicDataHandler();
        try {
            InputStream inputStream = getResources().getAssets().open(Config.FILE_SHOP);
            handler.setResource(data, inputStream).read(new DataHandlerCallBack() {
                @Override
                public void onStart(String s) {

                }

                @Override
                public void onSuccess(String s) {

                }

                @Override
                public void onFailure(String s, Exception e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initUserData() {
        UserDataHandler handler = new UserDataHandler();
        File file = new File(getFilesDir(), Config.FILE_USER);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        handler.setResource(app.getUser(), file).read(new DataHandlerCallBack() {
            @Override
            public void onStart(String s) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFailure(String s, Exception e) {

            }
        });
    }

    public void saveUserData() {
        UserDataHandler handler = new UserDataHandler();
        File file = new File(getFilesDir(), Config.FILE_USER);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        handler.setResource(app.getUser(), file).save(new DataHandlerCallBack() {
            @Override
            public void onStart(String s) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFailure(String s, Exception e) {

            }
        });
    }

    public class ServiceBinder extends Binder {
    }
}
