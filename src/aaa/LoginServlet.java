package aaa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		/*if(username.equals("222")&&password.equals("222")) {
			response.getWriter().println("success");
		}
		else {
			response.getWriter().println("fail");
			
		}*/
		
		Connection connection = DBUtil.getConnection();
	 String sql="select count(*) from users where username=? and password=?";
	 ResultSet resultSet = null;
	 int result =0;
	 try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
				
			}
		} 
	 catch (SQLException e) {
			e.printStackTrace();
		}		
		//response.getWriter().println(result);
		RequestDispatcher rDispatcher = request.getRequestDispatcher("result.jsp");
		request.setAttribute("resMessage", result==1?"success":"fail");
		rDispatcher.forward(request, response);

	
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
