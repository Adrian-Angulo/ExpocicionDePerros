/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlels;

import Clases.ExpocicionPerros;
import Clases.Perro;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
     String ruta = "C:\\Users\\ADRIAN CASTILLO\\Desktop\\Expocicion canina\\ExpocicionDePerros\\src\\main\\java\\data\\data.bin";
     ExpocicionPerros exposicionPerros = new ExpocicionPerros();
     ArrayList<Perro> perros= new ArrayList<>(); //poner deserializacion
     
     
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
            
            // Agregar perro a la lista de la exposicion de perros
            exposicionPerros.setDarPerros(perros);
            
            // serializar lista de perros
            serializacion(perros, ruta);
            
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
             // Establecer la lista de perros de la exposicion como un atributo de solicitud
                request.setAttribute("perros", exposicionPerros.getDarPerros()); 

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
    
    /**
     * Este método permite serializar una lista de perros en un archivo.
     *
     * @param darPerros la lista de perros que se desea serializar.
     * @param ruta la ruta del archivo donde se desea guardar la lista serializada.
     */
    public void serializacion(List<Perro> darPerros, String ruta){
        //Creamos un flujo de salida de objetos
        try(ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(ruta))) {
            
            //Utilizamos el flujo de datos para escribir la lista de perros en el archivo
            escribiendoFichero.writeObject(darPerros);
            System.out.println("Se escribio el archivo");
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo");
        }
    }    
}
