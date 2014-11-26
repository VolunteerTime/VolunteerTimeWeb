/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-26
 */
package scau.info.volunteertime.dao.messages;

/**
 * @author �̳���
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
