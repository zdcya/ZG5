package com.bawei.utils;

import android.widget.Toast;

public class MsgUtils {

    private static volatile MsgUtils  msgUtils = null;

    public MsgUtils() {
    }

    public static MsgUtils getInstance() {

        if (msgUtils == null){

            synchronized (MsgUtils.class){

                if (msgUtils == null){

                    msgUtils = new MsgUtils();
                }
            }

        }

        return msgUtils;
    }

    public void showMsg(String msg){

        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
    

}
