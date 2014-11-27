/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-23
 */
package com.chao.model;

/**
 * @author 蔡超敏
 * 
 */
public class Course implements IModel {
  private int id;
  private String courseid;
  private String coursename;
  private String teachername;
  private String addressnumber;
  private int startweek;
  private int endweek;
  private String weekday;
  private int isAll;

  public Course() {
    System.out.println("一个course对象创建");
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourseid() {
    return courseid;
  }

  public void setCourseid(String courseid) {
    this.courseid = courseid;
  }

  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }

  public String getTeachername() {
    return teachername;
  }

  public void setTeachername(String teachername) {
    this.teachername = teachername;
  }

  public String getAddressnumber() {
    return addressnumber;
  }

  public void setAddressnumber(String addressnumber) {
    this.addressnumber = addressnumber;
  }

  public int getStartweek() {
    return startweek;
  }

  public void setStartweek(int startweek) {
    this.startweek = startweek;
  }

  public int getEndweek() {
    return endweek;
  }

  public void setEndweek(int endweek) {
    this.endweek = endweek;
  }

  public String getWeekday() {
    return weekday;
  }

  public void setWeekday(String weekday) {
    this.weekday = weekday;
  }

  public int getIsAll() {
    return isAll;
  }

  public void setIsAll(int isAll) {
    this.isAll = isAll;
  }



}
