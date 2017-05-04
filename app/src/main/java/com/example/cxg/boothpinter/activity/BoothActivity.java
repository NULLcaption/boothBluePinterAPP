package com.example.cxg.boothpinter.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.cxg.boothpinter.R;
import com.example.cxg.boothpinter.utils.Bluetooth;
import com.example.cxg.boothpinter.utils.MessageBox;
import com.example.cxg.boothpinter.utils.StatusBox;
import com.example.cxg.boothpinter.utils.lable_sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 蓝牙打印首页
 */
public class BoothActivity extends AppCompatActivity {

    /*蓝牙适配器*/
    public static BluetoothAdapter myBluetoothAdapter;
    /*远程连接地址*/
    public String SelectedBDAddress;
    /*打印机盒子状态*/
    StatusBox statusBox;
    /*盒子信息*/
    MessageBox megBox;
    /*编辑张数*/
    EditText tv1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_booth);

        /*Button Button1 = (Button) findViewById(R.id.button1);
        statusBox = new StatusBox(this, Button1);
        megBox = new MessageBox(this);
        tv1 = (EditText) findViewById(R.id.editText);
        tv1.setText("10");
        SelectedBDAddress = "";
        *//*判断设备是否支持蓝牙设备*//*
        if (!ListBluetoothDevice())
            finish();
        *//*循环多张打印*//*
        Button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                Print1(SelectedBDAddress);
            }
        });
        Button Button2 = (Button) findViewById(R.id.button2);
        *//*单张打印*//*
        Button2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                Print2(SelectedBDAddress);
            }
        });
        *//*单张打印———格式不同*//*
        Button Button3 = (Button) findViewById(R.id.button3);
        Button3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                Print3(SelectedBDAddress);
            }
        });*/
    }

    /**
     * 循环多张打印
     * @param BDAddress
     */
    private void Print1(String BDAddress) {
        statusBox.Show("正在打印...");
        if (!Bluetooth.OpenPrinter(BDAddress)) {
            showMessage(Bluetooth.ErrorMessage);
            Bluetooth.close();
            statusBox.Close();
            return;
        }
        // create page
        String name = tv1.getText().toString();
        int num = Integer.parseInt(name);
        lable_sdk.SelectPage(0);
        lable_sdk.ClearPage();
        lable_sdk.SelectPage(1);
        lable_sdk.ClearPage();
        lable_sdk.SetPageSize(83 * 8, 72 * 8);
        lable_sdk.ErrorConfig(true);
        for (int i = 1; i <= num; i++) {
            DrawContent(i);// content
            lable_sdk.PrintPage(0x04, 150, true);
            lable_sdk.ClearPage();
        }
        Bluetooth.close();
        statusBox.Close();
    }// print1

    /**
     * 单张打印
     * @param BDAddress
     */
    private void Print2(String BDAddress) {
        statusBox.Show("正在打印...");
        if (!Bluetooth.OpenPrinter(BDAddress)) {
            showMessage(Bluetooth.ErrorMessage);
            Bluetooth.close();
            statusBox.Close();
            return;
        }
        // create page
        lable_sdk.SelectPage(0);
        lable_sdk.ClearPage();
        lable_sdk.SelectPage(1);
        lable_sdk.ClearPage();
        lable_sdk.SetPageSize(83 * 8, 72 * 8);
        lable_sdk.ErrorConfig(true);
        DrawContent(1);// content
        lable_sdk.PrintPage(0x04, 150, true);
        lable_sdk.SelectPage(0);
        lable_sdk.ClearPage();
        lable_sdk.SelectPage(1);
        lable_sdk.ClearPage();
        if (zp_realtime_status(10000) != 0)
            showMessage(Bluetooth.ErrorMessage);
        Bluetooth.close();
        statusBox.Close();
    }// print2

    /**
     * 单张打印不同的格式
     * @param BDAddress
     */
    private void Print3(String BDAddress) {
        statusBox.Show("正在打印...");
        if (!Bluetooth.OpenPrinter(BDAddress)) {
            showMessage(Bluetooth.ErrorMessage);
            Bluetooth.close();
            statusBox.Close();
            return;
        }
        // create page
        String name = tv1.getText().toString();
        int num = Integer.parseInt(name);
        lable_sdk.SelectPage(0);
        lable_sdk.ClearPage();
        lable_sdk.SelectPage(1);
        lable_sdk.ClearPage();
        lable_sdk.SetPageSize(83 * 8, 72 * 8);
        lable_sdk.ErrorConfig(true);
        DrawContentSpike();// content
        lable_sdk.PrintPageSpike(0x04, 150, num, 0x1);
        lable_sdk.ClearPage();
        Bluetooth.close();
        statusBox.Close();
    }// print3

    /**
     * 打印数据输出时间设置
     * @param timeout
     * @return
     */
    public static int zp_realtime_status(int timeout) {
        byte[] status = new byte[8];
        byte[] buf = new byte[11];
        buf[0] = 0x1f;
        buf[1] = 0x00;
        buf[2] = 0x06;
        buf[3] = 0x00;
        buf[4] = 0x07;
        buf[5] = 0x14;
        buf[6] = 0x18;
        buf[7] = 0x23;
        buf[8] = 0x25;
        buf[9] = 0x32;
        buf[10] = 0x00;
        Bluetooth.SPPWrite(buf, 10);
        if (Bluetooth.SPPReadTimeout(status, 1, timeout) == false) {
            return -1;
        }
        return status[0];
    }

    /**
     * 页面布局
     * @param num
     */
    private void DrawContent(int num) {
        lable_sdk.DrawText(7 * 8, 0, "xx物流", 0x00, 0x0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 3 * 8, "ZICOX", 0x00, 0x0);
        zp_realtime_status(1000);
        lable_sdk.DrawCode1D(21 * 8, 0, "0123456789", 0x1, 0x03, (0x7 * 8));
        zp_realtime_status(1000);
        lable_sdk.DrawCode1D(6 * 8, 7 * 8, "0123456789", 0x1, 0x43, (0x7 * 8));
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 7 * 8, 82 * 8, 7 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 16 * 8, 82 * 8, 16 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 25 * 8, 82 * 8, 25 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 31 * 8, 82 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 40 * 8, 82 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 46 * 8, 82 * 8, 46 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 7 * 8, 6 * 8, 46 * 8, 2);
        zp_realtime_status(1000);
        for (int j = 0; j < 82; j += 14) {
            lable_sdk.DrawLine((12 + j) * 8, 7 * 8, (12 + j) * 8, 25 * 8, 2);
            zp_realtime_status(1000);
        }
        lable_sdk.DrawLine(82 * 8, 7 * 8, 82 * 8, 46 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(17 * 8, 25 * 8, 17 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(49 * 8, 25 * 8, 49 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(61 * 8, 25 * 8, 61 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(12 * 8, 31 * 8, 12 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(40 * 8, 31 * 8, 40 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(45 * 8, 31 * 8, 45 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(20 * 8, 40 * 8, 20 * 8, 46 * 8, 2);
        zp_realtime_status(1000);

        lable_sdk.DrawText(7 * 8, 7 * 8, "地", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 11 * 8, "区", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(15 * 8, 10 * 8, "上海", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(30 * 8, 10 * 8, "广州", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(44 * 8, 10 * 8, "深圳", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(58 * 8, 10 * 8, "北京", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(72 * 8, 10 * 8, "西安", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 16 * 8, "编", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 21 * 8, "号", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(15 * 8, 19 * 8, "209", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(30 * 8, 19 * 8, "30", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(44 * 8, 19 * 8, "56", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(58 * 8, 19 * 8, "78", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(72 * 8, 19 * 8, "94", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 26 * 8, "目的", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(18 * 8, 26 * 8, "目的站名称", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(49 * 8, 26 * 8, "包装", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(62 * 8, 26 * 8, "木箱", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 31 * 8, "件", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 35 * 8, "数", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(17 * 8, 34 * 8, "第      件", 0, 0);
        String str = String.valueOf(num);
        lable_sdk.DrawText(22 * 8, 34 * 8, str, 0, 0);
        zp_realtime_status(1000);
        // DrawTextSpike(21,34,1,0,"0");
        // SetAddNum(1,0,0,0,1,0);
        lable_sdk.DrawText(41 * 8, 31 * 8, "单", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(41 * 8, 35 * 8, "号", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(47 * 8, 31 * 8, "88779177", 0x20, 0xA);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 41 * 8, "收货人", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(22 * 8, 41 * 8, "张三", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(22 * 8, 48 * 8, "周杰伦  宣传部", 0x0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(22 * 8, 51 * 8, "2012-05-17", 0x0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(54 * 8, 47 * 8, "精准汽运", 0x04, 0xa);
        zp_realtime_status(1000);
    }// DrawContent

    /**
     * spike页面布局
     */
    private void DrawContentSpike() {
        lable_sdk.DrawText(7 * 8, 0, "xx物流", 0x00, 0x0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 3 * 8, "ZICOX", 0x00, 0x0);
        zp_realtime_status(1000);
        lable_sdk.DrawCode1D(21 * 8, 0, "0123456789", 0x1, 0x03, (0x7 * 8));
        zp_realtime_status(1000);
        lable_sdk.DrawCode1D(6 * 8, 7 * 8, "0123456789", 0x1, 0x43, (0x7 * 8));
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 7 * 8, 82 * 8, 7 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 16 * 8, 82 * 8, 16 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 25 * 8, 82 * 8, 25 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 31 * 8, 82 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 40 * 8, 82 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 46 * 8, 82 * 8, 46 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(6 * 8, 7 * 8, 6 * 8, 46 * 8, 2);
        for (int j = 0; j < 82; j += 14) {
            lable_sdk.DrawLine((12 + j) * 8, 7 * 8, (12 + j) * 8, 25 * 8, 2);
            zp_realtime_status(1000);
        }
        lable_sdk.DrawLine(82 * 8, 7 * 8, 82 * 8, 46 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(17 * 8, 25 * 8, 17 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(49 * 8, 25 * 8, 49 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(61 * 8, 25 * 8, 61 * 8, 31 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(12 * 8, 31 * 8, 12 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(40 * 8, 31 * 8, 40 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(45 * 8, 31 * 8, 45 * 8, 40 * 8, 2);
        zp_realtime_status(1000);
        lable_sdk.DrawLine(20 * 8, 40 * 8, 20 * 8, 46 * 8, 2);
        zp_realtime_status(1000);

        lable_sdk.DrawText(7 * 8, 7 * 8, "地", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 11 * 8, "区", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(15 * 8, 10 * 8, "上海", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(30 * 8, 10 * 8, "广州", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(44 * 8, 10 * 8, "深圳", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(58 * 8, 10 * 8, "北京", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(72 * 8, 10 * 8, "西安", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 16 * 8, "编", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 21 * 8, "号", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(15 * 8, 19 * 8, "209", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(30 * 8, 19 * 8, "30", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(44 * 8, 19 * 8, "56", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(58 * 8, 19 * 8, "78", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(72 * 8, 19 * 8, "94", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 26 * 8, "目的", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(18 * 8, 26 * 8, "目的站名称", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(49 * 8, 26 * 8, "包装", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(62 * 8, 26 * 8, "木箱", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 31 * 8, "件", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 35 * 8, "数", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(17 * 8, 34 * 8, "第      件", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawTextSpike(21 * 8, 34 * 8, "0", 0, 0, 0);
        zp_realtime_status(1000);
        lable_sdk.SetSpike(1, 0, 1);
        zp_realtime_status(1000);
        lable_sdk.DrawText(41 * 8, 31 * 8, "单", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(41 * 8, 35 * 8, "号", 0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(47 * 8, 31 * 8, "88779177", 0x20, 0xA);
        zp_realtime_status(1000);
        lable_sdk.DrawText(7 * 8, 41 * 8, "收货人", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(22 * 8, 41 * 8, "张三", 0x20, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(22 * 8, 48 * 8, "周杰伦  宣传部", 0x0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(22 * 8, 51 * 8, "2012-05-17", 0x0, 0);
        zp_realtime_status(1000);
        lable_sdk.DrawText(54 * 8, 47 * 8, "精准汽运", 0x04, 0xa);
        zp_realtime_status(1000);
    }// DrawContentSpike

    /**
     * 远程连接设备的蓝牙列表
     * @return
     */
    public boolean ListBluetoothDevice() {
        final List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ListView listView = (ListView) findViewById(R.id.listView1);
        SimpleAdapter m_adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2, new String[] {
                "DeviceName", "BDAddress" }, new int[] {
                android.R.id.text1, android.R.id.text2 });
        listView.setAdapter(m_adapter);

        if ((myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
            Toast.makeText(this, "没有找到蓝牙适配器", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!myBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 2);
        }

        Set<BluetoothDevice> pairedDevices = myBluetoothAdapter
                .getBondedDevices();
        if (pairedDevices.size() <= 0)
            return false;
        for (BluetoothDevice device : pairedDevices) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("DeviceName", device.getName());
            map.put("BDAddress", device.getAddress());
            list.add(map);
        }
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                SelectedBDAddress = list.get(position).get("BDAddress");
                if (((ListView) parent).getTag() != null) {
                    ((View) ((ListView) parent).getTag())
                            .setBackgroundDrawable(null);
                }
                ((ListView) parent).setTag(view);
                view.setBackgroundColor(Color.YELLOW);
            }
        });
        return true;
    }// ListBluetoothDevice

    /**
     * 输出信息
     * @param str
     */
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
