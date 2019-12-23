<%-- 
    Document   : placeorder
    Created on : 16 Apr, 2019, 2:24:49 AM
    Author     : hp
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place Order </title>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">

        <script src="scripts/jquery.js"></script>
        <script src="scripts/removeitem.js"></script>
 
    </head>
    <body >
        <%@include file="logo.html" %>
       
        <%
            
             String username  = (String)session.getAttribute("username");
             System.out.println("username in the placeorder: "+ username);
            if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
            }
            else 
            { 
                
               // response.setIntHeader("Refresh", 2);
                StringBuffer displayBlock = new StringBuffer("<h1><font color='green'>My Store Order Details</font></h1>");
                displayBlock.append("<div style = 'float:left';>");
                Enumeration en = session.getAttributeNames();
                displayBlock.append("<table border= '1'>");
                displayBlock.append("<tr><th>Item id:</th><th>Item Name</th><th>Item Price</th><th>Remove </th></tr>");
                double totalAmount = 0.0;
                while(en.hasMoreElements())
                {
                  Object o = en.nextElement();
                   if(o.equals("username")==false)
                   {
                      // System.out.println("inside if of placeorder");
                       ItemDTO item = (ItemDTO)session.getAttribute(o.toString());
                       displayBlock.append("<tr id='"+item.getItemId()+"'><td>"+item.getItemId()+"</td><td>"+item.getItemName()+"</td><td>"+item.getItemPrice()+"</td><td><input type='button' id='removebtn' value='Delete' onclick='removeitem("+item.getItemId()+")'></td></tr>");
                       totalAmount += item.getItemPrice();
                   }
                }
                displayBlock.append("</table>");
                displayBlock.append("<p><strong> Total Amount to Pay: </strong>"+totalAmount+"</p>");
                displayBlock.append("<p><a href = 'StoreControllerServlet'> Continue Shopping</a>&nbsp;&nbsp;&nbsp;&nbsp;");
                displayBlock.append("<a href='checkout.jsp?totalAmount=" + totalAmount + "'> CheckOut</a></p>");
                out.println(displayBlock);
            }
         %>
       
        </body>
</html>