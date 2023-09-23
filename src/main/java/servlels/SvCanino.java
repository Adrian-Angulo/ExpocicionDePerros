/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlels;

import Clases.ExpocicionPerros;
import static Clases.ExpocicionPerros.deserializacion;
import Clases.Perro;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADRIAN CASTILLO
 */
@WebServlet(name = "SvCanino", urlPatterns = {"/SvCanino"})
public class SvCanino extends HttpServlet {
     //String ruta = "C:\\Users\\ADRIAN CASTILLO\\Desktop\\Expocicion canina\\ExpocicionDePerros\\src\\main\\java\\data\\data.bin";
     
     ExpocicionPerros exposicionPerros = new ExpocicionPerros();
     ArrayList<Perro> perros= new ArrayList<>();
    
     

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               

           
                ServletContext context = getServletContext();
                perros= deserializacion(context);
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
            exposicionPerros.serializacion(perros, context);
            
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
             // Establecer la lista de perros de la exposicion como un atributo de solicitud
                request.setAttribute("perros", exposicionPerros.getDarPerros()); 

            // Redireccionar a la página de listado de videos
                request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    

    
}
