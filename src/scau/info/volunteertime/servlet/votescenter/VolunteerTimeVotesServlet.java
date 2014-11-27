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

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		VotesDao dao = new VotesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.delete( id);

			// 获取系统当前时间，统计花费时间
			long endTime = System.currentTimeMillis();
			log.debug("查询所花时间：" + (endTime - startTime) + "毫秒");
			log.debug("查询结果：" + rsStr);

			// 获取http输出流对象
			PrintWriter writer = response.getWriter();
			log.debug("开始网络传输······");
			// 获取系统当前时间，用于统计所花费的时间
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// 获取系统当前时间，用于统计所花费的时间
			endTime = System.currentTimeMillis();
			log.debug("网络传输所花时间：" + (endTime - startTime) + "毫秒");
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

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		System.out.println("title = " + title + "　typeStr　= " + typeStr
				+ " choices .szie = " + choices.length);

		int type = Integer.parseInt(typeStr);

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		VotesDao dao = new VotesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			User user = (User) request.getSession().getAttribute("userinfo");
			String userid = "";
			if (user == null)
				userid = "admin";
			else
				userid = user.getUserid();

			if (choices != null && choices.length != 0)
				rsStr = dao.checkDate(userid, title, type, choices);

			// 获取系统当前时间，统计花费时间
			long endTime = System.currentTimeMillis();
			log.debug("查询所花时间：" + (endTime - startTime) + "毫秒");
			log.debug("查询结果：" + rsStr);

			// 获取http输出流对象
			PrintWriter writer = response.getWriter();
			log.debug("开始网络传输······");
			// 获取系统当前时间，用于统计所花费的时间
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// 获取系统当前时间，用于统计所花费的时间
			endTime = System.currentTimeMillis();
			log.debug("网络传输所花时间：" + (endTime - startTime) + "毫秒");

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

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		VotesDao dao = new VotesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.updateChoice(userId, id, votes);

			// 获取系统当前时间，统计花费时间
			long endTime = System.currentTimeMillis();
			log.debug("查询所花时间：" + (endTime - startTime) + "毫秒");
			log.debug("查询结果：" + rsStr);

			// 获取http输出流对象
			PrintWriter writer = response.getWriter();
			log.debug("开始网络传输······");
			// 获取系统当前时间，用于统计所花费的时间
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// 获取系统当前时间，用于统计所花费的时间
			endTime = System.currentTimeMillis();
			log.debug("网络传输所花时间：" + (endTime - startTime) + "毫秒");
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

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		VotesDao dao = new VotesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.getNewVotes();

			// 获取系统当前时间，统计花费时间
			long endTime = System.currentTimeMillis();
			log.debug("查询所花时间：" + (endTime - startTime) + "毫秒");
			log.debug("查询结果：" + rsStr);

			// 获取http输出流对象
			PrintWriter writer = response.getWriter();
			log.debug("开始网络传输······");
			// 获取系统当前时间，用于统计所花费的时间
			startTime = System.currentTimeMillis();
			writer.write(rsStr);
			// 获取系统当前时间，用于统计所花费的时间
			endTime = System.currentTimeMillis();
			log.debug("网络传输所花时间：" + (endTime - startTime) + "毫秒");
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
