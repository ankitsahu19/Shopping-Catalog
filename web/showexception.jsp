<%-- 
    Document   : showexception
    Created on : 16 Apr, 2019, 2:27:58 AM
    Author     : hp
--%>


<%

Exception e=(Exception)request.getAttribute("exception");
String msg=e.getMessage();
System.out.println("Exception is (exc. from login servlet):"+e);
out.println(msg);
%>