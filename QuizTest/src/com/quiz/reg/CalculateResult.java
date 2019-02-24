package com.quiz.reg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CalculateResult
 */
@WebServlet("/CalculateResult")
public class CalculateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
		 int current = 0;
         int num=1, optno = 0;
         String selected_opt = request.getParameter("option") ;
         System.out.println(selected_opt);
         if (selected_opt.equals("A"))
         {
        	 optno = 4; 
         }else if (selected_opt.equals("B"))
         {
        	 optno = 5;
         }else if (selected_opt.equals("C"))
         {
        	 optno = 6;
         }else if (selected_opt.equals("D"))
         {
        	 optno = 7;
         }
         HttpSession sess=request.getSession();
			String test_id1 =(String)sess.getAttribute("test_id");
			 System.out.println(test_id1);
			int count = (int)sess.getAttribute("count");
			 System.out.println("count = " + count);
         Object qlist=sess.getAttribute("qlist");
         int Tot_ques = (Integer)sess.getAttribute("Tot_ques");
         List qlist1= (ArrayList)qlist;
         ListIterator itr=qlist1.listIterator();
         
         sess.setAttribute("qlist",qlist);
         sess.setAttribute("Tot_ques", Tot_ques);
         sess.setAttribute("test_id", test_id1);
   
         if (request.getParameter("hidden") != null) 
        {
          current = Integer.parseInt(request.getParameter("hidden"));
         }
         sess.setAttribute("hidden", current);
         
         System.out.println(current);
         
         if (current < Tot_ques - 1)
         {
         	for (int i = 0; i < current; i++) 
         	{	
         	
             	itr.next();
             	itr.next();
             	itr.next();
             	itr.next();
             	itr.next();
             	itr.next();
             	itr.next();                     
            		num++;                    
         	} 
         }
         for (int k =0;k<2;k++)
         {
        	itr.next(); 
         }
         String answer = (String)itr.next();
         for(int j=0; j < optno - 4;j++) 
         {
        	itr.next();
         }
         String Selected_otp = (String)itr.next();
         if(answer.equals(Selected_otp))
         {
        	 count++;
         }
         sess.setAttribute("count", count); 
         RequestDispatcher rd=request.getRequestDispatcher("quiz.jsp");
         if(rd !=null)
         {
             rd.forward(request, response);
         }
          
	}
	

}
