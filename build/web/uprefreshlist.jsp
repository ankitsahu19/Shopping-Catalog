<%-- 
    Document   : uprefreshlist
    Created on : 16 Apr, 2019, 2:29:45 AM
    Author     : hp
--%>

<%@page import="java.util.ArrayList"%>
<%
  ArrayList<Integer> idm=(ArrayList<Integer>)request.getAttribute("id");
       StringBuilder displayBlock=new StringBuilder();
       displayBlock.append("<div id=updivrefresh>");
        displayBlock.append("Choose the Item ID : <select id='selectid' onchange='getIdDetails()'>");
         for(Integer id : idm)
         {
           displayBlock.append("<option>"+id+"</option>");
          }
           displayBlock.append("</select>");
         displayBlock.append("</div >");
         System.out.println("sending div from uprefreshlist: "+displayBlock);
        out.println(displayBlock);





%>