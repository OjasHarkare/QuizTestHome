package com.quiz.reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deletetest
 */
@WebServlet("/deletetest")
public class deletetest extends HttpServlet {
	String test_id;
	private static final long serialVersionUID = 1L; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletetest() {
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
		
		
		try{  
			test_id = request.getParameter("delete_test_id");
			Class.forName("oracle.jdbc.driver.OracleDriver");  	
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ojas","system","system");
			Statement st = con.createStatement();
			st.executeQuery("DROP TABLE " + test_id);
		  			System.out.println("Table Deleted");		
				  				         
			RequestDispatcher req = request.getRequestDispatcher("adminLogin.jsp");
			req.forward(request, response);
			}         
			catch (Exception e2) {
				PrintWriter out = response.getWriter();
				out.println("<meta http-equiv='refresh' content='3;URL=admin_createtest.jsp'>");
	         out.println("<p style='color:red;'>Test Does not Exists</p>");
		
			}
	}

}
