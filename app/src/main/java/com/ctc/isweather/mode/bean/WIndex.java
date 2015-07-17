package com.ctc.isweather.mode.bean;

/**
 * Created by TPIAN on 15/7/13.
 */
public class WIndex {

    /**
     * title :  指数名字
     * zs    :  指数的数值
     * tipt  :  建议
     * des   :  描述
     */
    String title,zs,tipt,des;

    public WIndex(){}

    public WIndex(String title,String zs,String tipt,String des){
        setTitle(title);
        setZs(zs);
        setTipt(tipt);
        setDes(des);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
