<%-- 
    Document   : addformresponse
    Created on : 16 Apr, 2019, 2:21:30 AM
    Author     : hp
--%>
<%

boolean result=(Boolean)request.getAttribute("result");
if(result)
{
out.println("Success");
}
else
{

out.println("Failure");

}



%>