/**
* ClassName : China.java
* Create on ：2016年9月22日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package com.ifeng.auto.area.entity;

public class AreaLevel {
    private String code;    //地区编码
    private String name;    //地区名称
    private String cityCode;    //城市编码
    private String cityName;    //城市名称
    private String provinceCode;//省编码
    private String povinceName;  //省名称
    private Integer level;      //等级 1,2,3

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPovinceName() {
        return povinceName;
    }

    public void setPovinceName(String povinceName) {
        this.povinceName = povinceName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "AreaLevel [code=" + code + ", name=" + name + ", cityCode=" + cityCode + ", cityName=" + cityName + ", provinceCode=" + provinceCode + ", povinceName=" + povinceName + ", level=" + level + "]";
    }
}
