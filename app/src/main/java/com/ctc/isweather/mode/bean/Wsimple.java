package com.ctc.isweather.mode.bean;

/**
 * Created by chris on 2015/7/17.
 */
public class Wsimple {
    private String cityid;
    private String cityname;
    private String pm25;
    private String sd;//湿度
    private String maintemp; //主要温度

    public Wsimple(){}

    public Wsimple(String cityname,String cityid,String pm25,String sd,String maintmp){
        setCityname(cityname);
        setCityid(cityid);
        setPm25(pm25);
        setSd(sd);
        setMaintemp(maintmp);
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getMaintemp() {
        return maintemp;
    }

    public void setMaintemp(String maintemp) {
        this.maintemp = maintemp;
    }
}
