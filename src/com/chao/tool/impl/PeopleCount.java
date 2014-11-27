/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-24
 */
package com.chao.tool.impl;

/**
 * @author 蔡超敏
 * 
 */
public class PeopleCount {
  private int count = 0;

  public PeopleCount() {
    System.out.println("--一个PeopleCount对象创建---");
  }

  public int getCount() {
    return this.count;
  }

  public void count() {
    this.count++;
  }
}
