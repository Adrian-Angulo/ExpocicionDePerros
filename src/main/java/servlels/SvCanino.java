package servlels;
/**
 * Establecemos los import necesarios, como las clases, excepciones, para escribir y leer archivos,
 * para poner los array, para subir el tipo file y las configuraciones del servlet
 */
import Clases.ExpocicionPerros;
import Clases.Perro;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    


    /**
     * Metodo GET para manejo de ventana nodal
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        
        String nombre = request.getParameter("nombre");
        
        String tipo = request.getParameter("tipo");
        
 
        switch(tipo){
        
            case "modal":
                
                Perro perro = ExpocicionPerros.buscarPerroPorNombre(nombre, context); // Implementa la lógica para buscar el perro en tu lista de perros
                
                System.out.println("---------"+perro);
                 
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
                break;
            case "search":
                request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
            case "ordenar":
                request.getRequestDispatcher("index.jsp").forward(request, response);

            break;
    }
    }
    /**
    * Usamos metodo POST para mandar las respuestas del formulario 
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         
        /**
         * Obtenemos el objeto de ServletContext para obtener la informacion del servlet, lo usamos para obtener la PATH
         * Basado: https://www.arquitecturajava.com/java-servletcontext/
         */
        
        ServletContext context = getServletContext();
        
        /**
         * Establecemos el array creado al inicio con la informacion deserializada, mandamos el objeto para que en la 
         * clase exposicion perros obtenga la ruta, ya que al obtener la ruta desde el servlet da error
         */
        
        perros= ExpocicionPerros.listarPerros(context, null, null);
        
        /**
         * Llamamos las variables por el metodo POST
         */
            String nombrePerro=request.getParameter("nombre");
            String razaPerro=request.getParameter("raza");
            /**
             * Manejo del File obtenido por el formulario
             */
            Part fotoPart = request.getPart("imagen");// Se llama la parte del archivo 
            
            /**
             * Creacion de la PATH para guardar la imagen
             */
            
            String imagenPerro=fotoPart.getSubmittedFileName();

            String uploadDirectory = getServletContext().getRealPath("Recursos");

            String filePath=uploadDirectory+ File.separator+ imagenPerro;

            /**
             * Iniciamos flujo para guardar la imagen
             */
            
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
            
            /**
             * Verificamos si el perro existe, en caso de hacerlo no se añade
             */
            
            if(ExpocicionPerros.perrosIguales(perros, nombrePerro)){
                
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
            }
            
            
            
            
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
