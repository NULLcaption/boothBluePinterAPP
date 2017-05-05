package com.example.cxg.boothpinter.provider;

import android.content.Context;

/**
 * Created by Administrator on 2017/5/5.
 */

public class DataProviderFactory {

    public  static Context ctx;


    public static Context getContext() {
        return ctx;
    }
    public static void setContext(Context ctx) {
        DataProviderFactory.ctx = ctx;
    }
    public static IDataProvider getProvider() {
        return WebService.getInstance();
    }

    public static IDataProvider getProvider(Context ctx) {
        DataProviderFactory.ctx = ctx;
        return getProvider();
    }

}
