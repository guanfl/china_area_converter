/**
* ClassName : DataPersistence.java
* Create on ：2016年9月22日
* Copyrights 2016 guanfl All rights reserved.
* Email : guanfl@163.com
*/
package com.ifeng.auto.area.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ifeng.auto.area.entity.AreaLevel;
import com.ifeng.auto.area.util.JdbcUtil;

public class DataPersistence {
    /**
     * 往数据库中插入原始数据（值）
     */
    public void insertDataToDB(List<AreaLevel> areaLevels) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO t_china_area"
                + " (code,name,city_code,city_name,province_code, province_name,level)" + " VALUES(?,?,?,?,?,?,?)";
        try {
            conn = JdbcUtil.getConnection();
            if(null == conn ) return ;
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            for (AreaLevel area : areaLevels) {
                int index = 1;
                pstmt.setString(index++, area.getCode());
                pstmt.setString(index++, area.getName());
                pstmt.setString(index++, area.getCityCode());
                pstmt.setString(index++, area.getCityName());
                pstmt.setString(index++, area.getProvinceCode());
                pstmt.setString(index++, area.getPovinceName());
                pstmt.setInt(index++, area.getLevel());                                       
                pstmt.addBatch();
            }
            // execute batch
            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, null);
        }
    }
}
