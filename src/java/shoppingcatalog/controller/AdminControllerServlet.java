/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.AdminDAO;
import shoppingcatalog.dto.ItemDTO;

/**
 *
 * @author hp
 */
public class AdminControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session  = request.getSession();
            String username  = (String)session.getAttribute("username");
            System.out.println("inside admin controller servlet with ADMIN : "+username);
           String memberusername=request.getParameter("memberusername");
           String memberusertype=request.getParameter("memberusertype");
         
       //    String itemid=request.getParameter("itemid");
           RequestDispatcher rd = null;
           try
           {
           if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
               return;
            }
            else 
            {
               if(memberusername!=null )//&& memberusertype!=null)
                {
                   System.out.println("Memberusername is: "+memberusername);
                   System.out.println("Memberusertype is: "+memberusertype);
                   System.out.println("admin user name is : "+username);
                    
                   if(username.equals(memberusername) && memberusertype.equals("ADMIN"))
                    {
                         boolean remove=AdminDAO.removeUser(memberusername, memberusertype);
                    System.out.println("is user is removed within user admin : "+remove);
                        session.invalidate();
               response.sendRedirect("removedadmin.html");
               return;
                    }
                boolean remove=AdminDAO.removeUser(memberusername, memberusertype);
                    System.out.println("is user is removed? : "+remove);
                    
                 
                //    System.out.println("User has been successfully removed from the DB ");
             //   request.setAttribute("remove",remove );
               rd = request.getRequestDispatcher("alteruserdetails.jsp");
                }
               
          /*     if(itemid!=null)
               {
                   int item=Integer.parseInt(itemid);
               ItemDTO obj =AdminDAO.getItemDetails(item);
                   System.out.println("itemDTO obj arrived in ACS for Displaying in Update Form");
               request.setAttribute("itemdetails", obj);
                   System.out.println("calling updatedisplay jsp");
               rd = request.getRequestDispatcher("updatedisplay.jsp");
                   System.out.println("just below call to updatedisplay jsp ");
               }
               else
               {
                   System.out.println("in else of itemid!=null from ACS");       
               }
               */
             //    int refresh=Integer.parseInt(request.getParameter("refresh"));
            //  if(refresh==10)
           //  {
              
            //       rd=request.getRequestDispatcher("seeoption.jsp");
               
           // }
                   
           
                String pname=(String)request.getParameter("pname");
             String ptype=(String)request.getParameter("ptype");
             String pprice=(String)request.getParameter("pprice");
             String pdesc=(String)request.getParameter("pdesc");
             String pimage=(String)request.getParameter("pimage");
                if(pname==null && ptype==null && pdesc==null && pimage==null && pprice==null )
                {
                    System.out.println("");
                     System.out.println("add form is empty , sending req to seeoption from ACS");
                     
                      ArrayList<Integer> id=AdminDAO.getIds();
                   System.out.println("ids arrived in ACS");
                   request.setAttribute("ids", id);
                     
             rd = request.getRequestDispatcher("seeoption.jsp");
                    System.out.println("stmt below call to see option ");
                }
                else
                {
                 ItemDTO item=null;
                System.out.println("Empty condition passed, now creating ItemDTO object in ACS");
                item =new ItemDTO();
                item.setItemName(pname);
                item.setItemDesc(pdesc);
                item.setItemPrice(Double.parseDouble(pprice));
                item.setItemType(ptype);
                item.setItemImage(pimage);
                boolean result=AdminDAO.addItemIntoStore(item);
                System.out.println("Result is: "+result);
                 request.setAttribute("result", result);
           System.out.println("Result after addItemInfoStore is:"+result);
             System.out.println("sending req to addformresponse jsp from ACS");
           rd = request.getRequestDispatcher("addformresponse.jsp");
                }
 }
        
    }
          catch(Exception ex)
            {
                rd = request.getRequestDispatcher("showexception.jsp");
                ex.printStackTrace();
            }
            finally
            {
                rd.forward(request, response);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
