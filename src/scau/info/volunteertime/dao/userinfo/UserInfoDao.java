/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-25
 */
package scau.info.volunteertime.dao.userinfo;

/**
 * @author 蔡超敏
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

	/**
	 * @param userId
	 * @return
	 */
	String getUserInfo(String userId);

	/**
	 * @param userId
	 * @param key
	 * @param value
	 * @return
	 */
	String commitUserInfo(String userId, String key, String value);

	/**
	 * @param userId
	 * @param orignPassword
	 * @param newPassword
	 * @return
	 */
	String changePassword(String userId, String orignPassword,
			String newPassword);

}
