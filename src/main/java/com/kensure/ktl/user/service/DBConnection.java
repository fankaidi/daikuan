package com.kensure.ktl.user.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		// localhost指本机，也可以用本地ip地址代替，3306为MySQL数据库的默认端口号，“user”为要连接的数据库名
		String url = "jdbc:mysql://localhost:3366/jipiaodb";
		// 填入数据库的用户名跟密码
		String username = "myslh";
		String password = "myslh";
		String sql = "select * from user_info";// 编写要执行的sql语句，此处为从user表中查询所有用户的信息
		try {
			Class.forName(driver);// 加载驱动程序，此处运用隐式注册驱动程序的方法
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, username,
					password);// 创建连接对象
			Statement st = con.createStatement();// 创建sql执行对象
			ResultSet rs = st.executeQuery(sql);// 执行sql语句并返回结果集
			while (rs.next())// 对结果集进行遍历输出
			{
				System.out.println("username: " + rs.getString(1));// 通过列的标号来获得数据
				
			}
			// 关闭相关的对象
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}