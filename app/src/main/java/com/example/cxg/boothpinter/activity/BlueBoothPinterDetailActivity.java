package com.example.cxg.boothpinter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cxg.boothpinter.R;
import com.example.cxg.boothpinter.application.XPPApplication;
import com.example.cxg.boothpinter.pojo.Sysinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 物料列表详细表单
 * Created by Administrator on 2017/5/3.
 */

public class BlueBoothPinterDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView znum;
    private TextView werks;
    private TextView matnr;
    private TextView zlichn;
    private TextView lgmng;
    private TextView Zmenge;
    private EditText date;
    private TextView erfmgl;
    private EditText mark;
    private Button printer;
    private Button backHome;
    private Button exit;
    private Sysinfo sysinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sysinfo_detail);
        initView();
        initData();
    }
    /**
     * init view
     * */
    private void initView() {
        name = (TextView) findViewById(R.id.name1);
        znum = (TextView) findViewById(R.id.znum);
        werks = (TextView) findViewById(R.id.werks);
        matnr = (TextView) findViewById(R.id.matnr);
        zlichn = (TextView) findViewById(R.id.zlichn);
        lgmng = (TextView) findViewById(R.id.lgmng);
        Zmenge = (TextView) findViewById(R.id.Zmenge);
        date = (EditText) findViewById(R.id.date);
        erfmgl = (TextView) findViewById(R.id.erfmgl);
        mark = (EditText) findViewById(R.id.mark);

        findViewById(R.id.printer).setOnClickListener(BtnClicked);
        findViewById(R.id.back_home).setOnClickListener(BtnClicked);
        findViewById(R.id.exit).setOnClickListener(BtnClicked);

    }

    /**
     * init data for service
     * */
    private void initData() {
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            sysinfo = (Sysinfo) bun.get("sysinfo");
        }
        if (sysinfo != null) {
            name.setText(sysinfo.getName1());
            znum.setText(sysinfo.getZnum());
            werks.setText(sysinfo.getWerks());
            matnr.setText(sysinfo.getMatnr());
            zlichn.setText(sysinfo.getZlichn());
            lgmng.setText(sysinfo.getLgmng());
            Zmenge.setText(sysinfo.getZmenge());
            SimpleDateFormat dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateNow = new Date();
            date.setText(dateString.format(dateNow));
            erfmgl.setText(sysinfo.getErfmgl());
            mark.setText("1");
        }
    }

    /**
     * 按钮监听事件
     * */
    private View.OnClickListener BtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.printer:
                    Intent intent = new Intent(BlueBoothPinterDetailActivity.this,BoothActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                    System.out.println("printer!!");
                    break;
                case R.id.back_home:
                    XPPApplication.exit(BlueBoothPinterDetailActivity.this);
                    break;
                case R.id.exit:
                    XPPApplication.exit(BlueBoothPinterDetailActivity.this);
                    break;
                default:
                    break;
            }
        }
    };

}
