package org.huxia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huxia.bean.GenderCount;
import org.huxia.dao.GenderCountDao;
import net.sf.ezmorph.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GenderCountServlet
 */
@WebServlet("/GenderCountServlet")
public class GenderCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenderCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		GenderCountDao gendercount = new GenderCountDao();
		//gendercount.calculateGenderNumber();
		List<GenderCount> gender =gendercount.calculateGenderNumber();
		JSONObject jsonObject =new JSONObject();
		JSONArray sex =new JSONArray();
		JSONArray no =new JSONArray();

		for(int i=0; i< gender.size();i++)
			{
				sex.add(gender.get(i).getGender());	
				no.add(gender.get(i).getNumber());
			}
		jsonObject.put("sex", sex);
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
