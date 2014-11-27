/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-22
 */
package com.chao.model;

import java.sql.Date;

/**
 * @author �̳���
 * 
 */
public class Message implements IModel {

  private int id;
  private String title;
  private String content;
  private String image;
  private Date pubtime;
  private int category;
  private int replacenum;
  private String userid;

  public Message() {
    System.out.println("-------һ��Message�������-------");
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Date getPubtime() {
    return pubtime;
  }

  public void setPubtime(Date pubtime) {
    this.pubtime = pubtime;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public int getReplacenum() {
    return replacenum;
  }

  public void setReplacenum(int replacenum) {
    this.replacenum = replacenum;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public String getSubContent() {
    return content.substring(0, content.length() <= 20 ? content.length() : 20);
  }
}
