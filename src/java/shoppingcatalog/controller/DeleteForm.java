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

/**
 *
 * @author hp
 */
public class DeleteForm extends HttpServlet {

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
      
        String dpid=(String)request.getParameter("dpid");
          int id;
          if(dpid!=null)
          {
          id=Integer.parseInt(dpid);
      
                  System.out.println("just above removeItem call");
                boolean result=AdminDAO.removeItem(id);
                  System.out.println("just below removeItem call");
                System.out.println("Result is: "+result);
                 request.setAttribute("result", result);
           System.out.println("Result after updateItemInfoStore is:"+result);
             System.out.println("sending req to addformresponse jsp from deleteform servlet");
           rd = request.getRequestDispatcher("addformresponse.jsp");
                }
          // String v1=request.getParameter("v1");
           ArrayList<Integer> itemid=AdminDAO.getIds();
                   System.out.println("ids arrived in Delete form");
                   request.setAttribute("id", itemid);
                   //if(v1.equals("1"))
                   //    rd = request.getRequestDispatcher("uprefreshlist.jsp");
                 //  else
           rd = request.getRequestDispatcher("refreshlist.jsp");
          
              }// end of if         
            
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
