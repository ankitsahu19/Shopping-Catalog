<%-- 
    Document   : myorders
    Created on : 16 Apr, 2019, 2:24:24 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="shoppingcatalog.dto.OrderDTO"%>
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
   StringBuffer displayblock=new StringBuffer("<h1><font color='green'>My Store- My orders:</font></h1>");
   displayblock.append("<div style='float:left;'>");
         ArrayList<OrderDTO> orderList= StoreDAO.getOrdersByCustomer(username);
        
         if(orderList.size()==0)
         {
         displayblock.append("<p><strong>You have not placed any order yet!!</strong></p>");
         
         out.println(displayblock);
         }
         else
         {
         SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
         displayblock.append("<table border='1' bgcolor='blue'>");
         displayblock.append("<tr><th>Order Id:</th><th>Order Amount:</th><th>Order Date</th></tr>");
         for(OrderDTO o:orderList)
         {
        String dateStr=sdf.format(o.getOrderDate());
        String order=o.getOrderId();
         displayblock.append("<tr><td><a href='OrderInfo.jsp?ordername="+order+"'>"+order+"</a></td><td>"+o.getOrderAmount()+"</td><td>"+dateStr+"</td><tr>");
       }
          displayblock.append("</table>");
         
         displayblock.append("<p><a  href='StoreControllerServlet'>Show Categories </a></p></div>");
         displayblock.append("</div>");
         displayblock.append("<h2 id='logout'><a href='myorders.jsp'>My orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
           out.println(displayblock);  
         
         }    
     
     }
 

%>