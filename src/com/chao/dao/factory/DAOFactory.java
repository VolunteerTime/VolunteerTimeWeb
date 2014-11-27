/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-6-21
 */
package com.chao.dao.factory;

import com.chao.dao.proxy.CourseDaoProxy;
import com.chao.dao.proxy.MessageDaoProxy;
import com.chao.dao.proxy.UserCourseDaoProxy;
import com.chao.dao.proxy.UserDaoProxy;

/**
 * @author 蔡超敏
 * 
 */
public class DAOFactory {
  public static UserDaoProxy getUserDaoInstance() throws Exception {
    return new UserDaoProxy();
  }

  public static MessageDaoProxy getMessageDaoInstance() throws Exception {
    return new MessageDaoProxy();
  }

  public static CourseDaoProxy getCourseDaoInstance() throws Exception {
    return new CourseDaoProxy();
  }

  public static UserCourseDaoProxy getUserCourseDaoInstance() throws Exception {
    return new UserCourseDaoProxy();
  }

}
