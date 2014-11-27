/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.dao.idao;

import java.util.List;

import com.chao.model.IModel;

/**
 * @author 蔡超敏
 * 
 */
public interface IDao {
  public IModel findById(Object key) throws Exception;

  public List findAll(Object key) throws Exception;

  public boolean add(IModel obj) throws Exception;

  public boolean delete(Object key) throws Exception;

  public boolean update(IModel obj) throws Exception;

}
