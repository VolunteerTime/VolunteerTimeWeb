/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.dao.proxy;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.dao.impl.UserDaoImpl;
import com.chao.model.IModel;
import com.chao.model.User;
import com.chao.tool.factory.DatabaseConnectionFactory;
import com.chao.tool.itool.DatabaseConnection;

/**
 * @author 蔡超敏
 * 
 */
public class UserDaoProxy implements IDao {
  private DatabaseConnection dbc;
  private IDao dao;

  public UserDaoProxy() throws Exception {
    this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
    this.dao = new UserDaoImpl(dbc.getConnection());
  }

  public int getUser(int type) throws Exception {
    int num;
    try {
      num = ((UserDaoImpl) dao).getUser(type);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return num;
  }

  public boolean updateCount(int count) throws Exception {
    try {
      return ((UserDaoImpl) dao).updateCount(count);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
  }

  public int getCount() throws Exception {
    int num;
    try {
      num = ((UserDaoImpl) dao).getCount();
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return num;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findById(java.lang.Object)
   */
  @Override
  public IModel findById(Object key) throws Exception {
    User user = null;
    try {
      user = (User) this.dao.findById(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return user;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findAll(java.lang.Object)
   */
  @Override
  public List<User> findAll(Object key) throws Exception {
    List<User> users = new ArrayList<User>();
    try {
      users = this.dao.findAll(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return users;
  }

  public List<User> findAll(String userid, int authority) throws Exception {
    List<User> users = new ArrayList<User>();
    try {
      System.out.println("userid:authority=" + userid + ":" + authority);
      users = ((UserDaoImpl) dao).findAll(userid, authority);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return users;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#add(com.chao.model.IModel)
   */
  @Override
  public boolean add(IModel obj) throws Exception {
    boolean flag = false;
    User user = (User) obj;
    try {
      if (this.dao.findById(user.getUserid()) == null) {
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
    try {
      return this.dao.update(obj);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
  }

}
