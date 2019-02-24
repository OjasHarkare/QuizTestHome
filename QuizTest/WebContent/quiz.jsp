<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz</title>
</head>
<body>
<form name="form1" action="CalculateResult" method="post" target="_self">
<% 				

               int current = 0;
                int num=1;
                HttpSession sess=request.getSession();
    			String test_id1 =(String)sess.getAttribute("test_id");
    
                Object qlist=sess.getAttribute("qlist");
                int Tot_ques = (Integer)sess.getAttribute("Tot_ques");
                int count = (int)sess.getAttribute("count");
                List qlist1= (ArrayList)qlist;
                ListIterator itr=qlist1.listIterator();
               // if (request.getParameter("hidden") != null) 
               //{
               //  current = Integer.parseInt(request.getParameter("hidden"));
               // }
               current =(Integer)sess.getAttribute("hidden");
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
                sess.setAttribute("count", count);
                %>

<p>
 				<input type="hidden" name="qid" value="<%=itr.next() %>"/>
               <textarea name="question" cols="50" rows="5"  Readonly id="question" ><%=num %>.<%=itr.next() %>
               </textarea>
            </p>
                  <input type="hidden" name="corans" value="<%=itr.next() %>"/>
                   
            <p>
                  A).<input type="radio" name="option" value="A"><%=itr.next()%>
            </p>
            <p>
                  B).<input type="radio" name="option" value="B"><%=itr.next()%>
            </p>
            <p>
                  C).<input type="radio" name="option" value="C"><%=itr.next() %>
            </p>
            <p>
                  D).<input type="radio" name="option" value="D"><%=itr.next()%>
            </p>
             
            <BR/>
            <p>
                
                    <input type="hidden" name="hidden" value="<%=current+1 %>"/>
                    <input type="submit" VALUE="next">                   
            </p>  </form>
            <input type="button" VALUE="Submit Exam" onclick="location.href='ResultServlet'"/>

</body>
</html> 
