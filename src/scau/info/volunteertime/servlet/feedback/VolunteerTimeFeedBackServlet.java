package scau.info.volunteertime.servlet.feedback;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.dao.feedback.FeedBackDao;
import scau.info.volunteertime.dao.feedback.FeedBackDaoImpl;

/**
 * Servlet implementation class VolunteerTimeFeedBackServlet
 */
@WebServlet(description = "VolunteerTimeFeedBackServlet", urlPatterns = { "/VolunteerTimeFeedBackServlet" })
public class VolunteerTimeFeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(VolunteerTimeFeedBackServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeFeedBackServlet() {
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
				getFeedBack(request, response);
				break;
			case 1:
				deleteFeedBack(request, response);
				break;

			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void deleteFeedBack(HttpServletRequest request,
			HttpServletResponse response) {
		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");
		String idStr = request.getParameter("id");
		int id = 0;
		if (idStr != null)
			id = Integer.parseInt(idStr);

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		FeedBackDao dao = new FeedBackDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.delete(id);

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
	private void getFeedBack(HttpServletRequest request,
			HttpServletResponse response) {

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");
		String userId = request.getParameter("userId");
		String content = request.getParameter("content");


		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		FeedBackDao dao = new FeedBackDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.commitUserFeedBack(userId, content);

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
