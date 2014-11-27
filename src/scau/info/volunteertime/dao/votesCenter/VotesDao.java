/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package scau.info.volunteertime.dao.votesCenter;

import java.util.List;

import scau.info.volunteertime.vo.ActivityData;
import scau.info.volunteertime.vo.Vote;

/**
 * @author 蔡超敏
 * 
 */
public interface VotesDao {

	/**
	 * @param userId
	 * @return
	 */
	String getNewVotes();

	/**
	 * @param userId
	 * @param id
	 * @param votes
	 * @return
	 */
	String updateChoice(String userId, int id, String votes);

	public abstract List<Vote> getAll(String keyword);

	/**
	 * @param userid
	 * @param title
	 * @param type
	 * @param choices
	 * @return
	 */
	String checkDate(String userid, String title, int type, String[] choices);

	/**
	 * @param id
	 * @return
	 */
	String delete(int id);

}
