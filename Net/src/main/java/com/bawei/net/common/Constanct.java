package com.bawei.net.common;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.bawei.utils.MyApplication;

public class Constanct {

    public static final int TIMEOUT = 1;

    public static String getAuthCode(){

        try{
            ApplicationInfo applicationInfo = MyApplication.getAppContext().getPackageManager().getApplicationInfo(MyApplication.getAppContext().getPackageName(), PackageManager.GET_META_DATA);
            String authCode = applicationInfo.metaData.getString("authCode");
            return authCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}
