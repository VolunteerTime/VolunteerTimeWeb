/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-21
 */
package com.chao.tool.itool;

import java.sql.Connection;

/**
 * @author �̳���
 * 
 */
public interface DatabaseConnection {
  public Connection getConnection() throws Exception;

  public void close();
}