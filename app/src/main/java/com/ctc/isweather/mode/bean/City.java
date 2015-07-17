package com.ctc.isweather.mode.bean;

/**
 * Created by TPIAN on 15/7/16.
 */
public class City {
    private String id,name;

    public City(){}

    public City(String name,String id){
        setName(name);
        setId(id);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
