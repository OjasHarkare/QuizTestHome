package com.quiz.reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login_username = request.getParameter("login_email");
		String login_password = request.getParameter("login_password");
		String password=null;
		PrintWriter out = response.getWriter();

		if(login_username.isEmpty() || login_password.isEmpty() )
		{
			RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.include(request, response);
		}
		else if(login_username.equals("admin") && login_password.equals("admin"))
		{
			RequestDispatcher req = request.getRequestDispatcher("adminLogin.jsp");
			req.forward(request, response);
		}
		else
		{
			try{  
				Class.forName("oracle.jdbc.driver.OracleDriver");  
	
				java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ojas","system","system");
				
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;	
				preparedStatement = connection.prepareStatement("SELECT PASS FROM registeruser WHERE NAME = ?");
		         preparedStatement.setString(1, login_username);
		         resultSet = preparedStatement.executeQuery();
		         while(resultSet.next()){
		        	password= resultSet.getString(1);
		         }
		         if(login_password.equals(password)){

		 			RequestDispatcher req = request.getRequestDispatcher("StartTest.jsp");
		 			req.forward(request, response);
		         }
		         else{
		        	 out.println("<meta http-equiv='refresh' content='3;URL=Register.jsp'>");
			         out.println("<p style='color:red;'>Username or Password Invalid</p>");
		         }
			}
		         
				catch (Exception e2) {
					out.println("<meta http-equiv='refresh' content='3;URL=Register.jsp'>");
			         out.println("<p style='color:red;'>Username or Password Invalid</p>");}
			
		}
		}
}
