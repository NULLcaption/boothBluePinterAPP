package com.example.cxg.boothpinter.provider;

import android.app.Activity;

import com.example.cxg.boothpinter.pojo.Sysinfo;

import java.util.List;

/**
 * service interface
 * Created by Administrator on 2017/5/5.
 */

public interface IDataProvider {
    /**
     * init data
     * @param activity
     */
    public void startDateUpdateTasks(Activity activity);
    /**
     *  获取门店列表
     */
    public boolean getSysinfoList();

    /**
     * 获取门店信息
     * @param par
     * @return
     */
    public List<Sysinfo> getSysinfo(String par);
}
