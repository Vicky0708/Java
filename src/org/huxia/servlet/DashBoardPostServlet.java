package org.huxia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huxia.bean.DashBoradPost;
import org.huxia.bean.GenderCount;
import org.huxia.dao.DashBoardPostDao;
import org.huxia.dao.GenderCountDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DashBoardPostServlet
 */
@WebServlet("/DashBoardPostServlet")
public class DashBoardPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashBoardPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DashBoardPostDao Postcount = new DashBoardPostDao();
		List<DashBoradPost> post =Postcount.calculatePostNumber();
		JSONObject jsonObject =new JSONObject();
		JSONArray name =new JSONArray();
		JSONArray no =new JSONArray();
		response.setCharacterEncoding("UTF-8");
		for(int i=0; i< post.size();i++)
			{
				name.add(post.get(i).getStuName());	
				no.add(post.get(i).getNumber());
			}
		jsonObject.put("name", name);
		jsonObject.put("no", no);
		PrintWriter out = response.getWriter();
		
		out.print(jsonObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
