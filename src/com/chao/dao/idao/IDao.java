/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-21
 */
package com.chao.dao.idao;

import java.util.List;

import com.chao.model.IModel;

/**
 * @author �̳���
 * 
 */
public interface IDao {
  public IModel findById(Object key) throws Exception;

  public List findAll(Object key) throws Exception;

  public boolean add(IModel obj) throws Exception;

  public boolean delete(Object key) throws Exception;

  public boolean update(IModel obj) throws Exception;

}
