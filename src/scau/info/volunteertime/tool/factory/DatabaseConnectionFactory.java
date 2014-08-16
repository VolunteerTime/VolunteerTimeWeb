/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-8-16
 */
package scau.info.volunteertime.tool.factory;

import scau.info.volunteertime.tool.DatabaseConnection;
import scau.info.volunteertime.tool.MySQLDatabaseConnection;

/**
 * @author �̳���
 * 
 */
public class DatabaseConnectionFactory {
	public static DatabaseConnection getDatabaseConnection() {
		return new MySQLDatabaseConnection();
	}
}
