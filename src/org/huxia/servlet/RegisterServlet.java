package org.huxia.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.huxia.bean.User;
import org.huxia.dao.UserDao;

import java.io.File;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String stuId = request.getParameter("stuId");
		String stuName = request.getParameter("stuName");
		String password = request.getParameter("password");
		//String avatar = request.getParameter("avatar");
		String gender = request.getParameter("gender");
		String bio = request.getParameter("bio");
		String gitUrl = request.getParameter("gitUrl");
		
		Part part = request.getPart("avatar");
		String fileName = part.getSubmittedFileName();	
		String newFileName = UUID.randomUUID().toString() + "_" + fileName;
		String filePath = getServletContext().getRealPath("/img/upload");
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		System.out.println(filePath);
		part.write(filePath + "/" + newFileName);
		String avatar = newFileName;

		
		UserDao userDao = new UserDao();
		User user = new User();
		user.setStuId(stuId);
		user.setStuName(stuName);
		user.setPassword(password);
		user.setAvatar(avatar);
		user.setGender(gender);
		user.setBio(bio);
		user.setGitUrl(gitUrl);
		boolean result = userDao.create(user);		

		RequestDispatcher rDispatcher = request.getRequestDispatcher("result.jsp");
		request.setAttribute("resMessage", result==true?"注册成功":"注册失败");
		rDispatcher.forward(request, response);
	}

}
