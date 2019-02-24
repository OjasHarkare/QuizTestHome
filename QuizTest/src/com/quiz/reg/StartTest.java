package com.quiz.reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartTest
 */
@WebServlet("/StartTest")
public class StartTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTest() {
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
		int current = 0,Tot_ques=0;
		List<Object> qlist=new ArrayList<>();	
		String test_id = request.getParameter("test_id");
		PrintWriter out = response.getWriter();
			try{  		

				Class.forName("oracle.jdbc.driver.OracleDriver");  
					java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ojas","system","system");
					        PreparedStatement preparedStatement = null;
		        ResultSet rs = null,resultset=null;	
		        
		        PreparedStatement pi=((java.sql.Connection) connection).prepareStatement("SELECT MAX(QID) FROM " +test_id);
		        resultset = pi.executeQuery();
		        if (resultset.next())
				Tot_ques = resultset.getInt(1);
		        
				preparedStatement = connection.prepareStatement("SELECT * FROM " +test_id);
		         rs = preparedStatement.executeQuery();
		         while(rs.next()){
		        	    qlist.add(rs.getString(1));
		                qlist.add(rs.getString(2));
		                qlist.add(rs.getString(7));
		                qlist.add(rs.getString(3));
		                qlist.add(rs.getString(4));
		                qlist.add(rs.getString(5));
		                qlist.add(rs.getString(6));
		         }  
		       //System.out.println(qlist);
			      HttpSession sess=request.getSession(true);
	             sess.setAttribute("qlist",qlist);
	             System.out.println("Tot ques : " + Tot_ques);
	             sess.setAttribute("Tot_ques", Tot_ques);
	             sess.setAttribute("count", 0);
	             sess.setAttribute("hidden", current);
	             //current = (Integer)sess.getAttribute("current");
	             RequestDispatcher rd=request.getRequestDispatcher("quiz.jsp");
	             if(rd !=null)
	             {
	                 rd.forward(request, response);
	             }
	              
	         rs.close();
	         preparedStatement.close();
	         connection.close(); }  				
				catch (Exception e2) {
					System.out.println(e2);
					out.println("<meta http-equiv='refresh' content='3;URL=StartTest.jsp'>");
			         out.println("<p style='color:red;'>Test ID Incorrect</p>");}
			
	}
	}

