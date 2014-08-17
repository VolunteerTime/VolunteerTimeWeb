/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-8-16
 */
package scau.info.volunteertime.tool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author �̳���
 * 
 */
public class MySQLDatabaseConnection implements DatabaseConnection {

	private DataSource ds = null;
	private Connection conn = null;

	@Override
	public Connection getConnection() throws Exception {
		System.out.println("��ʼ�����ݿ�");
		// ��ʼ�����������ռ�
		Context ctx = new InitialContext();
		// ����java:/comp/envΪ�̶�·��
		Context envContext = (Context) ctx.lookup("java:/comp/env");
		// ����jdbc/mysqldsΪ����Դ��JNDI�󶨵�����
		ds = (DataSource) envContext.lookup("jdbc/scau");
		conn = ds.getConnection();
		System.out.println("��ʼ�����ݿ�ɹ�");
		return conn;
	}

	@Override
	public void close() {
		try {
			System.out.println("���ݿ�׼���ر�");
			conn.close();
		} catch (SQLException e) {
			System.out.println("���ݿ�ر�ʧ��");
			e.printStackTrace();
		}
		System.out.println("���ݿ�رճɹ�");
	}

}
