package scau.info.volunteertime.servlet.votescenter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.chao.model.User;

import scau.info.volunteertime.dao.messages.MessagesDao;
import scau.info.volunteertime.dao.messages.MessagesDaoImpl;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDaoImpl;
import scau.info.volunteertime.dao.votesCenter.VotesDao;
import scau.info.volunteertime.dao.votesCenter.VotesDaoImpl;
import scau.info.volunteertime.vo.Vote;

/**
 * Servlet implementation class VolunteerTimeVotesServlet
 */
@WebServlet(description = "VolunteerTimeVotesServlet", urlPatterns = { "/VolunteerTimeVotesServlet" })
public class VolunteerTimeVotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(VolunteerTimeVotesServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeVotesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("action_type");
		if (actionType != null) {
			switch (Integer.parseInt(actionType.trim())) {
			case 0:
				getNewData(request, response);
				break;
			case 1:
				commitData(request, response);
				break;
			case 2:
				addVote(request, response);
				break;
			case 3:
				deleteVote(request, response);
				break;

			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void deleteVote(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter("id");

		int id = 0;
		if (idStr != null) {
			id = Integer.parseInt(idStr);
		}

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		VotesDao dao = new VotesDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.delete( id);

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void addVote(HttpServletRequest request,
			HttpServletResponse response) {
		String title = request.getParameter("inputpublishMessageTitle");
		String typeStr = request.getParameter("publishVoteType");
		String[] choices = request
				.getParameterValues("inputpublishMessageChoice");

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		System.out.println("title = " + title + "��typeStr��= " + typeStr
				+ " choices .szie = " + choices.length);

		int type = Integer.parseInt(typeStr);

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		VotesDao dao = new VotesDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			User user = (User) request.getSession().getAttribute("userinfo");
			String userid = "";
			if (user == null)
				userid = "admin";
			else
				userid = user.getUserid();

			if (choices != null && choices.length != 0)
				rsStr = dao.checkDate(userid, title, type, choices);

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");

			response.sendRedirect("/VolunteerTimeWeb/manageVotes.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void commitData(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String votes = request.getParameter("votes");
		String idStr = request.getParameter("id");

		int id = 0;
		if (idStr != null) {
			id = Integer.parseInt(idStr);
		}

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		VotesDao dao = new VotesDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.updateChoice(userId, id, votes);

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void getNewData(HttpServletRequest request,
			HttpServletResponse response) {

		// ����������ݵ��ĵ����ͺͱ���
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************�ɹ�չʾ���ݲ�ѯ****************");
		log.debug("��ʼ��ѯ������������");
		System.out.println("****************�ɹ�չʾ���ݲ�ѯ****************");
		System.out.println("��ʼ��ѯ������������");

		String rsStr = "";

		// **********************���ݵ�ǰҳ����ҳ���С�����ݿ��л�ȡ�����Ϣ**********************
		// ����Dao�������ڴ����ݿ��ѯ����

		VotesDao dao = new VotesDaoImpl();

		try {
			// ��ȡϵͳ��ǰʱ�䣬ͳ�� ����ʱ��
			long startTime = System.currentTimeMillis();

			rsStr = dao.getNewVotes();

			// ��ȡϵͳ��ǰʱ�䣬ͳ�ƻ���ʱ��
			long endTime = System.currentTimeMillis();
			log.debug("��ѯ����ʱ�䣺" + (endTime - startTime) + "����");
			log.debug("��ѯ�����" + rsStr);

			// ��ȡhttp���������
			PrintWriter writer = response.getWriter();
			log.debug("��ʼ���紫�䡤����������");
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// ��ȡϵͳ��ǰʱ�䣬����ͳ�������ѵ�ʱ��
			endTime = System.currentTimeMillis();
			log.debug("���紫������ʱ�䣺" + (endTime - startTime) + "����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
