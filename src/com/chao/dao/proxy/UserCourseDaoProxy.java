/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-24
 */
package com.chao.dao.proxy;

import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.dao.impl.UserCourseDaoImpl;
import com.chao.model.IModel;
import com.chao.model.UserCourse;
import com.chao.tool.factory.DatabaseConnectionFactory;
import com.chao.tool.itool.DatabaseConnection;

/**
 * @author �̳���
 * 
 */
public class UserCourseDaoProxy implements IDao {
  private DatabaseConnection dbc;
  private IDao dao;

  public UserCourseDaoProxy() throws Exception {
    this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
    this.dao = new UserCourseDaoImpl(dbc.getConnection());
  }

  public UserCourse findByUserAndCourse(String userid, String courseid) throws Exception {
    UserCourse userCourse = null;
    try {
      userCourse = (UserCourse) ((UserCourseDaoImpl) dao).findByUserAndCourse(userid, courseid);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
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
    boolean flag = false;
    try {
      flag = this.dao.add(obj);
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
