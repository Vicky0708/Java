package org.huxia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huxia.bean.Comment;
import org.huxia.bean.LikeFunction;
import org.huxia.bean.User;
import org.huxia.dao.CommentDao;
import org.huxia.dao.PostDao;

/**
 * Servlet implementation class LikePostServlet
 */
@WebServlet("/LikePostServlet")
public class LikePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String postid = request.getParameter("postid");
		String action = request.getParameter("action");
		PostDao postdao=new PostDao();

		
		if (postid == null || "".equals(postid)) {
			out.print("<script type='text/javascript'>");
			out.print("alert('miss postid parameter.');");
			out.print("window.location='index.jsp';");
			out.print("</script>");
			return;
		}
		String id = ((User)request.getSession().getAttribute("User")).getStuId();
		LikeFunction like = new LikeFunction();
		like.setId(id);
		like.setPostId(postid);
		like.setLike("1");
		
		Boolean result = postdao.create(like);
		if (result) {
			out.print("<script type='text/javascript'>");
			out.print("alert('点赞成功！');");
			out.print("window.location='post.jsp?postid="+postid+"';");
			out.print("</script>");
		}  else {
			out.print("<script type='text/javascript'>");
			out.print("alert('您已点过赞了！');");
			out.print("window.location='javascript:history.go(-1);';");
			out.print("</script>");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
