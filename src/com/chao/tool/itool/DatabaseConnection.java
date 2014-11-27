/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.tool.itool;

import java.sql.Connection;

/**
 * @author 蔡超敏
 * 
 */
public interface DatabaseConnection {
  public Connection getConnection() throws Exception;

  public void close();
}
