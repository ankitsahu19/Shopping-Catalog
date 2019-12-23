<%-- 
    Document   : OrderInfo
    Created on : 1 Apr, 2019, 1:38:49 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page import="shoppingcatalog.dto.OrderInfoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Order Info Page</title>
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
         String ordername=request.getParameter("ordername");
         ArrayList<OrderInfoDTO> order=StoreDAO.getInfoByOrder(ordername);
         if(order.size()==0)
         {
             System.out.println("No orders exits for OrderID: "+ordername);
         }
         
         StringBuffer displayBlock=new StringBuffer("<p>Welcome : "+username+"<br> Details of the Order ID :<strong><font color='blue'> "+ordername+"</font> </strong>is as follows:</p>");
         displayBlock.append("<br><table border='1'>");
         displayBlock.append("<tr><th>Item Name</th><th>Item Price</th></tr>");
      
         for(OrderInfoDTO obj:order)
        {
         
            displayBlock.append("<tr><td>"+obj.getItemName()+"</td><td>"+obj.getItemPrice()+"</td></tr>");
        }
      displayBlock.append("</table>");
      displayBlock.append("<p><a href='myorders.jsp'><strong> <--Back</strong></a></p>");
         out.println(displayBlock);
     }


%>    
     
     </body>
</html>
