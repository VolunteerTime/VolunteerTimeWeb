/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-8-16
 */
package scau.info.volunteertime.tool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author 蔡超敏
 * 
 */
public class MySQLDatabaseConnection implements DatabaseConnection {

	private DataSource ds = null;
	private Connection conn = null;

	@Override
	public Connection getConnection() throws Exception {
		System.out.println("初始化数据库");
		// 初始化查找命名空间
		Context ctx = new InitialContext();
		// 参数java:/comp/env为固定路径
		Context envContext = (Context) ctx.lookup("java:/comp/env");
		// 参数jdbc/mysqlds为数据源和JNDI绑定的名字
		ds = (DataSource) envContext.lookup("jdbc/scau");
		conn = ds.getConnection();
		System.out.println("初始化数据库成功");
		return conn;
	}

	@Override
	public void close() {
		try {
			System.out.println("数据库准备关闭");
			conn.close();
		} catch (SQLException e) {
			System.out.println("数据库关闭失败");
			e.printStackTrace();
		}
		System.out.println("数据库关闭成功");
	}

}
