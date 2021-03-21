package com.luoyuer.pushapplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppListActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applist);
        lv = findViewById(R.id.listview);
        PackageManager pm = getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        String[] appNames = new String[installedPackages.size()];
        for(int i=0;i<installedPackages.size();i++){
            appNames[i]=installedPackages.get(i).applicationInfo.loadLabel(pm).toString();
        }
//        Arrays.asList(appNames).forEach(System.out::println);
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, appNames);
        lv.setAdapter(adapter);
    }
}
