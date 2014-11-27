/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-22
 */
package com.chao.tool.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 蔡超敏
 * 
 */
public class IPTimeStamp {
  private SimpleDateFormat sdf = null;
  private String ip = null;

  public IPTimeStamp() {

  }

  public IPTimeStamp(String ip) {
    this.ip = ip;
  }

  public String getIPTimeRand() {
    StringBuffer buf = new StringBuffer();
    if (this.ip != null) {
      String[] s = this.ip.split("\\.");
      for (int i = 0; i < s.length; i++) {
        buf.append(this.addZero(s[i], 3));
      }
    }
    buf.append(this.getTimeStamp());
    Random r = new Random();
    for (int i = 0; i < 3; i++) {
      buf.append(r.nextInt(10));
    }
    return buf.toString();
  }

  /**
   * @param string
   * @param i
   * @return String
   */
  private String addZero(String string, int i) {
    StringBuffer s = new StringBuffer();
    s.append(string);
    while (s.length() < i) {
      s.insert(0, "0");
    }
    return s.toString();
  }

  public String getDate() {
    this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return this.sdf.format(new Date());
  }

  public String getTimeStamp() {
    this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    return this.sdf.format(new Date());
  }


}
