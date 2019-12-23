<%-- 
    Document   : showitemsbytype
    Created on : 16 Apr, 2019, 2:28:51 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dto.ItemInfoDTO"%>
<%@page import="java.util.ArrayList"%>
<%
   
    String username = (String)session.getAttribute("username");
     if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
               
            }
            else
        {
         out.println("<ul>");   
        ArrayList<ItemInfoDTO>items = (ArrayList<ItemInfoDTO>)  request.getAttribute("iteminfo");
        for(ItemInfoDTO item :items)
        {
           int itemid =  item.getItemId();
           String itemname = item.getItemName();
           out.println("<li id = '" +itemid+"'> <a href =StoreControllerServlet?item_id="+itemid+">"+itemname +" </a> </li>");
        }
        out.println("</ul>");
        }

%>