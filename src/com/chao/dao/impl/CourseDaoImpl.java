/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-23
 */
package com.chao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.model.Course;
import com.chao.model.IModel;

/**
 * @author 蔡超敏
 * 
 */
public class CourseDaoImpl implements IDao {
  private Connection conn = null;
  private PreparedStatement pstmt = null;

  public CourseDaoImpl(Connection conn) {
    this.conn = conn;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findById(java.lang.Object)
   */
  @Override
  public IModel findById(Object key) throws Exception {
    Course course = null;
    String sql =
        "select id,courseid,coursename,teachername,addressnumber,startweek,endweek,weekday,isAll from course where courseid = ?";
    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setString(1, (String) key);
    ResultSet rs = this.pstmt.executeQuery();
    if (rs.next()) {
      course = new Course();
      course.setId(rs.getInt(1));
      course.setCourseid(rs.getString(2));
      course.setCoursename(rs.getString(3));
      course.setTeachername(rs.getString(4));
      course.setAddressnumber(rs.getString(5));
      course.setStartweek(rs.getInt(6));
      course.setEndweek(rs.getInt(7));
      course.setWeekday(rs.getString(8));
      course.setIsAll(rs.getInt(9));
    }
    this.pstmt.close();
    System.out.println("course-findById-end");
    return course;
  }

  public IModel findByKeyId(Object key) throws Exception {
    Course course = null;
    String sql =
        "select id,courseid,coursename,teachername,addressnumber,startweek,endweek,weekday,isAll from course where id = ?";
    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, (int) key);
    ResultSet rs = this.pstmt.executeQuery();
    if (rs.next()) {
      course = new Course();
      course.setId(rs.getInt(1));
      course.setCourseid(rs.getString(2));
      course.setCoursename(rs.getString(3));
      course.setTeachername(rs.getString(4));
      course.setAddressnumber(rs.getString(5));
      course.setStartweek(rs.getInt(6));
      course.setEndweek(rs.getInt(7));
      course.setWeekday(rs.getString(8));
      course.setIsAll(rs.getInt(9));
    }
    this.pstmt.close();
    System.out.println("course-findById-end");
    return course;
  }

  public List<Course> findAll() throws Exception {
    List<Course> courses = new ArrayList<Course>();
    Course course = null;
    String sql =
        "select id,courseid,coursename,teachername,addressnumber,startweek,endweek,weekday,isAll from course";
    this.pstmt = this.conn.prepareStatement(sql);
    ResultSet rs = this.pstmt.executeQuery();
    while (rs.next()) {
      course = new Course();
      course.setId(rs.getInt(1));
      course.setCourseid(rs.getString(2));
      course.setCoursename(rs.getString(3));
      course.setTeachername(rs.getString(4));
      course.setAddressnumber(rs.getString(5));
      course.setStartweek(rs.getInt(6));
      course.setEndweek(rs.getInt(7));
      course.setWeekday(rs.getString(8));
      course.setIsAll(rs.getInt(9));
      courses.add(course);
    }
    this.pstmt.close();
    System.out.println("Course-findAll-end");
    return courses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findAll(java.lang.Object)
   */
  @Override
  public List<Course> findAll(Object key) throws Exception {
    System.out.println("courses-findAll-start");
    List<Course> courses = new ArrayList<Course>();
    Course course = null;
    String sql =
        "select course.id,usercourse.courseid,coursename,teachername,addressnumber,startweek,endweek,weekday,isAll from course,usercourse where course.courseid=usercourse.courseid and usercourse.userid = ?";
    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setString(1, (String) key);
    ResultSet rs = this.pstmt.executeQuery();
    while (rs.next()) {
      course = new Course();
      course.setId(rs.getInt(1));
      course.setCourseid(rs.getString(2));
      course.setCoursename(rs.getString(3));
      course.setTeachername(rs.getString(4));
      course.setAddressnumber(rs.getString(5));
      course.setStartweek(rs.getInt(6));
      course.setEndweek(rs.getInt(7));
      course.setWeekday(rs.getString(8));
      course.setIsAll(rs.getInt(9));
      courses.add(course);
    }
    this.pstmt.close();
    System.out.println("courses-findAll-end");
    return courses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#add(com.chao.model.IModel)
   */
  @Override
  public boolean add(IModel obj) throws Exception {
    System.out.println("course-add-init");
    boolean flag = false;
    Course course = (Course) obj;
    String sql =
        "insert into course(courseid,coursename,teachername,addressnumber,startweek,endweek,weekday,isAll) values(?,?,?,?,?,?,?,?)";

    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setString(1, course.getCourseid());
    this.pstmt.setString(2, course.getCoursename());
    this.pstmt.setString(3, course.getTeachername());
    this.pstmt.setString(4, course.getAddressnumber());
    this.pstmt.setInt(5, course.getStartweek());
    this.pstmt.setInt(6, course.getEndweek());
    this.pstmt.setString(7, course.getWeekday());
    this.pstmt.setInt(8, course.getIsAll());

    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("course-add-end");
    return flag;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#delete(java.lang.Object)
   */
  @Override
  public boolean delete(Object key) throws Exception {
    System.out.println("course-delete-init");
    boolean flag = false;
    String sql = "delete from course where id = ?";

    this.pstmt = this.conn.prepareStatement(sql);
    this.pstmt.setInt(1, (int) key);
    if (this.pstmt.executeUpdate() > 0) {
      flag = true;
    }
    this.pstmt.close();
    System.out.println("course-delete-end");
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
