<%-- 
    Document   : seestore
    Created on : 16 Apr, 2019, 2:27:28 AM
    Author     : hp
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script src="scripts/jquery.js"></script>
        <script src="scripts/showitems.js"></script>
    </head>
    <body>
        <%@include  file="logo.html" %>
<%
    StringBuffer displayBlock= null ;
            
            String username  = (String)session.getAttribute("username");
             System.out.println("username in the seestore real "+ username);
            if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
            }
            else
            {
            displayBlock = new StringBuffer(" <h1>My Categories</h1><p>Select a category to see its items<p>");
             ArrayList<String> itemList =(ArrayList<String>) request.getAttribute("types");
             for(String itemType:itemList)
             {
                 displayBlock.append("<p id='" +itemType+ "'><strong><a href = '#'onclick =getitemNames('"+itemType+"')><span>+"+itemType+"</span></a></strong></p>");
             }
            
             displayBlock.append("<h2 id='logout'><a href='myorders.jsp'>My orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
            out.println(displayBlock);
            }
            
%>
    </body>
</html>
