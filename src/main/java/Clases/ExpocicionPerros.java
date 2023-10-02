package Clases;

/**
 * Añadir imports necesarios para serializar y deserializar, arrays, excepciones
 * y servlet context por los parametros para obtener la path.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;

/**
 * Creacion clase ExpocicionPerros que extiende la clase Perro
 *
 * @author Juan Calpa, María Casanova y Adrian Castillo
 */
public class ExpocicionPerros extends Perro {

    /**
     * lista que almacena los perros de la clase perro
     */
    ArrayList<Perro> darPerros = new ArrayList<>();

    /**
     * Constructor vacio
     */
    public ExpocicionPerros() {

    }

    /**
     * Metodo que se encarga de ordenar perros por raza
     */
    public void ordenarPorRaza() {

    }

    /**
     * Metodo que se encarga de ordenar perros por nombre
     */
    public void ordenarPorNombre() {

    }

    /**
     * Metodo que se encarga de ordenar perros por puntos
     */
    public void ordenarPorPuntos() {

    }

    /**
     * Metodo que se encarga de ordenar perros por edad
     */
    public void ordenarPorEdad() {

    }

    /**
     * Metodo que se encarga de buscar un perro ingresando el nombre
     *
     * @param nombre
     * @return
     */
    public int buscarPerro(String nombre) {

        return 0;
    }

    /**
     * Metodo que se encarga de buscarBinario por nombre
     *
     * @param nombre
     * @return
     */
    public int buscarBinarioPorNombre(String nombre) {

        return 0;
    }

    /**
     * Metodo que se encarga de agregar un perro
     *
     * @param nombreP
     * @param razaP
     * @param imagenP
     * @param puntosP
     * @param edadP
     * @return
     */
    public boolean agregarPerro(String nombreP, String razaP, String imagenP, int puntosP, int edadP) {

        return false;
    }

    /**
     * Metodo que se encarga de verificar la invariantes
     */
    private void verificarInvariante() {

    }

    /**
     * Metodo que se encarga de buscar perros con nombres repetidos
     *
     * @return
     */
    private boolean buscarPerrosConNombresRepetidos() {

        return false;
    }

    /**
     * Metodo para buscar el mayor puntaje de un peroo
     *
     * @return
     */
    public int buscarPerroMyorPuntaje() {

        return 0;
    }

    /**
     * Metodo que se utiliza para buscar el menor puntaje de un perro
     *
     * @return
     */
    public int buscarPerroMenorPuntaje() {

        return 0;
    }

    /**
     * Metodo que se encarga de buscar la edad del perro mayor
     *
     * @return
     */
    public int buscarPerroMayorEdad() {

        return 0;
    }

    /**
     * Metodo para dar los perros
     *
     * @return
     */
    public ArrayList<Perro> getDarPerros() {
        return darPerros;
    }

    /**
     * Metodo para establecer el valor de los perros
     *
     * @param darPerros
     */
    public void setDarPerros(ArrayList<Perro> darPerros) {
        this.darPerros = darPerros;
    }

    /**
     * Metodo para serializar el array y manejar persistencia
     *
     * @param darPerros
     * @param context
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void serializacion(List<Perro> darPerros, ServletContext context) throws FileNotFoundException, IOException {

        /**
         * Establecemos la ruta
         */
        String ruta = "/data/data.txt";// Nombramos la carpeta ya creada y el tipo de archivo que queremos crear -Datos conocidos

        /**
         * Creamos la ruta absoluta, utilizamos el contexto del servlet para
         * obtener la path real, añadiendo la ruta relativa Utilizamos la
         * funcion getRealPath ya que la ruta absoluta es diferente en cada
         * equipo por lo que se maneja por variable Tomado de:
         * https://docs.oracle.com/javaee/5/api/javax/servlet/ServletContext.html
         */
        String rutaa = context.getRealPath(ruta);

        /**
         * Imprimimos la ruta como prueba para obtener la ruta y crear la
         * carpeta en la cual se crea el archivo- Bandera
         */
        System.out.println("El archivo serializado se encuentra en: " + rutaa);

        /**
         * Verificacion del archivo, en caso de no existir lo crea
         */
        File archivo = new File(rutaa);

