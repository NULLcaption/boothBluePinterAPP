package com.example.cxg.boothpinter.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxg.boothpinter.R;
import com.example.cxg.boothpinter.application.XPPApplication;
import com.example.cxg.boothpinter.pojo.Zslips;
import com.example.cxg.boothpinter.provider.DataProviderFactory;
import com.example.cxg.boothpinter.utils.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

/**
 * 物料列表详细表单
 * Created by Administrator on 2017/5/3.
 */

public class BlueBoothPinterDetailActivity extends AppCompatActivity {

    private TextView name, Lifnr;
    private TextView znum;
    private TextView werks;
    private TextView matnr, Maktx;
    private TextView zlichn;
    private TextView lgmng;
    private TextView Zmenge;
    private EditText Zgrdate, Zproddate;
    private TextView erfmgl,IZipcode,Charg;
    private EditText mark, Qcnum;
    private Button printer, backHome, exit;
    private Zslips zslips;
    private DatePicker zproddateDatePicker, zgrdateDatePicker;
    private Dialog waitingDialog;
    private Dialog overdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sysinfo_detail);
        initView();
        initData();
        //时间选择控件
        selectDatePicker();
    }

    /**
     * init view
     */
    private void initView() {
        znum = (TextView) findViewById(R.id.znum);//单据号
        name = (TextView) findViewById(R.id.name1);//供应商名称
        Lifnr = (TextView) findViewById(R.id.Lifnr);//供应商编号
        werks = (TextView) findViewById(R.id.werks);//工厂
        matnr = (TextView) findViewById(R.id.matnr);//物料
        Maktx = (TextView) findViewById(R.id.Maktx);//物料描述
        zlichn = (TextView) findViewById(R.id.zlichn);//版本
        lgmng = (TextView) findViewById(R.id.lgmng);//交货单数量
        Zmenge = (TextView) findViewById(R.id.Zmenge);//未清数量
        Zgrdate = (EditText) findViewById(R.id.Zgrdate);//生产日期
        Zgrdate.setOnClickListener(BtnClicked);
        Zproddate = (EditText) findViewById(R.id.Zproddate);//入库日期
        Zproddate.setOnClickListener(BtnClicked);
        erfmgl = (TextView) findViewById(R.id.erfmgl);//托盘标准数量
        mark = (EditText) findViewById(R.id.mark);//标签数
        Qcnum = (EditText) findViewById(R.id.Qcnum);//车牌号
        IZipcode = (TextView) findViewById(R.id.IZipcode);
        Charg = (TextView) findViewById(R.id.Charg);

        findViewById(R.id.printer).setOnClickListener(BtnClicked);
        findViewById(R.id.back_home).setOnClickListener(BtnClicked);
        findViewById(R.id.exit).setOnClickListener(BtnClicked);

    }

    /**
     * init data for service
     */
    private void initData() {
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            zslips = (Zslips) bun.get("zslips");
        }
        if (zslips != null) {
            name.setText(zslips.getEName1());
            Lifnr.setText(zslips.getLifnr());
            znum.setText(zslips.getZnum());
            werks.setText(zslips.getWerks());
            matnr.setText(zslips.getMatnr());
            Maktx.setText(zslips.getMaktx());
            zlichn.setText(zslips.getZlichn());
            lgmng.setText(zslips.getLgmng());
            Zmenge.setText(zslips.getZmenge());
            //IZipcode.setText(zslips.getZipcode());
            //默认时间设置
            SimpleDateFormat dateString = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNow = new Date();
            Zgrdate.setText(dateString.format(dateNow));
            Zproddate.setText(dateString.format(dateNow));
            erfmgl.setText(zslips.getErfmg());
            //标签个数
            if (!"0.0".equals(zslips.getZmenge()) && !"0.0".equals(zslips.getErfmg())) {
                //标签个数=托盘标准数量/未清数量
                double zmenge1 = parseDouble(zslips.getZmenge());
                double erfmgl = Double.parseDouble(zslips.getErfmg());
                double mark1 = erfmgl / zmenge1;
                int i0 = (int) Math.round(mark1);
                mark.setText(String.valueOf(i0));
            } else {
                mark.setText("0");//默认标签是0个
            }
        }
    }

    /**
     * description: 选择时间控件
     * author: xg.chen
     * date: 2017/6/26 11:15
     * version: 1.0
     */
    private void selectDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        Zproddate.setText(now.split(" ")[0]);
        Zgrdate.setText(now.split(" ")[0]);

        zproddateDatePicker = new DatePicker(this, new DatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                Zproddate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        zproddateDatePicker.showSpecificTime(false); // 不显示时和分false
        zproddateDatePicker.setIsLoop(false); // 不允许循环滚动*/

        zgrdateDatePicker = new DatePicker(this, new DatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                Zgrdate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        zgrdateDatePicker.showSpecificTime(false); // 显示时和分true
        zgrdateDatePicker.setIsLoop(true); // 允许循环滚动
    }

    /**
     * 按钮监听事件
     */
    private View.OnClickListener BtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.printer:
                    Zslips zslips_001 = new Zslips();
                    if ("".equals(Zmenge.getText().toString().trim())
                            || "0.0".equals(Zmenge.getText().toString().trim())) {
                        Toast.makeText(getApplicationContext(), "已全部入库,请补打!", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        zslips_001.setZmenge(Zmenge.getText().toString().trim());
                    }
                    if ("".equals(Qcnum.getText().toString().trim())) {
                        Toast.makeText(getApplicationContext(), "车牌号必填!", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        zslips_001.setQcnum(Qcnum.getText().toString().trim());
                    }
                    if (!"".equals(erfmgl.getText().toString().trim())
                            || "0.0".equals(erfmgl.getText().toString().trim())) {
                        // 正则判断下是否输入值为数字
                        Pattern p2 = Pattern.compile("^[0-9]*[1-9][0-9]*$");
                        String erfmgl1 = erfmgl.getText().toString().trim();
                        Matcher matcher = p2.matcher(erfmgl1);
                        if (!matcher.matches()) {
                            Toast.makeText(getApplicationContext(), "标准托盘数量数必须填，且必须为数字...", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        zslips_001.setLgmng(erfmgl.getText().toString().trim());
                    } else {
                        Toast.makeText(getApplicationContext(), "请输入标准托盘数量!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (!"".equals(mark.getText().toString().trim())
                            || !"0".equals(mark.getText().toString().trim())) {
                        // 正则判断下是否输入值为数字
                        Pattern p2 = Pattern.compile("^[0-9]*[1-9][0-9]*$");
                        String mark1 = mark.getText().toString().trim();
                        Matcher matcher1 = p2.matcher(mark1);
                        if (!matcher1.matches()) {
                            Toast.makeText(getApplicationContext(), "标签数量数必须填，且必须为为数字...", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        zslips_001.setMenge(mark.getText().toString().trim());
                    } else {
                        Toast.makeText(getApplicationContext(), "请输入标签数量!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    zslips_001.setLifnr(Lifnr.getText().toString());
                    zslips_001.setMatnr(matnr.getText().toString());
                    zslips_001.setZlichn(zlichn.getText().toString());
                    zslips_001.setZgrdate(Zgrdate.getText().toString());
                    zslips_001.setZproddate(Zproddate.getText().toString());
                    zslips_001.setZnum(znum.getText().toString());
                    //工厂
                    String factory = werks.getText().toString();
                    if (factory.equals("湖州工厂")) {
                        zslips_001.setWerks("1000");
                    } else if (factory.equals("成都工厂")) {
                        zslips_001.setWerks("2000");
                    } else if (factory.equals("天津工厂")) {
                        zslips_001.setWerks("3000");
                    }
                    //单位{BAO=包, GUO=锅, TAO=套, BEI=杯, BOX=箱, KG=公斤, DAI=袋, HE=盒, GE=个}
                    if ("个".equals(zslips.getMeins())) {
                        zslips_001.setMeins("GE");
                    } else if ("盒".equals(zslips.getMeins())){
                        zslips_001.setMeins("HE");
                    } else if ("袋".equals(zslips.getMeins())){
                        zslips_001.setMeins("DAI");
                    } else if ("公斤".equals(zslips.getMeins())){
                        zslips_001.setMeins("KG");
                    } else if ("箱".equals(zslips.getMeins())){
                        zslips_001.setMeins("BOX");
                    } else if ("杯".equals(zslips.getMeins())){
                        zslips_001.setMeins("BEI");
                    } else if ("套".equals(zslips.getMeins())){
                        zslips_001.setMeins("TAO");
                    } else if ("锅".equals(zslips.getMeins())){
                        zslips_001.setMeins("GUO");
                    } else if ("包".equals(zslips.getMeins())){
                        zslips_001.setMeins("BAO");
                    }

                    //生成托盘编码
                    new getZipcodeTask().execute(zslips_001);
                    System.out.println("IZipcode==========>"+IZipcode.getText().toString());
                    //打印
                    if (!"".equals(IZipcode.getText().toString())){
                        Intent intent = new Intent(BlueBoothPinterDetailActivity.this, BoothActivity.class);

                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    } else {
                        Toast.makeText(getApplicationContext(), "无返回编码,无发打印!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    break;
                case R.id.back_home:
                    XPPApplication.exit(BlueBoothPinterDetailActivity.this);
                    break;
                case R.id.exit:
                    XPPApplication.exit(BlueBoothPinterDetailActivity.this);
                    break;
                //生产日期
                case R.id.Zgrdate:
                    // 日期格式为yyyy-MM-dd
                    zgrdateDatePicker.show(Zgrdate.getText().toString());
                    break;
                //库存日期
                case R.id.Zproddate:
                    // 日期格式为yyyy-MM-dd
                    zproddateDatePicker.show(Zgrdate.getText().toString());
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * description: 生成托盘编码
     * author: xg.chen
     * date: 2017/7/4 14:01
     * version: 1.0
     */
    private class getZipcodeTask extends AsyncTask<Object, Integer, List<Zslips>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWaitingDialog();
        }

        @Override
        protected List<Zslips> doInBackground(Object... params) {
            Object zslips_001 = params[0];
            return DataProviderFactory.getProvider().getZipcode((Zslips) zslips_001);
        }

        @Override
        protected void onPostExecute(List<Zslips> result) {
            dismissWaitingDialog();
            if (result.size() != 0) {
                IZipcode.setText(result.get(0).getZipcode());
                Charg.setText(result.get(0).getZipcode());
            } else {
                Toast.makeText(getApplicationContext(), "连接超时...退出稍后重试...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * description: 加载图片开始
     * author: xg.chen
     * date: 2017/6/26 11:56
     * version: 1.0
     */
    private void showWaitingDialog() {
        if (waitingDialog == null) {

            waitingDialog = new Dialog(this, R.style.TransparentDialog);
            waitingDialog.setContentView(R.layout.login_waiting_dialog);
            DialogInterface.OnShowListener showListener = new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    ImageView img = (ImageView) waitingDialog.findViewById(R.id.loading);
                    ((AnimationDrawable) img.getDrawable()).start();
                }
            };
            DialogInterface.OnCancelListener cancelListener = new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    // updateButtonLook(false);
                }
            };
            waitingDialog.setOnShowListener(showListener);
            waitingDialog.setCanceledOnTouchOutside(false);
            waitingDialog.setOnCancelListener(cancelListener);
            waitingDialog.show();
        }
    }

    /**
     * description: 加载结束
     * author: xg.chen
     * date: 2017/6/26 11:56
     * version: 1.0
     */
    private void dismissWaitingDialog() {
        if (waitingDialog != null) {
            ImageView img = (ImageView) waitingDialog.findViewById(R.id.loading);
            ((AnimationDrawable) img.getDrawable()).stop();

            waitingDialog.dismiss();
            waitingDialog = null;
        }
    }

}
