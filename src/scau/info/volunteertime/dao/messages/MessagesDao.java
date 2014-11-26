/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package scau.info.volunteertime.dao.messages;

/**
 * @author 蔡超敏
 *
 */
public interface MessagesDao {

	/**
	 * @param principalId
	 * @param participators
	 * @param message
	 * @return
	 */
	String saveMessages(String principalId, String participators, String message,String title);

	/**
	 * @param userId
	 * @return
	 */
	String getNewMessage(String userId);

	/**
	 * @param id
	 * @return
	 */
	String updateRead(int id);

	/**
	 * @param userId
	 * @return
	 */
	String getNewMessages(String userId);

}
