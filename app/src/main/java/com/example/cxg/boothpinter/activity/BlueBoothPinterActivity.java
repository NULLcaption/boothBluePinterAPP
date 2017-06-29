package com.example.cxg.boothpinter.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cxg.boothpinter.R;
import com.example.cxg.boothpinter.adapter.BlueBoothPinterAdapter;
import com.example.cxg.boothpinter.pojo.Ztwm004;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 采购物料系统首页
 * author: xg.chen
 * date: 2017/6/29 9:22
 * version: 1.0
*/

public class BlueBoothPinterActivity extends AppCompatActivity {

    /*详单列表*/
    private List<Ztwm004> ztwm004List;
    /*详单适配器*/
    private BlueBoothPinterAdapter blueBoothPinterAdapter;
    /*输入值:单据号码*/
    private EditText znum;
    /*输出值:供应商名称*/
    private TextView EName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth);

        //初始化视图
        initView();

        //加载列表
        ztwm004List = new ArrayList<>();
        ListView sysinfoListView = (ListView)findViewById(R.id.lv_sysinfo);
        sysinfoListView.setAdapter(new BlueBoothPinterAdapter(this.ztwm004List,this));

        //加载数据
        initData();
    }

    /**
     * description: init UI view
     * author: xg.chen
     * date: 2017/6/29 9:41
     * version: 1.0
    */
    private void initView (){
        EName2 = (TextView) findViewById(R.id.EName2);

        findViewById(R.id.button_ok).setOnClickListener(BtnClicked);//确定按钮
        findViewById(R.id.button_no).setOnClickListener(BtnClicked);//退出按钮
    }

    /**
     * description: init data fot service test data
     * author: xg.chen
     * date: 2017/6/29 9:41
     * version: 1.0
    */
    private void initData() {

        for (int i = 0; i < 10; i++){
            Ztwm004 ztwm004 = new Ztwm004();
            ztwm004.setMatnr("包装纸箱"+i);
            ztwm004.setEMaktx("这个东西是用来包装成品的。");
            ztwm004.setLgmng("10"+i);
            ztwm004.setMeins("箱");
            ztwm004.setZlichn("ZTWM00"+i+"-ZLICHN");
            ztwm004List.add(ztwm004);
        }

    }

    /**
     * description: 按钮事件监听类
     * author: xg.chen
     * date: 2017/6/23 15:30
     * version: 1.0
     */
    private View.OnClickListener BtnClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_ok :

                    break;
                case R.id.button_no :

                    break;
                default:
                    break;
            }
        }
    };
}
