package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import shoppingcatalog.dao.StoreDAO;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import shoppingcatalog.dto.OrderDTO;

public final class myorders_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/stylesheet.css\">\n");

   
    String username = (String)session.getAttribute("username");
     if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
               
            }
     else
     {
   StringBuffer displayblock=new StringBuffer("<h1>My Store- My orders: </h1>");
   displayblock.append("<div style='float:left;'>");
         ArrayList<OrderDTO> orderList= StoreDAO.getOrdersByCustomer(username);
        
         if(orderList.size()==0)
         {
         displayblock.append("<p><strong>You have not placed any order yet!!</strong></p>");
         
         out.println(displayblock);
         }
         else
         {
         SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
         displayblock.append("<table border='1'>");
         displayblock.append("<tr><th>Order Id:</th><th>Order Amount:</th><th>Order Date</th></tr>");
         for(OrderDTO o:orderList)
         {
        String dateStr=sdf.format(o.getOrderDate());
        String order=o.getOrderId();
         displayblock.append("<tr><td><a href='OrderInfo.jsp?ordername="+order+"'>"+order+"</a></td><td>"+o.getOrderAmount()+"</td><td>"+dateStr+"</td><tr>");
       }
          displayblock.append("</table>");
         
         displayblock.append("<p><a  href='StoreControllerServlet'>Show Categories </a></p></div>");
         displayblock.append("</div>");
         displayblock.append("<h2 id='logout'><a href='myorders.jsp'>My orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h2>");
           out.println(displayblock);  
         
         }    
     
     }
 


    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
