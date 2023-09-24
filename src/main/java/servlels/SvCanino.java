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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        String imagenPerro=null;
        
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
            Part filePart = request.getPart("imagen");// Se llama la parte del archivo 
            /**
             * Usamos un IF para verificar si el archivo es valido
             */
            if (filePart != null) {
                /**
                 * Llamamos la ruta real en el sistema de archivos del servidor donde se guardarán los archivos cargados
                 * Basado: https://www.tabnine.com/code/java/methods/javax.servlet.http.Part/getSubmittedFileName
                 */ 
                String ruta1 = context.getRealPath("/Recursos");
                /**
                 * Buscamos el nombre del archivo cargado
                 */
                String nombreA = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                /**
                 * Creamos la ruta completa del archivo en el sistema de archivos del servidor
                 * Basado: https://lineadecodigo.com/java/crear-la-ruta-de-un-fichero-con-java/
                 */
                String rutaCompleta = ruta1 + File.separator + nombreA;
                /**
                 * Guardamos el nombre del archivo en la variable imagenPerro
                 */
                imagenPerro = filePart.getSubmittedFileName();
                 System.out.print(imagenPerro);
                /**
                 * Se obtiene el contenido del archivo - Flujo de entrada
                 */
                
                InputStream fileContent = filePart.getInputStream();

                /**
                 * Guardamos el archivo en el sistema de archivos del servidor - Flujo de salida
                 * Basado: https://www.tabnine.com/code/java/methods/java.io.OutputStream/write http://dis.um.es/~lopezquesada/documentos/IES_1213/IAW/curso/UT3/ActividadesAlumnos/17/index.html
                 * https://jorgesanchez.net/manuales/viejos/fpr/fpr1009.pdf
                 */
                
                try (OutputStream os = new FileOutputStream(rutaCompleta)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                       /**
                        * Leemos el contenido del archivo del flujo de entrada y escribe en el flujo de salida
                        */
                        os.write(buffer, 0, bytesRead);
                    }
                }

                /**
                 * Cerramos los recursos
                 */
                
                fileContent.close();
            } else {
                /**
                 * Bandera por si no se encontró ninguna parte del archivo
                 */
                response.getWriter().println("No se ha seleccionado ninguna imagen.");

            }
            
            //Continua pasando las variables por el metodo POST
            int  puntosPerro = Integer.parseInt(request.getParameter("puntos"));
            int edadPerro=Integer.parseInt(request.getParameter("edad"));
             
            /**
             * Creamos un objeto Perro con los datos del formulario
             */
            
            Perro perro = new Perro(nombrePerro, razaPerro, imagenPerro, puntosPerro, edadPerro);
            
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
