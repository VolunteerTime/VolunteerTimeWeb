/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.tool.factory;

import com.chao.tool.impl.MySQLDatabaseConnection;
import com.chao.tool.itool.DatabaseConnection;

/**
 * @author 蔡超敏
 * 
 */
public class DatabaseConnectionFactory {
  public static DatabaseConnection getDatabaseConnection() {
    return new MySQLDatabaseConnection();
  }

}
