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
import com.example.cxg.boothpinter.activity.SysinfoDetailActivity;
import com.example.cxg.boothpinter.pojo.Sysinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统物料列表适配器
 * Created by Administrator on 2017/5/2.
 */

public class SysinfoAdapter extends BaseAdapter {

    public List<Sysinfo> sysinfoList = new ArrayList<Sysinfo>();
    private LayoutInflater layoutInflater;
    private Activity activity;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    public SysinfoAdapter(List<Sysinfo> parameterList, Activity activity) {
        params.leftMargin = 2;
        this.sysinfoList = parameterList;
        this.layoutInflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return sysinfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return sysinfoList.get(position);
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
            hodler.matnr = (TextView) convertView.findViewById(R.id.matnr);
            hodler.maktx = (TextView) convertView.findViewById(R.id.maktx);
            hodler.lgmng = (TextView) convertView.findViewById(R.id.lgmng);
            hodler.meins = (TextView) convertView.findViewById(R.id.meins);
            hodler.zlichn = (TextView) convertView.findViewById(R.id.zlichn);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHolder) convertView.getTag();
            resetViewHolder(hodler);
        }
        Sysinfo sysinfo = sysinfoList.get(position);
        hodler.matnr.setText(sysinfo.getMatnr());
        hodler.maktx.setText(sysinfo.getMaktx());
        hodler.lgmng.setText(sysinfo.getLgmng());
        hodler.meins.setText(sysinfo.getMeins());
        hodler.zlichn.setText(sysinfo.getZlichn());

        /*设置列表的点击事件*/
        final int n = position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SysinfoDetailActivity.class);
                intent.putExtra("sysinfo",sysinfoList.get(n));
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
        pViewHodler.meins.setText(null);
        pViewHodler.zlichn.setText(null);
    }

    /**
     * view holder
     */
    private static class ViewHolder {
        public TextView matnr = null;
        public TextView maktx = null;
        public TextView lgmng = null;
        public TextView meins = null;
        public TextView zlichn = null;
    }
}
