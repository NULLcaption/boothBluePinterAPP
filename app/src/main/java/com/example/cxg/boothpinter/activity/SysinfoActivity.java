package com.example.cxg.boothpinter.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.cxg.boothpinter.R;
import com.example.cxg.boothpinter.adapter.SysinfoAdapter;
import com.example.cxg.boothpinter.pojo.Sysinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统物料组详单界面
 * Created by Administrator on 2017/5/2.
 */

public class SysinfoActivity extends AppCompatActivity {

    /*详单列表*/
    private List<Sysinfo> sysinfoList;
    /*详单适配器*/
    private SysinfoAdapter sysinfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth);

        sysinfoList = new ArrayList<>();
        ListView sysinfoListView = (ListView)findViewById(R.id.lv_sysinfo);
        sysinfoListView.setAdapter(new SysinfoAdapter(this.sysinfoList,this));
        initData();
    }

    /**
     * init data fot service test data
     */
    private void initData() {
        for (int i = 0; i < 10; i++){
            Sysinfo sysinfo = new Sysinfo();
            sysinfo.setMatnr("包装纸箱"+i);
            sysinfo.setMaktx("这个东西是用来包装成品的。");
            sysinfo.setLgmng("10"+i);
            sysinfo.setMeins("箱");
            sysinfo.setZlichn("ZTWM00"+i+"-ZLICHN");
            sysinfoList.add(sysinfo);
        }

    }
}
