package com.example.admin.trial;

/**
 * Created by Admin on 23/03/2018.
 */

public class dataextract {
    String name,type,deathtoll,injuries,area,year;

    public dataextract(String name, String type, String area, String year, String deathtoll, String injuries) {
        this.name = name;
        this.type = type;
        this.deathtoll = deathtoll;
        this.injuries = injuries;
        this.area = area;
        this.year = year;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeathtoll() {
        return deathtoll;
    }

    public void setDeathtoll(String deathtoll) {
        this.deathtoll = deathtoll;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
