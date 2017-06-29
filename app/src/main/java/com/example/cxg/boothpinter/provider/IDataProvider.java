package com.example.cxg.boothpinter.provider;

import android.app.Activity;

import com.example.cxg.boothpinter.pojo.Ztwm004;

import java.util.List;

/**
 * service interface
 * Created by Administrator on 2017/5/5.
 */

public interface IDataProvider {
    /**
     * init data
     *
     * @param activity
     */
    void startDateUpdateTasks(Activity activity);

    /**
     * 根据单据获取采购物料信息
     *
     * @param string
     * @return
     */

    List<Ztwm004> getPurchasedItemInfo(String string);
}
