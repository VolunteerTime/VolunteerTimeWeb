/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-23
 */
package com.chao.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.dao.impl.CourseDaoImpl;
import com.chao.model.Course;
import com.chao.model.IModel;
import com.chao.tool.factory.DatabaseConnectionFactory;
import com.chao.tool.itool.DatabaseConnection;

/**
 * @author 蔡超敏
 * 
 */
public class CourseDaoProxy implements IDao {
  private DatabaseConnection dbc;
  private IDao dao;

  public CourseDaoProxy() throws Exception {
    this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
    this.dao = new CourseDaoImpl(dbc.getConnection());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findById(java.lang.Object)
   */
  @Override
  public IModel findById(Object key) throws Exception {
    Course course = null;
    try {
      course = (Course) this.dao.findById(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return course;
  }

  public IModel findByKeyId(Object key) throws Exception {
    Course course = null;
    try {
      course = (Course) ((CourseDaoImpl) dao).findByKeyId(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return course;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findAll(java.lang.Object)
   */
  @Override
  public List<Course> findAll(Object key) throws Exception {
    List<Course> courses = new ArrayList<Course>();
    try {
      courses = this.dao.findAll(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return courses;
  }

  public List<Course> findAll() throws Exception {
    List<Course> courses = new ArrayList<Course>();
    try {
      courses = ((CourseDaoImpl) dao).findAll();
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return courses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#add(com.chao.model.IModel)
   */
  @Override
  public boolean add(IModel obj) throws Exception {
    boolean flag = false;
    Course course = (Course) obj;
    try {
      if (this.dao.findById(course.getCourseid()) == null) {
        flag = this.dao.add(obj);
      }
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return flag;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#delete(java.lang.Object)
   */
  @Override
  public boolean delete(Object key) throws Exception {
    try {
      return this.dao.delete(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
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
