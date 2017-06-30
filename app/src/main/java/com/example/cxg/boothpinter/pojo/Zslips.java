package com.example.cxg.boothpinter.pojo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * description: 详细信息实体
 * author: xg.chen
 * date: 2017/6/30 14:09
 * version: 1.0
*/
@DatabaseTable(tableName = "Zslips")
public class Zslips implements Serializable,KvmSerializable {

    @DatabaseField
    private String Znum;
    @DatabaseField
    private String Vbeln;
    @DatabaseField
    private String Werks;
    @DatabaseField
    private String Ebeln;
    @DatabaseField
    private String Matnr;
    @DatabaseField
    private String Maktx;
    @DatabaseField
    private String Zlichn;
    @DatabaseField
    private String Lgmng;
    @DatabaseField
    private String Meins;
    @DatabaseField
    private String Erfmg;
    @DatabaseField
    private String Erfme;
    @DatabaseField
    private String Zmenge;

    public Zslips() {
        super();
    }

    public Zslips(String znum, String vbeln, String werks, String ebeln, String matnr, String maktx, String zlichn, String lgmng, String meins, String erfmg, String erfme, String zmenge) {
        Znum = znum;
        Vbeln = vbeln;
        Werks = werks;
        Ebeln = ebeln;
        Matnr = matnr;
        Maktx = maktx;
        Zlichn = zlichn;
        Lgmng = lgmng;
        Meins = meins;
        Erfmg = erfmg;
        Erfme = erfme;
        Zmenge = zmenge;
    }

    public String getZnum() {
        return Znum;
    }

    public void setZnum(String znum) {
        Znum = znum;
    }

    public String getVbeln() {
        return Vbeln;
    }

    public void setVbeln(String vbeln) {
        Vbeln = vbeln;
    }

    public String getWerks() {
        return Werks;
    }

    public void setWerks(String werks) {
        Werks = werks;
    }

    public String getEbeln() {
        return Ebeln;
    }

    public void setEbeln(String ebeln) {
        Ebeln = ebeln;
    }

    public String getMatnr() {
        return Matnr;
    }

    public void setMatnr(String matnr) {
        Matnr = matnr;
    }

    public String getMaktx() {
        return Maktx;
    }

    public void setMaktx(String maktx) {
        Maktx = maktx;
    }

    public String getZlichn() {
        return Zlichn;
    }

    public void setZlichn(String zlichn) {
        Zlichn = zlichn;
    }

    public String getLgmng() {
        return Lgmng;
    }

    public void setLgmng(String lgmng) {
        Lgmng = lgmng;
    }

    public String getMeins() {
        return Meins;
    }

    public void setMeins(String meins) {
        Meins = meins;
    }

    public String getErfmg() {
        return Erfmg;
    }

    public void setErfmg(String erfmg) {
        Erfmg = erfmg;
    }

    public String getErfme() {
        return Erfme;
    }

    public void setErfme(String erfme) {
        Erfme = erfme;
    }

    public String getZmenge() {
        return Zmenge;
    }

    public void setZmenge(String zmenge) {
        Zmenge = zmenge;
    }

    @Override
    public String toString() {
        return "Zslips{" +
                "Znum='" + Znum + '\'' +
                ", Vbeln='" + Vbeln + '\'' +
                ", Werks='" + Werks + '\'' +
                ", Ebeln='" + Ebeln + '\'' +
                ", Matnr='" + Matnr + '\'' +
                ", Maktx='" + Maktx + '\'' +
                ", Zlichn='" + Zlichn + '\'' +
                ", Lgmng='" + Lgmng + '\'' +
                ", Meins='" + Meins + '\'' +
                ", Erfmg='" + Erfmg + '\'' +
                ", Erfme='" + Erfme + '\'' +
                ", Zmenge='" + Zmenge + '\'' +
                '}';
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }
}
