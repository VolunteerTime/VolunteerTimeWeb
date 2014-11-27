/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-24
 */
package com.chao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.model.Course;
import com.chao.model.IModel;
import com.chao.model.User;
import com.chao.model.UserCourse;

/**
 * @author 蔡超敏
 * 
 */
public class UserCourseDaoImpl implements IDao {
  private Connection conn = null;
  private PreparedStatement pstmt = null;

  public UserCourseDaoImpl(Connection conn) {
    this.conn = conn;
  }

  public UserCourse findByUserAndCourse(String userid, String courseid) throws Exception {
    UserCourse userCourse = null;
    String sql = "select id,courseid,userid from usercourse where userid = ? and courseid = ?";
    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setString(1, userid);
    this.pstmt.setString(2, courseid);
    ResultSet rs = this.pstmt.executeQuery();
    if (rs.next()) {
      userCourse = new UserCourse();
      userCourse.setId(rs.getInt(1));
      userCourse.setCourseid(rs.getString(2));
      userCourse.setUserid(rs.getString(3));
    }
    this.pstmt.close();
    System.out.println("userCourse-findByUserAndCourse-end");
    return userCourse;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findById(java.lang.Object)
   */
  @Override
  public IModel findById(Object key) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findAll(java.lang.Object)
   */
  @Override
  public List findAll(Object key) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#add(com.chao.model.IModel)
   */
  @Override
  public boolean add(IModel obj) throws Exception {
    System.out.println("UserCourse-add-init");
    boolean flag = false;
    UserCourse userCourse = (UserCourse) obj;
    String sql = "insert into usercourse(id,courseid,userid) values(?,?,?)";

    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, userCourse.getId());
    this.pstmt.setString(2, userCourse.getCourseid());
    this.pstmt.setString(3, userCourse.getUserid());
    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("user-add-end");
    return flag;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#delete(java.lang.Object)
   */
  @Override
  public boolean delete(Object key) throws Exception {
    System.out.println("usercourse-delete-init");
    boolean flag = false;
    String sql = "delete from usercourse where id = ?";

    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, (int) key);
    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("usercourse-delete-end");
    return flag;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#update(com.chao.model.IModel)
   */
  @Override
  public boolean update(IModel obj) throws Exception {
    // TODO Auto-generated method stub
    return false;
  }

}
