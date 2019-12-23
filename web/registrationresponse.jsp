<%-- 
    Document   : registrationresponse
    Created on : 16 Apr, 2019, 2:25:52 AM
    Author     : hp
--%>

<%
boolean userfound=(Boolean)request.getAttribute("userfound");

boolean result=(Boolean)request.getAttribute("result");

if(userfound==true)
{
out.println("uap");

}
else if(result==false)
{
out.println("failure");

}
else 
{
out.println("success");

}
%>