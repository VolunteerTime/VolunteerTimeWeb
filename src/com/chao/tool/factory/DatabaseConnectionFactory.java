/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-21
 */
package com.chao.tool.factory;

import com.chao.tool.impl.MySQLDatabaseConnection;
import com.chao.tool.itool.DatabaseConnection;

/**
 * @author �̳���
 * 
 */
public class DatabaseConnectionFactory {
  public static DatabaseConnection getDatabaseConnection() {
    return new MySQLDatabaseConnection();
  }

}
