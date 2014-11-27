/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-26
 */
package scau.info.volunteertime.dao.feedback;

import java.util.List;

import scau.info.volunteertime.vo.FeedBack;

/**
 * @author �̳���
 * 
 */
public interface FeedBackDao {

	/**
	 * @param userId
	 * @param content
	 * @return
	 */
	String commitUserFeedBack(String userId, String content);

	public List<FeedBack> getFeedBack();

	/**
	 * @param id
	 * @return
	 */
	String delete(int id);

}
