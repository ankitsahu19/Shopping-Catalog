<%-- 
    Document   : loginresponse
    Created on : 16 Apr, 2019, 2:23:30 AM
    Author     : hp
--%>
<%

boolean result=(Boolean)request.getAttribute("result");
String username=(String)request.getAttribute("username");
String usertype=(String)request.getAttribute("usertype");
if(result==true && usertype.equalsIgnoreCase("CUSTOMER"))
{
String url="StoreControllerServlet;jsessionid="+session.getId();
out.println(url);
HttpSession sess=request.getSession();
sess.setAttribute("username",username);
System.out.println("customer login response");

}
else if(result==true && usertype.equalsIgnoreCase("ADMIN"))
{
String url="AdminControllerServlet;jsessionid="+session.getId();
//System.out.println("Session id : "+url);
out.println(url);
HttpSession sess=request.getSession();
sess.setAttribute("username",username);
//System.out.println("sess value :" +sess);
System.out.println("admin login response");
}

else
{
out.println("incorrectdata");
}



%>