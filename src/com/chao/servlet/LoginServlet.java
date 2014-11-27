package com.chao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chao.dao.factory.DAOFactory;
import com.chao.model.User;
import com.chao.tool.Util;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Integer sign = Integer.valueOf(request.getParameter("sign"));
		if (sign == 0) {
			this.doAdminLoginCheck(request, response);
		} else if (sign == 1) {
			this.doAddUserCheck(request, response);
		} else if (sign == 2) {
			this.doAddUser(request, response);
		} else if (sign == 3) {
			this.doDeleteUser(request, response);
		} else if (sign == 4) {
			this.doAdminUser(request, response);
		} else if (sign == 5) {
			this.doCancelAdminUser(request, response);
		} else if (sign == 6) {
			this.doClickDeleteUser(request, response);
		} else if (sign == 7) {
			this.doBatchUser(request, response);
		} else {
			System.out.println(sign);
		}

	}

	/**
	 * @param request
	 * @param response
	 */
	private void doBatchUser(HttpServletRequest request,
			HttpServletResponse response) {
		String[] userids = request.getParameterValues("checkboxClick");
		String operator = request.getParameter("dropdownoperator");
		if (userids != null && userids.length > 0)
			if (operator != null && "option2".equals(operator)) {
				for (int i = 0; i < userids.length; i++) {
					System.out.println(userids[i]);
					User user;
					try {
						user = (User) DAOFactory.getUserDaoInstance().findById(
								userids[i]);
						if (user != null) {
							user.setAuthority(1);
							DAOFactory.getUserDaoInstance().update(user);

						} else {
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					response.sendRedirect("/ScauActivityWeb/admin/manageUser.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (operator != null && "option3".equals(operator)) {
				for (int i = 0; i < userids.length; i++) {
					System.out.println(userids[i]);
					User user;
					try {
						user = (User) DAOFactory.getUserDaoInstance().findById(
								userids[i]);
						if (user != null) {
							DAOFactory.getUserDaoInstance().delete(userids[i]);
						} else {
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					response.sendRedirect("/ScauActivityWeb/admin/manageUser.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					response.sendRedirect("/ScauActivityWeb/admin/manageUser.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else
			try {
				response.sendRedirect("/ScauActivityWeb/admin/manageUser.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doCancelAdminUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		System.out.println(userid);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			if (user != null && user.getPassword().equals(password)) {
				System.out.println(userid);
				user.setAuthority(0);
				DAOFactory.getUserDaoInstance().update(user);
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
			out.close();
		} catch (Exception e) {
			System.out.println("doAddUserCheck-err");
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAdminUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		System.out.println(userid);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			if (user != null && user.getPassword().equals(password)) {
				System.out.println(userid);
				user.setAuthority(1);
				DAOFactory.getUserDaoInstance().update(user);
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
			out.close();
		} catch (Exception e) {
			System.out.println("doAddUserCheck-err");
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doDeleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		System.out.println(userid);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			if (user != null && user.getPassword().equals(password)) {
				System.out.println(userid);
				DAOFactory.getUserDaoInstance().delete(userid);
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
			out.close();
		} catch (Exception e) {
			System.out.println("doAddUserCheck-err");
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doClickDeleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		System.out.println(userid);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			if (user != null) {
				System.out.println(userid);
				DAOFactory.getUserDaoInstance().delete(userid);
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("doAddUserCheck-err");
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAddUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		System.out.println(userid);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			if (user == null) {
				System.out.println(userid);
				user = new User();
				user.setUserid(userid);
				user.setPassword(password);
				user.setUsername("");
				user.setAuthority(0);
				DAOFactory.getUserDaoInstance().add(user);
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
			out.close();
		} catch (Exception e) {
			System.out.println("doAddUserCheck-err");
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAddUserCheck(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		System.out.println(userid);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			if (user == null) {
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("doAddUserCheck-err");
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAdminLoginCheck(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String authority = request.getParameter("authority");
		System.out.println(userid + ":" + password + ":" + authority);
		try {
			User user = (User) DAOFactory.getUserDaoInstance().findById(userid);
			System.out.println("user.getPassword() = " + user.getPassword()
					+ " Util.getMD5Str(password.trim()) = "
					+ Util.getMD5Str(password.trim()));
			System.out.println("user.getAuthority() = " + user.getAuthority());
			if (user != null
					&& user.getPassword().trim()
							.equals(Util.getMD5Str(password.trim()).trim())
					&& (Integer.parseInt(authority) == 1 ? user.getAuthority() == Integer
							.parseInt(authority) : true)) {
				HttpSession session = request.getSession();
				if (Integer.parseInt(authority) == 1) {
					session.setAttribute("userinfo", user);
				} else {
					session.setAttribute("commonUserinfo", user);
				}
				out.print(true);
				// response.sendRedirect("/ScauActivityWeb/admin/index.jsp");
			} else {
				out.print(false);
				// response.sendRedirect("/ScauActivityWeb/admin/login.jsp");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("doAdminLoginCheck-err");
			e.printStackTrace();
		}
	}

}
