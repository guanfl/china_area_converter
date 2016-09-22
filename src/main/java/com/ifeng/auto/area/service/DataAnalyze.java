/**
* ClassName : DataAnalyze.java
* Create on ：2016年9月22日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package com.ifeng.auto.area.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ifeng.auto.area.dao.DataPersistence;
import com.ifeng.auto.area.entity.AreaLevel;

public class DataAnalyze {
    private  String  LAST_PROVINCE_CODE_PRE = "11";
    private  String LAST_PROVINCE_CODE = "110000";
    private  String  LAST_PROVINCE_NAME = "北京市";
    
    private  String LAST__CITY_CODE_PRE = "01";
    private  String LAST_CITY_CODE = "110100";
    private  String LAST_CITY_NAME = "市辖区";
    
    private  String DEFUALT_EMPTY_CODE = "00";
    private  String DEFUALT_EMPTY_STR = "-";

    
    public List<AreaLevel> analyzeTxt() {
        BufferedReader br = null;
        List<AreaLevel> areaLevels = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(new File("china.txt")));
            for(String line = null; (line = br.readLine()) != null;){
                line = line.trim();
                if (null == line || "".equals(line)) break;
                String[] elems = line.split("\\s+");
                String code = StringUtils.trimToEmpty(elems[0]);
                String name = StringUtils.trimToEmpty(elems[1]);
                areaLevels.add(getData(code,name));
            }
            return areaLevels;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return null;
    }
    
    /**
     * 
     * @param line
     * @param defualtProvinceCode
     * @param defualtCityCode
     * @return
     */
    public AreaLevel getData(String code,String name){
        String prov = StringUtils.substring(code, 0, 2);
        String city = StringUtils.substring(code, 2, 4);
        String region = StringUtils.substring(code, 4, 6);
        
        AreaLevel areaLevel = new AreaLevel();
        if(!StringUtils.equals(LAST_PROVINCE_CODE_PRE, prov)){
            LAST_PROVINCE_CODE_PRE = prov;
            LAST_PROVINCE_CODE = code;
        }
        //省 province
        if(StringUtils.startsWith(code, LAST_PROVINCE_CODE_PRE) && StringUtils.equals(city,DEFUALT_EMPTY_CODE) && StringUtils.equals(region,DEFUALT_EMPTY_CODE)){
            LAST_PROVINCE_NAME = name;
            areaLevel.setName(name);
            areaLevel.setCode(code);
            areaLevel.setCityCode(DEFUALT_EMPTY_CODE);
            areaLevel.setCityName(DEFUALT_EMPTY_STR);
            areaLevel.setProvinceCode(DEFUALT_EMPTY_CODE);
            areaLevel.setPovinceName(DEFUALT_EMPTY_STR);
            areaLevel.setLevel(1);
            return areaLevel;
        }
        
        if(!StringUtils.equals(LAST__CITY_CODE_PRE, city)){
            LAST__CITY_CODE_PRE = city;
            LAST_CITY_CODE = code;
        }
        //市 city 
        if(StringUtils.startsWith(code, LAST_PROVINCE_CODE_PRE)&& StringUtils.equals(city,LAST__CITY_CODE_PRE) && StringUtils.equals(region,DEFUALT_EMPTY_CODE)){
            LAST_CITY_NAME = name;
            areaLevel.setName(name);
            areaLevel.setCode(code);
            areaLevel.setCityCode(DEFUALT_EMPTY_CODE);
            areaLevel.setCityName(DEFUALT_EMPTY_STR);
            areaLevel.setProvinceCode(LAST_PROVINCE_CODE);
            areaLevel.setPovinceName(LAST_PROVINCE_NAME);
            areaLevel.setLevel(2);
            return areaLevel;
        }
        areaLevel.setName(name);
        areaLevel.setCode(code);
        areaLevel.setCityCode(LAST_CITY_CODE);
        areaLevel.setCityName(LAST_CITY_NAME);
        areaLevel.setProvinceCode(LAST_PROVINCE_CODE);
        areaLevel.setPovinceName(LAST_PROVINCE_NAME);
        areaLevel.setLevel(3);
        return areaLevel;
    }
    
    public static void main(String args[]){
        List<AreaLevel> areaLevels = new DataAnalyze().analyzeTxt();
        DataPersistence persistence = new DataPersistence();
        //持久化数据
        persistence.insertDataToDB(areaLevels);
        System.out.println("DONE!");
    }

}
