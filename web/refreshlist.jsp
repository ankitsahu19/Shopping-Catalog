<%-- 
    Document   : refreshlist
    Created on : 16 Apr, 2019, 2:25:18 AM
    Author     : hp
--%>

<%@page import="java.util.ArrayList"%>
<%
  ArrayList<Integer> idm=(ArrayList<Integer>)request.getAttribute("id");
       StringBuilder displayBlock=new StringBuilder();
       displayBlock.append("<div id=dfdivrefresh>");
        displayBlock.append("Choose the Item ID : <select id='dfselectid' onchange='dfGetIdDetails()' >");
         for(Integer id : idm)
         {
           displayBlock.append("<option>"+id+"</option>");
          }
           displayBlock.append("</select>");
         displayBlock.append("</div >");
         System.out.println("sending div from refreshlist: "+displayBlock);
        out.println(displayBlock);





%>