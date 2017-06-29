package com.example.cxg.boothpinter.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cxg.boothpinter.R;
import com.example.cxg.boothpinter.activity.BlueBoothPinterDetailActivity;
import com.example.cxg.boothpinter.pojo.Ztwm004;

import java.util.ArrayList;
import java.util.List;
/**
 * description: 系统物料列表适配器
 * author: xg.chen
 * date: 2017/6/29 13:34
 * version: 1.0
*/

public class BlueBoothPinterAdapter extends BaseAdapter {

    public List<Ztwm004> ztwm004List = new ArrayList<Ztwm004>();
    private LayoutInflater layoutInflater;
    private Activity activity;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public BlueBoothPinterAdapter(List<Ztwm004> parameterList, Activity activity) {
        params.leftMargin = 2;
        this.ztwm004List = parameterList;
        this.layoutInflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return ztwm004List.size();
    }

    @Override
    public Object getItem(int position) {
        return ztwm004List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*列表字段赋值*/
        ViewHolder hodler;
        if (convertView == null) {
            hodler = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.layout_sysinfo, null);
            hodler.matnr = (TextView) convertView.findViewById(R.id.matnr);//物料编码
            hodler.maktx = (TextView) convertView.findViewById(R.id.maktx);//物料描述
            hodler.werks = (TextView) convertView.findViewById(R.id.werks);//工厂
            hodler.znum = (TextView) convertView.findViewById(R.id.znum);//单据号
            hodler.zlichn = (TextView) convertView.findViewById(R.id.zlichn);//版本
            hodler.lgmng = (TextView) convertView.findViewById(R.id.lgmng);//交货数量
            hodler.Zmenge = (TextView) convertView.findViewById(R.id.Zmenge);//未清数量
            hodler.Meins = (TextView) convertView.findViewById(R.id.Meins);//单位
            hodler.lgmng = (TextView) convertView.findViewById(R.id.lgmng);//托盘数量
            hodler.erfme = (TextView) convertView.findViewById(R.id.erfmgl);//托盘单位
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHolder) convertView.getTag();
            resetViewHolder(hodler);
        }
        Ztwm004 ztwm004 = ztwm004List.get(position);
        hodler.matnr.setText(ztwm004.getMatnr());
        hodler.maktx.setText(ztwm004.getEMaktx());
        hodler.werks.setText(ztwm004.getWerks());
        hodler.znum.setText(ztwm004.getZnum());
        hodler.zlichn.setText(ztwm004.getZlichn());
        hodler.lgmng.setText(ztwm004.getLgmng());
        hodler.Zmenge.setText(ztwm004.getZmenge());
        hodler.Meins.setText(ztwm004.getMeins());
        hodler.lgmng.setText(ztwm004.getLgmng());
        hodler.erfme.setText(ztwm004.getErfme());

        /*设置列表的点击事件*/
        final int n = position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BlueBoothPinterDetailActivity.class);
                intent.putExtra("ztwm004",ztwm004List.get(n));
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });

        return convertView;
    }

    /**
     * reset view holder
     *
     * @param pViewHodler
     */
    private void resetViewHolder(ViewHolder pViewHodler) {
        pViewHodler.matnr.setText(null);
        pViewHodler.maktx.setText(null);
        pViewHodler.lgmng.setText(null);
        pViewHodler.Meins.setText(null);
        pViewHodler.zlichn.setText(null);
        pViewHodler.werks.setText(null);
        pViewHodler.znum.setText(null);
        pViewHodler.Zmenge.setText(null);
        pViewHodler.erfme.setText(null);
    }

    /**
     * view holder
     */
    private static class ViewHolder {
        TextView matnr = null;
        TextView maktx = null;
        TextView lgmng = null;
        TextView zlichn = null;
        TextView werks = null;
        TextView znum = null;
        TextView Zmenge = null;
        TextView Meins = null;
        TextView erfme = null;

    }
}
