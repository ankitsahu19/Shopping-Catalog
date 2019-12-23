/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.StoreDAO;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;

/**
 *
 * @author hp
 */
public class StoreControllerServlet extends HttpServlet {

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
             RequestDispatcher rd = null;
            if(username==null)
            {
                session.invalidate();
               response.sendRedirect("accessdenied.html");
               return;
            }
            String itemId   =(String) (request.getParameter("item_id"));
            String itemType = (String) request.getParameter("item_type");
            String removeitemid=(String)request.getParameter("removeitemwithid");// for removing item from cart.
            System.out.println("inside controller itemtype: "+itemType);
            System.out.println("Item id is "+ itemId );
            System.out.println("id to removw is : "+removeitemid);
            try
            { 
                if(removeitemid!=null ) //&& itemType != null && itemId != null
                {
                   
                    Enumeration en = session.getAttributeNames();
               
                while(en.hasMoreElements())
                {
                  String obj  = (String)  en.nextElement();
                  if(obj.equals("username")==false)
                  {
                     ItemDTO item = (ItemDTO)  session.getAttribute(obj);
                    if(item.getItemId()==Integer.parseInt(removeitemid))
                    {
                     session.removeAttribute(obj);
                    
                    }
                  }
                }
            
                
             
                    System.out.println("inside removeitemid!=null block");
             rd = request.getRequestDispatcher("placeorder.jsp");
            
                }
                if(itemId == null && itemType == null && removeitemid==null)
                {
                    ArrayList<String> types   = StoreDAO.getItemTypes();
                    request.setAttribute("types",types);
                    rd = request.getRequestDispatcher("seestore.jsp"); 
                }
                else if (itemId == null && itemType != null)
                {
                    List<ItemInfoDTO>iteminfo = StoreDAO.getItemsByType(itemType);
                    request.setAttribute("iteminfo",iteminfo);
                    rd = request.getRequestDispatcher("showitemsbytype.jsp");
                }
                else if(itemId != null)
                { 
                    int itemid = Integer.parseInt(itemId) ;
                      ItemDTO item   = StoreDAO.getItemDetails(itemid) ;
                      request.setAttribute("item", item);
                    rd = request.getRequestDispatcher("showitemdetails.jsp");
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
