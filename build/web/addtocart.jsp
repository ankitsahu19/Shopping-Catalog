<%-- 
    Document   : addtocart
    Created on : 16 Apr, 2019, 2:21:53 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <title>Add to cart Page</title>
    </head>
    <body>
      <%@include  file="logo.html" %>
        
        <%
            String username  = (String)session.getAttribute("username");
             System.out.println("username in the add to cart: "+ username);
            if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
            }
            else 
            {
              String item_id = request.getParameter("item_id");  
              int itemid = Integer.parseInt(item_id);
             try
             {
              ItemDTO item =   StoreDAO.getItemDetails(itemid);
              // setting session's attribute
              session.setAttribute(String.valueOf(item.getItemId()),item);
              StringBuffer displayBlock = new StringBuffer("<h1> My Store-shopping cart</h1>");
              displayBlock.append("<div style='float:left;'>");
              displayBlock.append("<p><strong>Item added successfully</strong></p>");
              displayBlock.append("<p>Item Id :"+item.getItemId()+"</p>");
              displayBlock.append("<p>Item Name: "+item.getItemName()+"</p>");
              Enumeration  en = session.getAttributeNames();
              int count =0;
              while(en.hasMoreElements())
              {
              ++count;
              en.nextElement();
              }
             
             
              displayBlock.append("<p>Total Items in cart:"+(count-1)+"</p>");//because there is also username 
              displayBlock.append("<p><a href = 'StoreControllerServlet'> Continue Shopping </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
              displayBlock.append("<a href='placeorder.jsp'>Place Order</a></p></div>") ;
               displayBlock.append("<h2 id='logout'><a href='myorders.jsp'>My orders</a>&nbsp;&nbsp;&nbsp;<a href='loginControllerServlet?logout=logout'>Logout</a></h2>");
            
            out.println(displayBlock);
             }
             catch(Exception e)
             {
             e.printStackTrace();
             System.out.println("Exeption in add to cart jsp"+e.getMessage());
             }
            }    
        %>
        
    </body>
</html>
