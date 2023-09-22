/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlels;

import Clases.Perro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADRIAN CASTILLO
 */
@WebServlet(name = "SvCanino", urlPatterns = {"/SvCanino"})
public class SvCanino extends HttpServlet {
    
     ArrayList<Perro> perros= new ArrayList<>();
     
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

    }


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
        /**
         * Llamamos las variables por el metodo POST
         */
            String nombrePerro=request.getParameter("nombre");
            String razaPerro=request.getParameter("raza");
            String imagenPerro=request.getParameter("imagen");
            int  puntosPerro = Integer.parseInt(request.getParameter("puntos"));
            int edadPerro=Integer.parseInt(request.getParameter("edad"));
             
            System.out.println("nombre perro:"+nombrePerro);
            System.out.println("raza perro:"+razaPerro);
            System.out.println("imagen perro:"+imagenPerro);
            System.out.println("puntos perro:"+puntosPerro);
            System.out.println("edad perro:"+edadPerro);
            // Crear un objeto Perro con los datos del formulario
            Perro perro = new Perro(nombrePerro, razaPerro, imagenPerro, puntosPerro, edadPerro);

            // Agregar el perro a la lista
            perros.add(perro);
            
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
             // Establecer la lista como un atributo de solicitud
                request.setAttribute("perros", perros);

            // Redireccionar a la página de listado de videos
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
