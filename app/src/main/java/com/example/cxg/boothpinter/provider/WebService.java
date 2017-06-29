package com.example.cxg.boothpinter.provider;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cxg.boothpinter.pojo.Ztwm004;
import com.example.cxg.boothpinter.utils.Helpers;

import java.util.Date;
import java.util.List;

/**
 * description: webservice服务类
 * author: xg.chen
 * date: 2017/6/29 9:16
 * version: 1.0
*/

public class WebService implements IDataProvider{

    private static WebService instance;

    private static final int TIME_OUT = 30000;

    private static final String TAG = "WebService";

    private WebService() {
        super();
    }

    public static IDataProvider getInstance() {
        if (instance == null)
            instance = new WebService();
        return instance;
    }

    @Override
    public void startDateUpdateTasks(Activity activity) {
        Context ctx = DataProviderFactory.getContext();
        if (ctx != null) {
            SharedPreferences sp = ctx.getSharedPreferences("XPPWebService",
                    Context.MODE_PRIVATE);

            if (sp.contains("lastUpdate")) {
                Date now = new Date();
                String str1 = sp.getString("lastUpdate", "");
                String str2 = Helpers.getDateStrWithoutTime(now);
                if (str1.startsWith(str2)) {
                    Log.d(TAG, "No updates needed at this time.");
                    return;
                }
            }
            if (UpdateTask.getInstance().getStatus() != AsyncTask.Status.RUNNING) {
                sp.edit().putInt("lastUpdatedShopSequence", -1).commit();
                new UpdateTask(activity, false).execute();
            }
        }
    }

    @Override
    public List<Ztwm004> getPurchasedItemInfo(String string) {
        return null;
    }

}
