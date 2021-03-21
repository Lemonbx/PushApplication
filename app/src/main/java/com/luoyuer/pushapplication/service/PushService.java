package com.luoyuer.pushapplication.service;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PushService extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (!sbn.isClearable()) {
            return;
        }
        Bundle bundle = sbn.getNotification().extras;
        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(sbn.getPackageName(), PackageManager.GET_ACTIVITIES);
            String appName = info.applicationInfo.loadLabel(manager).toString();
            String title = bundle.get(Notification.EXTRA_TITLE).toString();
            if (title.contains("选择输入法")) {
                return;
            }
            String content = bundle.get(Notification.EXTRA_TEXT).toString();
            Log.i("666", bundle.get(Notification.EXTRA_TITLE).getClass().getName());
//            Toast toast = Toast.makeText(PushService.this,appName+":"+content,Toast.LENGTH_LONG);
//            toast.show();
            new Thread(() -> {
                String url = "http://10.0.0.81:1500/req/?username=11&password=11&name=" + appName + "&title=" + title + "&content=" + content;
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        System.out.println(response.body().string());
                    }
                });
            }).start();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
