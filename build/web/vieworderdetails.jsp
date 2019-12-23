<%-- 
    Document   : vieworderdetails
    Created on : 16 Apr, 2019, 2:30:11 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dao.AdminDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shoppingcatalog.dto.ViewOrderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order Details</title>
       
    </head>
    <body>
          <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
      <%
   
    String username = (String)session.getAttribute("username");
     if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
               
            }
     else
     {
         
         ArrayList<ViewOrderDTO> order=AdminDAO.viewOrderDetails();
         if(order.size()==0)
         {
             System.out.println("ArrayList recievd in vieworderdetails is empty");
             return;
         }
         
         StringBuffer displayBlock=new StringBuffer("<h2><font color='green'>Welcome :</font> <font color='white'>"+username+"</font></h2><stong><h3><font color='green'>Complete Order Details of <font color='blue'>E-Medicine</font> are as follows :</font></h3></strong></p>");
        
         displayBlock.append("<center>");
         displayBlock.append("<br><table border='1'>");
         displayBlock.append("<tr><th>Order ID</th><th>Customer Name</th><th>Order Amount</th><th>Order Date</th><th>Item Names</th></tr>");
      
         for(ViewOrderDTO obj:order)
        {
         
            displayBlock.append("<tr><td><center>"+obj.getOrderId()+"</center></td><td><center>"+obj.getCustName()+"</center></td><td><center>"+obj.getOrderAmount()+"</center></td><td><center>"+obj.getOrderDate()+"</center></td><td><center>"+obj.getItemName()+"</center></td></tr>");
        }
      displayBlock.append("</table>");
      displayBlock.append("</center>"); 
      displayBlock.append("<h2 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
     //  displayBlock.append("<h3><p><center><a href='seeoption.jsp'><strong> <--Back</strong></a></center></p></h3>");
      out.println(displayBlock);
     }


%>    
        
        
    </body>
</html>
