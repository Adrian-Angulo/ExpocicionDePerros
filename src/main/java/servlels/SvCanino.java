/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlels;

import Clases.ExpocicionPerros;
import static Clases.ExpocicionPerros.deserializacion;
import Clases.Perro;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ADRIAN CASTILLO
 */
@MultipartConfig
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
               String imagenPerro=null;

           
                ServletContext context = getServletContext();
                perros= deserializacion(context);
                /**
         * Llamamos las variables por el metodo POST
         */
            String nombrePerro=request.getParameter("nombre");
            String razaPerro=request.getParameter("raza");
            Part filePart = request.getPart("imagen");
            if (filePart != null) {
                // Obtiene la ruta del archivo
                String uploadPath = context.getRealPath("/Recursos");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                String filePath = uploadPath + File.separator + fileName;
                 imagenPerro = filePart.getSubmittedFileName();
                System.out.println(imagenPerro);
                // Obtener el contenido del archivo
                InputStream fileContent = filePart.getInputStream();

                // Crear un flujo de salida para guardar el archivo
                try (OutputStream os = new FileOutputStream(filePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                }

                // Cerrar el flujo de entrada
                fileContent.close();
            } else {
                response.getWriter().println("No se ha seleccionado ninguna imagen.");
            }
            
           
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
