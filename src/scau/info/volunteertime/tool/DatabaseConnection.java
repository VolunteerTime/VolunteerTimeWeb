/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-8-16
 */
package scau.info.volunteertime.tool;

import java.sql.Connection;

/**
 * @author �̳���
 * 
 */
public interface DatabaseConnection {
	public Connection getConnection() throws Exception;

	public void close();
}
