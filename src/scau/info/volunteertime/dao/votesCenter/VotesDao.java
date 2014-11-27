/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-26
 */
package scau.info.volunteertime.dao.votesCenter;

import java.util.List;

import scau.info.volunteertime.vo.ActivityData;
import scau.info.volunteertime.vo.Vote;

/**
 * @author �̳���
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