        /**
         * Bloque Try-Catch, inicializamos lo necesario para escribir el
         * archivo, se resume en una linea Basado:
         * http://www.sc.ehu.es/sbweb/fisica/cursoJava/fundamentos/archivos/objetos.htm
         */
        try (ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(rutaa))) {

            /**
             * Utilizamos el flujo de datos para escribir la lista de perros en
             * el archivo
             */
            escribiendoFichero.writeObject(darPerros);

            /**
             * Cerrar los recursos inicializados
             */
            escribiendoFichero.close();
            /**
             * Bandera para indicar que se escribio el archivo
             */
            System.out.println("Se escribio el archivo");

        } /**
         * Atrapamos las posibles excepciones
         */
        catch (FileNotFoundException ex) {

            System.out.println("No se encontro el archivo");

        } catch (IOException ex) {

            System.out.println("Error al escribir el archivo");
        }
    }

    /**
     * Metodo para deserializar el txt y manejar persistencia
     *
     * @param context
     * @return
     */
    public static ArrayList<Perro> deserializacion(ServletContext context) {
        /**
         * Iniciamos un array vacio para guardar la informacion y devolverlo en
         * un return
         */

        ArrayList<Perro> darPerros = new ArrayList<>();
        /**
         * Establecemos la ruta
         */

        String ruta = "/data/data.txt";// Nombramos la carpeta ya creada y el tipo de archivo que queremos crear -Datos conocidos

        /**
         * Creamos la ruta absoluta, utilizamos el contexto del servlet para
         * obtener la path real, añadiendo la ruta relativa Utilizamos la
         * funcion getRealPath ya que la ruta absoluta es diferente en cada
         * equipo por lo que se maneja por variable Tomado de:
         * https://docs.oracle.com/javaee/5/api/javax/servlet/ServletContext.html
         */
        String rutaa = context.getRealPath(ruta);

        /**
         * Busca el archivo
         */
        File archivo = new File(rutaa);

        /**
         * Bloque Try-Catch, inicializamos lo necesario para leer el archivo, se
         * resume en una linea Basado:
         * http://www.sc.ehu.es/sbweb/fisica/cursoJava/fundamentos/archivos/objetos.htm
         */
        try (ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(rutaa))) {
            /**
             * Establecemos el valor del array a lo que se leyo en el archivo
             * que se convierte de tipo ArrayList<Perro>
             */

            darPerros = (ArrayList<Perro>) leyendoFichero.readObject();

            /**
             * Bandera para indicar que se leyo exitosamente
             */
            System.out.println("Se leyo el archivo");

        } /**
         * Atrapamos las posibles excepciones
         */
        catch (FileNotFoundException ex) {

            System.out.println("No se encontró el archivo");
        } catch (IOException ex) {

            System.out.println("Error al leer el archivo");
        } catch (ClassNotFoundException ex) {

            System.out.println("Clase no encontrada al deserializar");
        }

        /**
         * Devolvemos el array, para establecerlo en el array del servlet
         */
        return darPerros;
    }

    /**
     * Metodo para listar los perros
     *
     * @param context
     * @return
     */
    public static ArrayList<Perro> listarPerros(ServletContext context, String perroBuscar, String perroOrden, String indice) throws IOException {

        ArrayList<Perro> listaP = new ArrayList<>();// Array vacio para llenar la informacion

        listaP = deserializacion(context);//llenamos el array con la informacion del txt
        /**
         * If para saber a que metodo llamar
         */
        if (perroBuscar != null) {
            listaP = buscarPerroNombre(listaP, perroBuscar);// Llamar metodo para buscar x nombre
        }
        if (perroOrden != null) {
            listaP = ordenarListaPerros(listaP, perroOrden, context); //Llamar metodo para ordenar
        } if (indice != null){
            switch (indice){
                case "mayorPun":
                    listaP = buscarPerroMayorPuntaje(listaP);
                    break;
                case "menorPun":
                    listaP = buscarPerroMenorPuntaje(listaP);
                    break;
                case "mayor edad":
                    listaP = buscarPerroMasViejo(listaP);
                    break;
            }
                
            
            listaP = buscarPerroMayorPuntaje (listaP);
        }

        return listaP;

    }

    /**
     * Metodo para ubicar los perros por el nombre Utilizado en la ventana modal
     *
     * @param nombre
     * @param context
     * @return
     */
    public static Perro buscarPerroPorNombre(String nombre, ServletContext context) throws IOException {
        ArrayList<Perro> listaP = new ArrayList<>();// Array vacio para llenar la informacion

        listaP = listarPerros(context, null, null, null); //Llenamos el array con la informacion para poder hacer la busqueda
        /**
         * Ciclo for para encontrar las coincidencias
         */
        for (Perro perro : listaP) {
            if (perro.getNombre().equals(nombre)) {
                return perro;
            }
        }
        return null;
    }

    /**
     *
     * Metodo para buscar los perros por el nombre El mismo llena un array solo
     * con el perro encontrado
     *
     * @param listaP
     * @param nombreBuscar
     * @return
     */
    public static ArrayList<Perro> buscarPerroNombre(ArrayList<Perro> listaP, String nombreBuscar) {

        ArrayList<Perro> perros = new ArrayList<>();// Array vacio para llenar la informacion
        /**
         * Ciclo for para encontrar las coincidencias
         */
        for (Perro p : listaP) {
            if (p.getNombre().equals(nombreBuscar)) {
                /**
                 * Crear un nuevo objeto perro para añadirlo al array nuevo
                 */
                Perro perro = new Perro(p.getNombre(), p.getRaza(), p.getImagen(), p.getPuntos(), p.getEdad());
                perros.add(perro);
            }
        }
        return perros;
    }

    /**
     *
     * Metodo para comparar si existen dos perros iguales
     *
     * @param listaP
     * @param nombrePerro
     * @return
     */
    public static boolean perrosIguales(ArrayList<Perro> listaP, String nombrePerro) {
        boolean ban = false; // Bandera boolean para indicar si hay coincidencias
        /**
         * Ciclo for para encontrar las coincidencias
         */
        for (Perro perro : listaP) {
            if (perro.getNombre().equals(nombrePerro)) {
                ban = true;// Si encuentra una coincidencia, devuelve true
            }

        }
        return ban; // Si no encuentra coincidencias, devuelve false
    }
    /**
     * 
     * Metodo que ordena la lista de perros segun nombre, raza, edad, puntos.
     * 
     * @param listaP
     * @param orden
     * @param context
     * @return
     * @throws IOException 
     */
    public static ArrayList<Perro> ordenarListaPerros(ArrayList<Perro> listaP, String orden, ServletContext context) throws IOException {

        ArrayList<Perro> perros = new ArrayList<>();// Array vacio para llenar la informacion

        /**
         * Agregar la nueva informacion en minusculas,se realiza esto para que
         * el metodo no llegue a priorizar las mayusculas
         */
        for (Perro p : listaP) {

            Perro perro = new Perro(p.getNombre().toLowerCase(), p.getRaza().toLowerCase(), p.getImagen(), p.getPuntos(), p.getEdad());  //Funcion toLowerCase para pasarlas a minusculas      

            perros.add(perro);// Crear cada objeto en minusculas
        }
        /**
         * Switch para analizar con que parametro se organizará la informacion
         */
        switch (orden) {
            /**
             * En cada caso se llama la funcion SORT enviando el metodo por el
             * cual se comparará BASADO:
             * https://www.digitalocean.com/community/tutorials/java-collections-sort
             */
            case "nombre":
                Collections.sort(perros, Comparator.comparing(Perro::getNombre));
                break;
            case "edad":
                Collections.sort(perros, Comparator.comparing(Perro::getEdad));
                break;
            case "raza":
                Collections.sort(perros, Comparator.comparing(Perro::getRaza));
                break;
            case "puntos":
                Collections.sort(perros, Comparator.comparing(Perro::getPuntos));
                break;

        }

        serializacion(perros, context);// Llenamos la informacion ordenada en el txt

        return perros;
    }
    
    /**
     * 
     * Metodo para eliminar el perro teniendo en cuenta el identificador unico
     * 
     * @param nombreEliminar
     * @param context
     * @throws IOException 
     */
    
    public static void eliminarPerro(String nombreEliminar, ServletContext context) throws IOException {

        ArrayList<Perro> listaP = listarPerros(context, null, null, null);// Array con los perros que hay en el txt
        
        Iterator<Perro> iterator = listaP.iterator();//Crea un iterador para recorrer la lista de perros
        /**
         * Inicia un bucle para recorrer la lista de perros
         */
        while (iterator.hasNext()) {
            Perro p = iterator.next();// Obtiene el próximo perro de la lista.
            /**
             * Comprueba si el nombre del perro coincide con el nombre a eliminar
             */
            if (p.getNombre().equals(nombreEliminar)) {
                System.out.println("------------------- entraiffff");
                iterator.remove(); // Elimina el perro de la lista
                System.out.println(listaP);
            }
        }
        /**
         * Guardamos la nueva informacion
         */
        serializacion(listaP, context);// Llenamos la informacion ordenada en el txt
    }
    
    /**
     * 
     * Metodo para modificar un perro obteniendo el objeto
     * 
     * @param pe
     * @param context
     * @throws IOException 
     */

    public static void modificarPerro(Perro pe, ServletContext context) throws IOException {
   
    ArrayList<Perro> listaP = listarPerros(context, null, null, null); // Obtiene la lista de perros del contexto de la aplicación
    /**
     * Bucle for para recorrer el arraylist
     */
    for (int i = 0; i < listaP.size(); i++) {
        /**
         * If para encontrar las coincidencias
         */
        if (listaP.get(i).getNombre().equals(pe.getNombre())) {
            // Reemplaza el perro existente con el perro actualizado
            listaP.set(i, pe);
            break; // Termina el bucle, ya que se encontró el perro
        }
    }

    // Actualiza la lista de perros en el contexto de la aplicación
    serializacion(listaP, context);
        
    }
    /**
     * 
     * Metodo para buscar el perro con mayor puntaje
     * 
     * @param listaP
     * @return 
     */
    public static ArrayList<Perro> buscarPerroMayorPuntaje(ArrayList<Perro> listaP) {

        ArrayList<Perro> perros = new ArrayList<>();// Array vacio para llenar la informacion
        
        /**
        * Se llama la funcion SORT enviando el metodo puntos y reversed para que los organice en manera descendente.
        *  BASADO: https://www.digitalocean.com/community/tutorials/java-collections-sort
        */
        
        Collections.sort(listaP, Comparator.comparing(Perro::getPuntos).reversed());
        /**
         * Ciclo for para enviar el primer perro 
         */
        for (Perro p : listaP) {
                Perro perro = new Perro(p.getNombre(), p.getRaza(), p.getImagen(), p.getPuntos(), p.getEdad());
                perros.add(perro);
                break;
            
        }
        return perros;
    }
    
    /**
     * 
     * Metodo para buscar el perro con menor puntaje
     * 
     * @param listaP
     * @return 
     */
    
    public static ArrayList<Perro> buscarPerroMenorPuntaje(ArrayList<Perro> listaP) {

        ArrayList<Perro> perros = new ArrayList<>();// Array vacio para llenar la informacion
        /**
        * Se llama la funcion SORT enviando el metodo puntos.
        *  BASADO: https://www.digitalocean.com/community/tutorials/java-collections-sort
        */
        Collections.sort(listaP, Comparator.comparing(Perro::getPuntos));
        /**
         * Ciclo for para enviar el primer perro 
         */
        for (Perro p : listaP) {
                Perro perro = new Perro(p.getNombre(), p.getRaza(), p.getImagen(), p.getPuntos(), p.getEdad());
                perros.add(perro);
                break;
            
        }
        return perros;
    }
    
    /**
     * 
     * Metodo para buscar el perro mas viejo
     * 
     * @param listaP
     * @return 
     */
    
    public static ArrayList<Perro> buscarPerroMasViejo(ArrayList<Perro> listaP) {

        ArrayList<Perro> perros = new ArrayList<>();// Array vacio para llenar la informacion
        /**
        * Se llama la funcion SORT enviando el metodo puntos y reversed para que los organice en manera descendente.
        *  BASADO: https://www.digitalocean.com/community/tutorials/java-collections-sort
        */
        Collections.sort(listaP, Comparator.comparing(Perro::getEdad).reversed());
        /**
         * Ciclo for para enviar el primer perro 
         */
        for (Perro p : listaP) {
                Perro perro = new Perro(p.getNombre(), p.getRaza(), p.getImagen(), p.getPuntos(), p.getEdad());
                perros.add(perro);
                break;
            
        }
        return perros;
    }
}