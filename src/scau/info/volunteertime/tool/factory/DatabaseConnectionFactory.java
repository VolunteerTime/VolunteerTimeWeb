/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-8-16
 */
package scau.info.volunteertime.tool.factory;

import scau.info.volunteertime.tool.DatabaseConnection;
import scau.info.volunteertime.tool.MySQLDatabaseConnection;

/**
 * @author 蔡超敏
 * 
 */
public class DatabaseConnectionFactory {
	public static DatabaseConnection getDatabaseConnection() {
		return new MySQLDatabaseConnection();
	}
}
