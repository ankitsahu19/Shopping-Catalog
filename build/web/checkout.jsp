<%-- 
    Document   : checkout
    Created on : 16 Apr, 2019, 2:22:51 AM
    Author     : hp
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut Page</title>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
       
    
           
    </head>
    <body>
         <%@include  file="logo.html" %>
        <h1>Check Out</h1>
        <%
         String username  = (String)session.getAttribute("username");
             System.out.println("username in the checkout "+ username);
            if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
            }
            else 
            {
                double totalAmount =0.0;
                try{
                 totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
                }
                catch(Exception ex)
                {
                  ex.printStackTrace();
                  out.println("error happened  in conversion in checkout jsp ");
                }
                StringBuffer displayBlock = new StringBuffer("<h1><font color='maroon'>My Store-CheckOut Page</font></h1>");
                displayBlock.append("<div style = 'float:left;'>");
                displayBlock.append("<p><strong> Thank You For Shopping with us !</strong></p>");
                displayBlock.append("<p><strong> Your payment of RS. "+totalAmount+" is Under processing!</strong></p>");
                Enumeration en = session.getAttributeNames();
                ArrayList<ItemDTO>itemList = new ArrayList<ItemDTO>();
                while(en.hasMoreElements())
                {
                  String obj  = (String)  en.nextElement();
                  if(obj.equals("username")==false)
                  {
                     ItemDTO item = (ItemDTO)  session.getAttribute(obj);
                     session.removeAttribute(obj);
                     itemList.add(item);
                  }
                }
               try
               {
                 boolean res =  StoreDAO.addOrder(username, itemList, totalAmount);
                 
                 if(res)
                   displayBlock.append("<p><b>Orders has been <font color='green'>Successfully stored </font>in DB </b></p>");
                 else 
                   displayBlock.append("<p>there is some error while storing orders in db </p>");
                 displayBlock.append("<p><a href = 'StoreControllerServlet'> Continue Shopping </a> &nbsp;&nbsp;&nbsp;&nbsp;");
                  displayBlock.append("<h2 id='logout'><a href='myorders.jsp'>My orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
         
                 out.println(displayBlock);
               }
               catch(Exception ex)
               {
                   ex.printStackTrace();
               }
                
            }
        %>
 
    </body>
</html>
