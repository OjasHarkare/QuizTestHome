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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createtest
 */
@WebServlet("/createtest")
public class createtest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createtest() {
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
		PrintWriter out = response.getWriter();

		String test_id = request.getParameter("test_id");
		HttpSession session = request.getSession();
		session.setAttribute("test_id", test_id);
		//System.out.println(test_id);
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  	
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ojas","system","system");
			Statement st = con.createStatement();
			st.executeQuery("CREATE TABLE " + test_id + " (QID INTEGER, QUES VARCHAR2(4000),OP1 VARCHAR2(4000),OP2 VARCHAR2(4000),OP3 VARCHAR2(4000),OP4 VARCHAR2(4000),ANS VARCHAR2(4000))");
			System.out.println("Table Created");
			
			RequestDispatcher req = request.getRequestDispatcher("admin_createtest.jsp");
			req.forward(request, response);
			
			}         
			catch (Exception e2) {
				out.println("<meta http-equiv='refresh' content='3;URL=adminLogin.jsp'>");
	         out.println("<p style='color:red;'>Test already Exists</p>");;
		
			}
			
		
	}

}