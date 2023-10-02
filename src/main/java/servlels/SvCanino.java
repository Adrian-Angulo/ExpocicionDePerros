package servlels;

/**
 * Establecemos los import necesarios, como las clases, excepciones, para
 * escribir y leer archivos, para poner los array, para subir el tipo file y las
 * configuraciones del servlet
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
    ArrayList<Perro> perros = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Metodo GET para manejo de ventana nodal
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * Llamamos variables necesarias para los metodos
         */
        ServletContext context = getServletContext();

        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
       
        /**
         * Switch para analizar que metodo debe realizar
         */
        switch (tipo) {

            case "modal":

                Perro perro = ExpocicionPerros.buscarPerroPorNombre(nombre, context); // Implementa la lógica para buscar el perro en tu lista de perros

                //Verificacion de la variable
                if (perro != null) {
                    // Genera la respuesta HTML con los detalles del perro
                    System.out.print("-------Entra if----");
                    String perroHtml = "<h2>Nombre: " + perro.getNombre() + "</h2>"
                            + "<p>Raza: " + perro.getRaza() + "</p>"
                            + "<p>Puntos: " + perro.getPuntos() + "</p>"
                            + "<p>Edad (meses): " + perro.getEdad() + "</p>"
                            + "<img src='Recursos/" + perro.getImagen() + "' alt='" + perro.getNombre() + "' width='100%'/>";
                    response.setContentType("text/html");
                    response.getWriter().write(perroHtml);

                } else {
                    // Maneja el caso en el que no se encuentra el perro
                    response.setContentType("text/plain");
                    response.getWriter().write("Perro no encontrado");
                }
                break;
            case "search":
                /**
                 * Redireccionamos a la página de listado de videos, la logica
                 * del metodo se implementa en listarPerros
                 */
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "ordenar":
                /**
                 * Redireccionamos a la página de listado de videos, la logica
                 * del metodo se implementa en listarPerros
                 */
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "editar":
                System.out.println("entra----------------------");
                
                perro = ExpocicionPerros.buscarPerroPorNombre(nombre, context); // Implementa la lógica para buscar el perro en tu lista de perros
                if (perro != null) {
                String nperro = perro.getNombre();
                System.out.print(nperro);
                String rperro = perro.getRaza();
                String iperro = perro.getImagen();
                int pperro = perro.getPuntos();
                int eperro = perro.getEdad();
                request.getRequestDispatcher("index.jsp?nombre="+nperro+"&accion=editar"+"&raza="+rperro+"&foto="+iperro+"&puntos="+pperro+"&edad="+eperro).forward(request, response);
               }
               else{
                   request.getRequestDispatcher("index.jsp").forward(request, response);
            }    
                
                
                break;

            case "delete":
                System.out.println("-------------entraaaaa--------------");
                ExpocicionPerros.eliminarPerro(nombre, context);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
                
            case "buscar":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }

    /**
     * Metodo POST para obtener las variables del formulario por debajo
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 /**
         * Obtenemos el objeto de ServletContext para obtener la informacion del
         * servlet, lo usamos para obtener la PATH Basado:
         * https://www.arquitecturajava.com/java-servletcontext/
         */
        ServletContext context = getServletContext();
        String accionSeleccionada = request.getParameter("tipo");
        String nombre = request.getParameter("perroedicion");
           System.out.println("========================");
                System.out.println(accionSeleccionada);
                System.out.println("========================");
        if (accionSeleccionada != null && accionSeleccionada.equals("editar")) {
                
           
             
                Perro p = ExpocicionPerros.buscarPerroPorNombre(nombre, context);

                if (p != null) {
                    // Recuperar los valores actualizados desde el formulario
                    String nombrePerro = request.getParameter("nombre");
                    String razaPerro = request.getParameter("raza");
                    String imagenPerro = cargarImagen(request, response);
                    int puntosPerro = Integer.parseInt(request.getParameter("puntos"));
                    int edadPerro = Integer.parseInt(request.getParameter("edad"));

                    // Actualiza los atributos del perro
                    p.setNombre(nombrePerro);
                    p.setEdad(edadPerro);
                    p.setImagen(imagenPerro);
                    p.setPuntos(puntosPerro);
                    p.setRaza(razaPerro);

                    // Llama al método para actualizar el perro
                    ExpocicionPerros.modificarPerro(p, context);

                    // Redirecciona a la página de listado de perros 
                    response.sendRedirect("index.jsp"); // Usamos sendRedirect para evitar problemas de recarga de página
                } else {
                    // Manejar el caso en el que no se encuentra el perro
                    response.getWriter().println("No se encontró el perro a actualizar.");
                }
        } else if (!accionSeleccionada.equals("editar")) {

                // Obtener la lista de perros existente desde el contexto
                ArrayList<Perro> perros = ExpocicionPerros.listarPerros(context, null, null, null);

                /**
                 * Llamamos las variables por el método POST
                 */
                String nombrePerro = request.getParameter("nombre");
                String razaPerro = request.getParameter("raza");
                String imagenPerro = cargarImagen(request, response);
                int puntosPerro = Integer.parseInt(request.getParameter("puntos"));
                int edadPerro = Integer.parseInt(request.getParameter("edad"));

                /**
                 * Verificamos si el perro existe, en caso de hacerlo no se
                 * añade
                 */
                if (!ExpocicionPerros.perrosIguales(perros, nombrePerro)) {
                    // Creamos un objeto Perro con los datos del formulario
                    Perro perro = new Perro(nombrePerro, razaPerro, imagenPerro, puntosPerro, edadPerro);

                    // Agregamos el perro a la lista de perros
                    perros.add(perro);

                    // Serializamos la lista de perros
                    ExpocicionPerros.serializacion(perros, context);

                    // Establecemos la lista de perros de la exposición como un atributo de solicitud
                    request.setAttribute("perros", perros);

                    // Redireccionamos a la página de listado de perros
                    response.sendRedirect("index.jsp");
                } else {
                    // Manejar el caso en el que el perro ya existe
                    response.getWriter().println("El perro ya existe en la lista.");
                }
            } else {
                // Manejar otros valores de "accionSeleccionada" si es necesario
                response.getWriter().println("Acción no válida.");
            }
        }

            


    

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public String cargarImagen(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /**
         * Manejo del File obtenido por el formulario
         */
        Part fotoPart = request.getPart("imagen");// Se llama la parte del archivo 

        /**
         * Creacion de la PATH para guardar la imagen
         */
        String imagenPerro = fotoPart.getSubmittedFileName();

        String uploadDirectory = getServletContext().getRealPath("Recursos");

        String filePath = uploadDirectory + File.separator + imagenPerro;

        /**
         * Iniciamos flujo para guardar la imagen
         */
        try (InputStream input = fotoPart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                /**
                 * Leemos el contenido del archivo del flujo de entrada y
                 * escribe en el flujo de salida
                 */
                output.write(buffer, 0, length);
            }
        }

        return imagenPerro;
    }

}
