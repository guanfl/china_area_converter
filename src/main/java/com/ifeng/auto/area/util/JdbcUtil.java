/**
 * ClassName : JdbcUtil.java
 * Create on : 2016年3月13日
 * Author : guanfl
 * Copyrights 2016 IBM All rights reserved.
 */
package com.ifeng.auto.area.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private static BasicConfiguration bc = BasicConfiguration.getInstance();

	private JdbcUtil() {
	}

	static {
		try {
			Class.forName(bc.getValue("driverClassName"));
		} catch (ClassNotFoundException e) {
			System.err.println("在classPath下没有找到数据库驱动...");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(bc.getValue("url"), bc.getValue("username"), bc.getValue("password"));
		} catch (SQLException e) {
			System.err.println("数据库连接失败...");
			return conn;
		}
		System.out.println("数据库连接成功");
		return conn;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
