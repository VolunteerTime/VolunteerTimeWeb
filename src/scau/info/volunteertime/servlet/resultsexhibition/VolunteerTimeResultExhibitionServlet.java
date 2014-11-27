/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-8-16
 */
package scau.info.volunteertime.servlet.resultsexhibition;

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

import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDaoImpl;
import scau.info.volunteertime.dao.userinfo.UserInfoDao;
import scau.info.volunteertime.dao.userinfo.UserInfoDaoImpl;
import scau.info.volunteertime.vo.Result;

import com.chao.model.User;
import com.chao.tool.Util;
import com.chao.tool.impl.IPTimeStamp;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class VolunteerTimeResultExhibitionServlet
 */
/**
 * @author 蔡超敏
 * 
 */
@WebServlet(description = "VolunteerTimeResultExhibitionServlet", urlPatterns = { "/VolunteerTimeResultExhibitionServlet" })
public class VolunteerTimeResultExhibitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(VolunteerTimeResultExhibitionServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VolunteerTimeResultExhibitionServlet() {
		super();
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
				sendNewData(request, response);
				break;
			case 1:
				sendDowmData(request, response);
				break;
			case 2:
				sendDropDownData(request, response);
				break;
			case 3:
				updateData(request, response);
				break;
			case 4:
				updateReadNumData(request, response);
				break;
			case 5:
				addResult(request, response);
				break;
			case 6:
				deleteResult(request, response);
				break;
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void deleteResult(HttpServletRequest request,
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

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

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
	private void addResult(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("doPublishMessage-init");
		PrintWriter out = null;
		try {
//			request.setCharacterEncoding("GBK");

			out = response.getWriter();
			SmartUpload smartUpload = new SmartUpload();

			smartUpload.initialize(this.getServletConfig(), request, response);
			// smartUpload.setMaxFileSize(5000000 * 5000000 * 5000000);
			// smartUpload.setAllowedFilesList("JPG,png,jpg,gif,GIF,bmp");

			// smartUpload.setAllowedFilesList("");
			smartUpload.upload();

			File file = smartUpload.getFiles().getFile(0);

			System.out.println(file.getFileName() + ":" + file.getFileExt());
			if (file.getFileExt().matches("JPG")
					|| file.getFileExt().matches("jpg")
					|| file.getFileExt().matches("gif")
					|| file.getFileExt().matches("GIF")) {
				System.out.println(file.getFileName());
				IPTimeStamp iTimeStamp = new IPTimeStamp(
						request.getRemoteAddr());
				String ext = file.getFileExt();
				String fileName = iTimeStamp.getIPTimeRand() + "." + ext;
				String path = getServletContext().getRealPath("/") + "images"
						+ java.io.File.separator;
				String url = path + fileName;
				String saveurl = "images/" + fileName;
				java.io.File pathFile = new java.io.File(path);// 创建文件夹
				pathFile.mkdirs();

				System.out.println("url = " + url);
				// String path =
				// "D:\\Program Files (x86)\\Java\\ECLIPSE_HOME\\android workspace2\\";
				file.saveAs(url);
				// file.saveAs(path + "images" + java.io.File.separator +
				// fileName);

				// 其他消息
				String title = smartUpload.getRequest().getParameter(
						"inputpublishMessageTitle");
				String content = smartUpload.getRequest().getParameter(
						"textfieldPublishMessage");

				System.out.println("title+userid= " + ":" + title
						+ " saveurl = " + saveurl);
				title = new String(title.trim().getBytes(), "utf-8");
				content = new String(content.trim().getBytes(), "utf-8");

				// title = new String(title.getBytes("GBK"), "utf-8");
				// content = new String(content.getBytes("GBK"), "utf-8");

				User user = (User) request.getSession()
						.getAttribute("userinfo");
				String userid;
				if (user == null)
					userid = "admin";
				else
					userid = user.getUserid();
				System.out.println("title+userid= " + userid + ":" + title
						+ " saveurl = " + saveurl);

				Result result = new Result();

				result.setTitle(title);
				result.setContent(content);
				result.setEditor(userid);

				result.setImage(saveurl);

				ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();
				dao.add(result);

				out.print("alert(\"ok\")");
			}

			response.sendRedirect("/VolunteerTimeWeb/manageResults.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("alert(\"no\")");
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void updateReadNumData(HttpServletRequest request,
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

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			rsStr = dao.updateReadNum(id);
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
	private void updateData(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************成果展示数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************成果展示数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			if (ids != null)
				rsStr = dao.checkDate(ids);

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
	private void sendDropDownData(HttpServletRequest request,
			HttpServletResponse response) {
		int currentPageSize = 0;
		long time = 0;
		String currentPageNumberParam = request.getParameter("currentPageSize");
		String fTime = request.getParameter("firstTime");
		if (currentPageNumberParam != null) {
			currentPageSize = Integer.parseInt(currentPageNumberParam);
		}

		if (fTime != null) {
			time = Long.parseLong(fTime);
			System.out.println("time = " + time);
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

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			if (currentPageSize != 0)
				rsStr = dao.getUp(time, currentPageSize);
			else
				rsStr = dao.getUp(time, 5);

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
	private void sendDowmData(HttpServletRequest request,
			HttpServletResponse response) {
		int currentPageSize = 0;
		long time = 0;
		String currentPageNumberParam = request.getParameter("currentPageSize");
		String eTime = request.getParameter("endTime");
		if (currentPageNumberParam != null) {
			currentPageSize = Integer.parseInt(currentPageNumberParam);
		}

		if (eTime != null) {
			time = Long.parseLong(eTime);
			System.out.println("time = " + time);
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

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			if (currentPageSize != 0)
				rsStr = dao.getDown(time, currentPageSize);
			else
				rsStr = dao.getDown(time, 5);

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

	private void sendNewData(HttpServletRequest request,
			HttpServletResponse response) {
		int currentPageSize = 0;
		String currentPageNumberParam = request.getParameter("currentPageSize");
		if (currentPageNumberParam != null) {
			currentPageSize = Integer.parseInt(currentPageNumberParam);
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

		ResultsExhibitionDao dao = new ResultsExhibitionDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();

			if (currentPageSize != 0)
				rsStr = dao.get(currentPageSize);
			else
				rsStr = dao.get(5);

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
