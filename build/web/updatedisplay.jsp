<%-- 
    Document   : updatedisplay
    Created on : 16 Apr, 2019, 2:29:19 AM
    Author     : hp
--%>

<%@page import="shoppingcatalog.dto.ItemDTO"%>
<%
System.out.println("called update display jsp");

ItemDTO item=(ItemDTO)request.getAttribute("itemdetails");
String collected=item.getItemId()+"*"+item.getItemName()+"*"+item.getItemType()+"*"+item.getItemDesc()+"*"+item.getItemPrice()+"*"+item.getItemImage();
System.out.println("response text sending from updatedisplay jsp");
/// till here this is not working, try in notepad++., problem is response text recieved is of seeoption and not of updatedisplay.
out.println(collected);



%>