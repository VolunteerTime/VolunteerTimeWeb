/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-25
 */
package scau.info.volunteertime.dao.userinfo;

/**
 * @author �̳���
 * 
 */
public interface UserInfoDao {

	/**
	 * @param userId
	 * @param password
	 * @return
	 */
	String checkUserLogin(String userId, String password);

	/**
	 * @param userId
	 * @param password
	 * @return
	 */
	String checkUserRegister(String userId, String password);

	/**
	 * @param userId
	 * @param password
	 * @param name
	 * @param sex
	 * @param className
	 * @param longPhone
	 * @param briefPhone
	 * @param qq
	 * @param wechant
	 * @return
	 */
	String commitUserData(String userId, String password, String name,
			String sex, String className, String longPhone, String briefPhone,
			String qq, String wechant);

}
