<%-- 
    Document   : showitemdetails
    Created on : 16 Apr, 2019, 2:28:23 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <title>item details</title>
    </head>
    <body>
        <%
           String username =(String)session.getAttribute("username");
           if(username==null)
           {
               
               session.invalidate();
               response.sendRedirect("accessdenied.html");
           }
           ItemDTO item = (ItemDTO)request.getAttribute("item");
           
           
           StringBuffer displayBlock  =  new StringBuffer("<h1><font color='green'>My Store item Details</h1><p><em>You are viewing</em></font><br/>");
           displayBlock.append("<strong><a href='StoreControllerServlet'>"+item.getItemType()+"&gt;</a><font color='green'>"+item.getItemName()+"<strong></font></p>");
           displayBlock.append("<div style='float:left'>");
           displayBlock.append("<img src='images/"+item.getItemImage()+"'></div>");
           displayBlock.append("<div style='float:left;padding-left:12px'>");
           displayBlock.append("<p><strong>Description:</strong><br/>"+item.getItemDesc()+"</p>");
           displayBlock.append("<p><strong>Price:</strong>"+item.getItemPrice()+"</p>");
           displayBlock.append("<p><a href='addtocart.jsp?item_id="+item.getItemId()+"'><font color='white'>add to cart </a></font></p></div>");
           displayBlock.append("<h2 id='logout'><a href='myorders.jsp'>My orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
            
            out.println(displayBlock);
           
        %>
    </body>
</html>
