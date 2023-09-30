package servlels;
/**
 * Establecemos los import necesarios, como las clases, excepciones, para escribir y leer archivos,
 * para poner los array, para subir el tipo file y las configuraciones del servlet
 */
import Clases.ExpocicionPerros;
import static Clases.ExpocicionPerros.deserializacion;
import Clases.Perro;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet para recibir la informacion del index.jsp 
 * 
 * @author Juan Calpa, María Casanova y Adrian Castillo
 */

@MultipartConfig //Para manejar las solicitudes HTTP para trabajar con la respuesta File del formulario - Evita el error de miltupartes
@WebServlet(name = "SvCanino", urlPatterns = {"/SvCanino"})

public class SvCanino extends HttpServlet {
    /**
     * Instanciamos la clase exposicionPerros, para usar sus metodos 
     */
    ExpocicionPerros exposicionPerros = new ExpocicionPerros();
    /**
     * Creamos un array donde contenerá la informacion de los perros
     */
    ArrayList<Perro> perros= new ArrayList<>();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    private Perro buscarPerroPorNombre(String nombre){
        ServletContext context = getServletContext();
        perros= deserializacion(context);
        for(Perro perro : perros){
            if(perro.getNombre().equals(nombre)){
                System.out.print("-----"+perro.getNombre());
                return perro;
        }
        }
        return null;
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        Perro perro = buscarPerroPorNombre(nombre); // Implementa la lógica para buscar el perro en tu lista de perros
        System.out.println(perro);
        if (perro != null) {
            // Genera la respuesta HTML con los detalles del perro
            System.out.print("-------Entra if----");
            String perroHtml = "<h2>Nombre: " + perro.getNombre() + "</h2>" +
                               "<p>Raza: " + perro.getRaza() + "</p>" +
                               "<p>Puntos: " + perro.getPuntos() + "</p>" +
                               "<p>Edad (meses): " + perro.getEdad() + "</p>" +
                               "<img src='Recursos/" + perro.getImagen() + "' alt='" + perro.getNombre() + "' width='100%'/>";
            response.setContentType("text/html");
            response.getWriter().write(perroHtml);
        } else {
            // Maneja el caso en el que no se encuentra el perro
            response.setContentType("text/plain");
            response.getWriter().write("Perro no encontrado");
        }
        
    }
    /**
    * Usamos metodo POST para mandar las respuestas del formulario 
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         
        /**
         * Declaramos la variable imagenPerro como null para no obtener error al momento de manejar el file
         * Puesto que entra en un if y puede la variable no ser declarada
         */
        
  
        
        /**
         * Obtenemos el objeto de ServletContext para obtener la informacion del servlet, lo usamos para obtener la PATH
         * Basado: https://www.arquitecturajava.com/java-servletcontext/
         */
        
        ServletContext context = getServletContext();
        
        /**
         * Establecemos el array creado al inicio con la informacion deserializada, mandamos el objeto para que en la 
         * clase exposicion perros obtenga la ruta, ya que al obtener la ruta desde el servlet da error
         */
        
        perros= deserializacion(context);
        
        /**
         * Llamamos las variables por el metodo POST
         */
            String nombrePerro=request.getParameter("nombre");
            String razaPerro=request.getParameter("raza");
            /**
             * Manejo del File obtenido por el formulario
             */
            Part fotoPart = request.getPart("imagen");// Se llama la parte del archivo 
            System.out.println("--------------");
            System.out.println(fotoPart);
            /**
             * Usamos un IF para verificar si el archivo es valido
             */
            String fileName=fotoPart.getSubmittedFileName();
            System.out.println(fileName);
            String uploadDirectory = getServletContext().getRealPath("Recursos");
            System.out.println(uploadDirectory);
            String filePath=uploadDirectory+ File.separator+ fileName;
            System.out.println(filePath);
            System.out.println("--------------");
            try (InputStream input = fotoPart.getInputStream();
                OutputStream output = new FileOutputStream(filePath)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = input.read(buffer)) >0) {
                       /**
                        * Leemos el contenido del archivo del flujo de entrada y escribe en el flujo de salida
                        */
                        output.write(buffer, 0, length);
                    }
                }
            
            //Continua pasando las variables por el metodo POST
            int  puntosPerro = Integer.parseInt(request.getParameter("puntos"));
            int edadPerro=Integer.parseInt(request.getParameter("edad"));
            String imagenPerro=null;
            /**
             * Creamos un objeto Perro con los datos del formulario
             */
            
            Perro perro = new Perro(nombrePerro, razaPerro, imagenPerro  , puntosPerro, edadPerro);
            
            /**
             * Agregamos el perro al array
             */
           
            perros.add(perro);
            
            /**
             * Agregamos el perro a la lista de la exposicion de perros
             */ 
            
            exposicionPerros.setDarPerros(perros);
            
            /**
             * Serializamos la lista de perros
             */
            
            exposicionPerros.serializacion(perros, context);
            
            /**
             * Establecemos la lista de perros de la exposicion como un atributo de solicitud
             */
             
            request.setAttribute("perros", exposicionPerros.getDarPerros()); 
            
            /**
             * Redireccionamos a la página de listado de videos
             */
           
            request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    

    
}
