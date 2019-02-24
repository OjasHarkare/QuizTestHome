package com.quiz.reg;

import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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
		String reg_email = request.getParameter("reg_email");
		String reg_password = request.getParameter("reg_password");
		PrintWriter out = response.getWriter();
			
		
		if(reg_email.isEmpty() || reg_password.isEmpty())
		{	
			RequestDispatcher req = request.getRequestDispatcher("Register.jsp");
			req.forward(request, response);
		}
		else
		{					
			try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  	
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ojas","system","system");
			PreparedStatement ps=((java.sql.Connection) con).prepareStatement("INSERT INTO registeruser VALUES(?,?)");  
			ps.setString(1,reg_email);  
			ps.setString(2,reg_password);  
				  				          
			int i=ps.executeUpdate();  
			if(i>0)  
			out.print("You are successfully registered..."); 
			
			RequestDispatcher req = request.getRequestDispatcher("index.jsp");
			req.forward(request, response);
			}         
			catch (Exception e2) {System.out.println(e2);
			
			out.println("<meta http-equiv='refresh' content='3;URL=Register.jsp'>");
	         out.println("<p style='color:red;'>User already Exists</p>");
			}
			
		}
	}
}
