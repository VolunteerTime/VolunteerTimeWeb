/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014年8月16日
 */
package scau.info.volunteertime.dao.votesCenter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDao;
import scau.info.volunteertime.dao.resultsexhibition.ResultsExhibitionDaoImpl;
import scau.info.volunteertime.servlet.resultsexhibition.VolunteerTimeResultExhibitionServlet;

/**
 * @author 蔡超敏
 *
 */
@WebServlet(description = "VolunteerTimeVoteCenterServlet", urlPatterns = { "/VolunteerTimeVoteCenterServlet" })
public class VolunteerTimeVoteCenterServlet extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	private static final Log log = LogFactory
			.getLog(VolunteerTimeResultExhibitionServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub 
		sendNewData(req,resp); 
		
		 
		
		
	}/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	private void sendNewData(HttpServletRequest request,
			HttpServletResponse response) {
		 int currentPageSize = 0;
		String currentPageNumberParam = request.getParameter("currentPageSize");
		if (currentPageNumberParam != null) {
			currentPageSize = Integer.parseInt(currentPageNumberParam);
		}

		// 设置输出内容的文档类型和编码
		response.setContentType("text/javascript;charset=UTF-8");

		log.debug("****************投票中心数据查询****************");
		log.debug("开始查询······");
		System.out.println("****************投票中心数据查询****************");
		System.out.println("开始查询······");

		String rsStr = "";

		// **********************根据当前页数及页面大小从数据库中获取相关信息**********************
		// 创建Dao对象，用于从数据库查询数据

		VoteCenterDaoImpl dao = new VoteCenterDaoImpl();

		try {
			// 获取系统当前时间，统计 花费时间
			long startTime = System.currentTimeMillis();
			System.out.println("request  "+request.getParameter("lastId"));
			if(request.getParameter("lastId")==null) return ;
				int lastId=Integer.valueOf(request.getParameter("lastId"));	//得到本地最后一条数据以便显示后面n条数据;
		
				rsStr = dao.get(lastId);

			// 获取系统当前时间，统计花费时间
			long endTime = System.currentTimeMillis();
			log.debug("查询所花时间：" + (endTime - startTime) + "毫秒");
			log.debug("查询结果：" + rsStr);

			// 获取http输出流对象
			PrintWriter writer = response.getWriter();
			log.debug("开始网络传输······");
			// 获取系统当前时间，用于统计所花费的时间
			startTime = System.currentTimeMillis();
			System.out.println(rsStr);
			
			writer.write(rsStr);
			// 获取系统当前时间，用于统计所花费的时间
			endTime = System.currentTimeMillis();
			log.debug("网络传输所花时间：" + (endTime - startTime) + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub 
		this.doGet(req, resp);
	}

}
