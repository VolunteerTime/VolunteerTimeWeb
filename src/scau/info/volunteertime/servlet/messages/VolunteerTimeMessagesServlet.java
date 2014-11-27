package scau.info.volunteertime.servlet.messages;

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

import scau.info.volunteertime.dao.messages.MessagesDao;
import scau.info.volunteertime.dao.messages.MessagesDaoImpl;
import scau.info.volunteertime.dao.userinfo.UserInfoDao;
import scau.info.volunteertime.dao.userinfo.UserInfoDaoImpl;

/**
 * Servlet implementation class VolunteerTimeMessagesServlet
 */
@WebServlet(description = "VolunteerTimeMessagesServlet", urlPatterns = { "/VolunteerTimeMessagesServlet" })
public class VolunteerTimeMessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(VolunteerTimeMessagesServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeMessagesServlet() {
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
				saveMessages(request, response);
				break;
			case 1:
				getNewMessages(request, response);
				break;
			case 2:
				UpdateRead(request, response);
				break;
			case 3:
				getNewMessage(request, response);
				break;
			case 4:
				UpdateSent(request, response);
				break;
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void UpdateSent(HttpServletRequest request,
			HttpServletResponse response) {
		String value = request.getParameter("id");
		int id = 0;

		if (value != null) {
			id = Integer.parseInt(value);
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

		MessagesDao dao = new MessagesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.updateSent(id);
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
	private void getNewMessage(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");

		String password = request.getParameter("password");

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		UserInfoDao daoUser = new UserInfoDaoImpl();
		MessagesDao dao = new MessagesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			String checkUser = daoUser.checkUserLogin(userId, password);
			if (checkUser.trim().equals("1"))
				rsStr = dao.getNewMessage(userId);
			else
				rsStr = "";

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
	private void UpdateRead(HttpServletRequest request,
			HttpServletResponse response) {
		String value = request.getParameter("id");
		int id = 0;

		if (value != null) {
			id = Integer.parseInt(value);
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

		MessagesDao dao = new MessagesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.updateRead(id);
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
	private void getNewMessages(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		MessagesDao dao = new MessagesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.getNewMessages(userId);

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
	 * 
	 * @param request
	 * @param response
	 */
	private void saveMessages(HttpServletRequest request,
			HttpServletResponse response) {
		
		String principalId = request.getParameter("principalId");
		String participators = request.getParameter("participators");
		String message = request.getParameter("message");
		String title = request.getParameter("title");


		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		MessagesDao dao = new MessagesDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao
					.saveMessages(principalId, participators, message, title);

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
