/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-6-21
 */
package com.chao.dao.factory;

import com.chao.dao.proxy.CourseDaoProxy;
import com.chao.dao.proxy.MessageDaoProxy;
import com.chao.dao.proxy.UserCourseDaoProxy;
import com.chao.dao.proxy.UserDaoProxy;

/**
 * @author �̳���
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
