/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateForm extends HttpServlet {

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
            System.out.println("inside update form servlet with ADMIN : "+username);
              String itemid=request.getParameter("itemid");
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
                  if(itemid!=null)
               {
                   int item=Integer.parseInt(itemid);
                    ItemDTO obj =AdminDAO.getItemDetails(item);
                   System.out.println("itemDTO obj arrived in upadteformCS for Displaying in Update Form");
                     request.setAttribute("itemdetails", obj);
                   System.out.println("calling updatedisplay jsp");
                     rd = request.getRequestDispatcher("updatedisplay.jsp");
                   System.out.println("just below call to updatedisplay jsp ");
                
               }
               //      else
               //  {
               //    System.out.println("in else of itemid!=null from ACS");       
              //   }
              
          //         ArrayList<Integer> itid=AdminDAO.getIds();
          //        System.out.println("ids arrived in update form");
        //         request.setAttribute("id", itid);
        //  rd = request.getRequestDispatcher("uprefreshlist.jsp");
              
          String upid=(String)request.getParameter("upid");
          int id;
          if(upid!=null)
          {
          id=Integer.parseInt(upid);
          
          String upname=(String)request.getParameter("upname");
             String uptype=(String)request.getParameter("uptype");
             String upprice=(String)request.getParameter("upprice");
             String updesc=(String)request.getParameter("updesc");
         String upimage=(String)request.getParameter("upimage");
              if(upname==null || uptype==null || updesc==null || upprice==null ) // && upimage==null 
                {
                    System.out.println("");
                     System.out.println("update form is empty in updateform servlet");
                     System.out.println("Plz fill all entries in update form");
                     return;
          //   rd = request.getRequestDispatcher("seeoption.jsp");
           //         System.out.println("stmt below call to see option ");
                }
          //      else
         //       {
        // int item=Integer.parseInt(itemid);
                 ItemDTO oitem=null;
                System.out.println("Empty condition passed, now creating ItemDTO object in updateform");
                oitem =new ItemDTO();
                oitem.setItemName(upname);
                oitem.setItemDesc(updesc);
                oitem.setItemPrice(Double.parseDouble(upprice));
                oitem.setItemType(uptype);
               oitem.setItemImage(upimage);
                  System.out.println("just above updateIteminfoStore call");
                boolean result=AdminDAO.updateItemIntoStore(oitem,id);
                  System.out.println("just below updateIteminfo store call");
                System.out.println("Result is: "+result);
                 request.setAttribute("result", result);
           System.out.println("Result after updateItemInfoStore is:"+result);
             System.out.println("sending req to addformresponse jsp from updateform servlet");
           rd = request.getRequestDispatcher("addformresponse.jsp");
                }    
          
              }// end of if         
            
//     } 
        }
        catch(Exception e)
        {
            System.out.println("error from Updateform servlet: "+e.toString());
            e.printStackTrace();
        
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
