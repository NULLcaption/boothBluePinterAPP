package com.example.cxg.boothpinter.provider;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cxg.boothpinter.pojo.Sysinfo;
import com.example.cxg.boothpinter.query.SysinfoBean;
import com.example.cxg.boothpinter.utils.Helpers;
import com.example.cxg.boothpinter.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5.
 */

public class WebService implements IDataProvider{

    private static WebService instance;

    private static final int TIME_OUT = 30000;

    private static final String TAG = "WebService";

    /** app服务地址(常用测试地址：本地，测试机，正式机) **/
//	private static String OPENAPIURL = "http://exptest.zjxpp.com:7186";//测试机
    private static String OPENAPIURL = "http://10.3.2.36:7186";// 本机
//	private static String OPENAPIURL = "http://exp.zjxpp.com:8105";//apn 生产机
    /* app服务接口 */
    private static final String GET_PERFORMANCE_URL = "/mobilePlatform/router/login";
    private static final String SYSINFO = "/customer";

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
    public boolean getSysinfoList() {
        try {
            Map<String, String> params = new HashMap<>();
            //params.put("userId", DataProviderFactory.);
            String result = HttpUtil.post(OPENAPIURL + GET_PERFORMANCE_URL
                    + SYSINFO, params);
            if (result != null) {
                Gson gson = new Gson();
                List<SysinfoBean> sysinfoList = gson.fromJson(result,
                        new TypeToken<List<Sysinfo>>() {
                        }.getType());
                for (SysinfoBean sysinfoBean : sysinfoList) {
                    sysinfoBean.setZnum("012123");
                    sysinfoBean.save();
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Sysinfo> getSysinfo(String par) {
        return null;
    }
}
