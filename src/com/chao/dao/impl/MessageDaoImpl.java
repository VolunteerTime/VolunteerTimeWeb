/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-22
 */
package com.chao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.model.IModel;
import com.chao.model.Message;

/**
 * @author 蔡超敏
 * 
 */
public class MessageDaoImpl implements IDao {
  private Connection conn = null;
  private PreparedStatement pstmt = null;

  public MessageDaoImpl(Connection conn) {
    this.conn = conn;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findById(java.lang.Object)
   */
  @Override
  public IModel findById(Object key) throws Exception {
    Message message = null;
    String sql =
        "select title,content,image,pubtime,category,replacenum,userid,id from message where id = ?";
    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, (int) key);
    ResultSet rs = this.pstmt.executeQuery();
    if (rs.next()) {
      message = new Message();
      message.setTitle(rs.getString(1));
      message.setContent(rs.getString(2));
      message.setImage(rs.getString(3));
      message.setPubtime(rs.getDate(4));
      message.setCategory(rs.getInt(5));
      message.setReplacenum(rs.getInt(6));
      message.setUserid(rs.getString(7));
      message.setId(rs.getInt(8));
    }
    this.pstmt.close();
    System.out.println("message-findById-end");
    return message;
  }

  public List<Message> findByCategory(Object key) throws Exception {
    List<Message> messages = new ArrayList<Message>();
    Message message = null;
    String sql =
        "select title,content,image,pubtime,category,replacenum,userid,id from message where category = ?";
    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, (int) key);
    ResultSet rs = this.pstmt.executeQuery();
    while (rs.next()) {
      message = new Message();
      message.setTitle(rs.getString(1));
      message.setContent(rs.getString(2));
      message.setImage(rs.getString(3));
      message.setPubtime(rs.getDate(4));
      message.setCategory(rs.getInt(5));
      message.setReplacenum(rs.getInt(6));
      message.setUserid(rs.getString(7));
      message.setId(rs.getInt(8));
      messages.add(message);
    }
    this.pstmt.close();
    System.out.println("message-findByCategory-end");
    return messages;
  }

  public List<Message> findNews() throws Exception {
    List<Message> messages = new ArrayList<Message>();
    Message message = null;
    String sql =
        "select title,content,image,pubtime,category,replacenum,userid,id from message where category = 5 or category = 6 or category = 7";
    this.pstmt = this.conn.prepareStatement(sql);
    ResultSet rs = this.pstmt.executeQuery();
    while (rs.next()) {
      message = new Message();
      message.setTitle(rs.getString(1));
      message.setContent(rs.getString(2));
      message.setImage(rs.getString(3));
      message.setPubtime(rs.getDate(4));
      message.setCategory(rs.getInt(5));
      message.setReplacenum(rs.getInt(6));
      message.setUserid(rs.getString(7));
      message.setId(rs.getInt(8));
      messages.add(message);
    }
    this.pstmt.close();
    System.out.println("findNews-findNews-end");
    return messages;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findAll(java.lang.Object)
   */
  @Override
  public List<Message> findAll(Object key) throws Exception {
    List<Message> messages = new ArrayList<Message>();
    Message message = null;
    String sql = "select title,content,image,pubtime,category,replacenum,userid,id from message";
    this.pstmt = this.conn.prepareStatement(sql);
    // this.pstmt.setInt(1, (Integer) key);
    ResultSet rs = this.pstmt.executeQuery();
    while (rs.next()) {
      message = new Message();
      message.setTitle(rs.getString(1));
      message.setContent(rs.getString(2));
      message.setImage(rs.getString(3));
      message.setPubtime(rs.getDate(4));
      message.setCategory(rs.getInt(5));
      message.setReplacenum(rs.getInt(6));
      message.setUserid(rs.getString(7));
      message.setId(rs.getInt(8));
      messages.add(message);
    }
    this.pstmt.close();
    System.out.println("message-findAll-end");
    return messages;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#add(com.chao.model.IModel)
   */
  @Override
  public boolean add(IModel obj) throws Exception {
    System.out.println("message-add-init");
    boolean flag = false;
    Message message = (Message) obj;
    String sql =
        "insert into message(title,content,image,pubtime,category,replacenum,userid) values(?,?,?,NOW(),?,?,?)";

    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setString(1, message.getTitle());
    this.pstmt.setString(2, message.getContent());
    this.pstmt.setString(3, message.getImage());
    this.pstmt.setInt(4, message.getCategory());
    this.pstmt.setInt(5, message.getReplacenum());
    this.pstmt.setString(6, message.getUserid());

    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("message-add-end");
    return flag;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#delete(java.lang.Object)
   */
  @Override
  public boolean delete(Object key) throws Exception {
    System.out.println("message-delete-init");
    boolean flag = false;
    String sql = "delete from message where id = ?";

    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, (int) key);
    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("message-delete-end");
    return flag;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#update(com.chao.model.IModel)
   */
  @Override
  public boolean update(IModel obj) throws Exception {
    System.out.println("message-update-init");
    boolean flag = false;
    String sql =
        "update message set title = ? ,content = ? ,image = ? ,pubtime = now() ,category = ? ,replacenum = ? where id = ?";
    Message message = (Message) obj;
    this.pstmt = this.conn.prepareStatement(sql);

    this.pstmt.setString(1, message.getTitle());
    this.pstmt.setString(2, message.getContent());
    this.pstmt.setString(3, message.getImage());

    this.pstmt.setInt(4, message.getCategory());
    this.pstmt.setInt(5, message.getReplacenum());
    this.pstmt.setInt(6, message.getId());

    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("message-update-end");
    return flag;
  }

  /**
   * @param title
   * @param category
   * @return
   */
  public List<Message> findAll(String title, int category) throws Exception {
    List<Message> messages = new ArrayList<Message>();
    Message message = null;
    String sql = "";
    if (title != null && !title.equals("")) {
      if (category == 5) {
        sql =
            "select title,content,image,pubtime,category,replacenum,userid,id from message where (category = 3 or category = 4) and title like ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + title + "%");
      } else {
        sql =
            "select title,content,image,pubtime,category,replacenum,userid,id from message where title like ? and category = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, "%" + title + "%");
        this.pstmt.setInt(2, category);
      }
    } else {
      if (category == 5) {
        sql =
            "select title,content,image,pubtime,category,replacenum,userid,id from message WHERE category = 3 or category = 4";
        this.pstmt = this.conn.prepareStatement(sql);
      } else {
        sql =
            "select title,content,image,pubtime,category,replacenum,userid,id from message where category = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, category);
      }
    }
    System.out.println(sql);
    ResultSet rs = this.pstmt.executeQuery();
    while (rs.next()) {
      message = new Message();
      message.setTitle(rs.getString(1));
      message.setContent(rs.getString(2));
      message.setImage(rs.getString(3));
      message.setPubtime(rs.getDate(4));
      message.setCategory(rs.getInt(5));
      message.setReplacenum(rs.getInt(6));
      message.setUserid(rs.getString(7));
      message.setId(rs.getInt(8));
      messages.add(message);
    }
    this.pstmt.close();
    System.out.println("messages-findAll-end");
    return messages;
  }

}
