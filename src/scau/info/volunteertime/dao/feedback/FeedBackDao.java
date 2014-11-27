/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package scau.info.volunteertime.dao.feedback;

import java.util.List;

import scau.info.volunteertime.vo.FeedBack;

/**
 * @author 蔡超敏
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
