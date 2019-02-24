package com.quiz.reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class insert_ques
 */
@WebServlet("/insert_ques")
public class insert_ques extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert_ques() {
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
		HttpSession session = request.getSession();
		String test_id = (String) session.getAttribute("test_id");
		System.out.println(test_id);
		String ques = request.getParameter("ques");
		String op1 = request.getParameter("op1");
		String op2 = request.getParameter("op2");
		String op3 = request.getParameter("op3");
		String op4 = request.getParameter("op4");
		String ansop = request.getParameter("ansop");
		int j = 0;
		
		if(ques.isEmpty() || op1.isEmpty() || op2.isEmpty() || op3.isEmpty() || op4.isEmpty()|| ansop.isEmpty())
		{
			System.out.println("Please fill the data");
		}
		else{
		try{  
			ResultSet resultset = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");  	
			java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ojas","system","system");
			
			PreparedStatement pi=((java.sql.Connection) con).prepareStatement("SELECT MAX(QID) FROM " +test_id);
			System.out.println(test_id);
			//pi.setString(1,test_id);
			resultset = pi.executeQuery();
			if (resultset.next())
			j = resultset.getInt(1);
			System.out.println(j);
			PreparedStatement ps=((java.sql.Connection) con).prepareStatement("INSERT INTO " + test_id + " VALUES (?,?,?,?,?,?,?)"); 
			ps.setInt(1, j+1);
			ps.setString(2,ques);  
			ps.setString(3,op1);
			ps.setString(4,op2);  
			ps.setString(5,op3);
			ps.setString(6,op4);  
			ps.setString(7,ansop);
			int i=ps.executeUpdate();  
			if(i>0)  
			out.print("Data Inserted"); 					  				         
			RequestDispatcher req = request.getRequestDispatcher("admin_createtest.jsp");
			req.forward(request, response);
			}         
			catch (Exception e2) {
				System.out.println(e2);
				out.println("<meta http-equiv='refresh' content='3;URL=adminLogin.jsp'>");
	         out.println("<p style='color:red;'>Could not insert data</p>");
		
			}
		}
	}

}