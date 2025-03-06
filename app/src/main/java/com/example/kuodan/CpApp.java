package com.example.kuodan;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.example.kuodan.tool.AppUtils;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by SAMSUNG on 2018/6/14.
 */

public class CpApp extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initBugly(mContext);
    }

    private void initBugly(Context context) {
        String packageName = context.getPackageName();
        String processName = AppUtils.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        strategy.setDeviceID(Build.SERIAL);
        strategy.setAppVersion(AppUtils.getAppVersion(context));
        strategy.setDeviceModel(Build.BRAND + " " + AppUtils.isTablet(context) + " " + Build.MODEL);
        // CrashReport.setUserSceneTag(context, 9527); // 上报后的Crash会显示该标签
        CrashReport.setIsDevelopmentDevice(context, BuildConfig.DEBUG);
        CrashReport.initCrashReport(context, "f4f0643028", true);
    }


    public static Context getContext() {
        return mContext;
    }


}