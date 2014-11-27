/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-22
 */
package com.chao.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.chao.dao.idao.IDao;
import com.chao.dao.impl.MessageDaoImpl;
import com.chao.model.IModel;
import com.chao.model.Message;
import com.chao.tool.factory.DatabaseConnectionFactory;
import com.chao.tool.itool.DatabaseConnection;

/**
 * @author 蔡超敏
 * 
 */
public class MessageDaoProxy implements IDao {
  private DatabaseConnection dbc;
  private IDao dao;

  public MessageDaoProxy() throws Exception {
    this.dbc = DatabaseConnectionFactory.getDatabaseConnection();
    this.dao = new MessageDaoImpl(dbc.getConnection());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findById(java.lang.Object)
   */
  @Override
  public IModel findById(Object key) throws Exception {
    Message message = null;
    try {
      message = (Message) this.dao.findById(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return message;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#findAll(java.lang.Object)
   */
  @Override
  public List<Message> findAll(Object key) throws Exception {
    List<Message> messages = new ArrayList<Message>();
    try {
      messages = this.dao.findAll(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return messages;
  }

  public List<Message> findAll(String title, int category) throws Exception {
    List<Message> messages = new ArrayList<Message>();
    try {
      System.out.println("title=" + title + ":" + category);
      messages = ((MessageDaoImpl) dao).findAll(title, category);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return messages;
  }

  public List<Message> findNews() throws Exception {
    List<Message> messages = new ArrayList<Message>();
    try {
      messages = ((MessageDaoImpl) dao).findNews();
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return messages;
  }

  public List<Message> findByCategory(Object key) throws Exception {
    List<Message> messages = new ArrayList<Message>();
    try {
      messages = ((MessageDaoImpl) dao).findByCategory(key);
    } catch (Exception ex) {
      throw ex;
    } finally {
      this.dbc.close();
    }
    return messages;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.chao.dao.idao.IDao#add(com.chao.model.IModel)
   */
  @Override
  public boolean add(IModel obj) throws Exception {
    boolean flag = false;
    Message message = (Message) obj;
    try {
      if (message.getId() == 0 || this.dao.findById(message.getId()) == null) {
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
