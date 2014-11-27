/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-21
 */
package com.chao.tool.impl;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

import com.chao.tool.itool.DatabaseConnection;

/**
 * @author �̳���
 * 
 */
public class MySQLDatabaseConnection implements DatabaseConnection {
  private DataSource ds = null;
  private Connection conn = null;


  /*
   * (non-Javadoc)
   * 
   * @see com.chao.tool.DatabaseConnection#getConnection()
   */
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

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.tool.DatabaseConnection#close()
   */
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
