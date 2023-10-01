package Clases;

/**
 * Añadir imports necesarios para serializar y deserializar,
 * arrays, excepciones y servlet context por los parametros 
 * para obtener la path. 
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
import java.util.List;
import javax.servlet.ServletContext;


/**
 * Creacion clase ExpocicionPerros que extiende la clase Perro
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
    public static void serializacion(List<Perro> darPerros, ServletContext context) throws FileNotFoundException, IOException{
        
        /**
         * Establecemos la ruta 
         */
        
        String ruta="/data/data.txt";// Nombramos la carpeta ya creada y el tipo de archivo que queremos crear -Datos conocidos
        
        /**
         * Creamos la ruta absoluta, utilizamos el contexto del servlet para obtener la path real, añadiendo la ruta relativa
         * Utilizamos la funcion getRealPath ya que la ruta absoluta es diferente en cada equipo por lo que se maneja por variable
         * Tomado de: https://docs.oracle.com/javaee/5/api/javax/servlet/ServletContext.html
         */
        
        String rutaa=context.getRealPath(ruta);
        
        /**
         * Imprimimos la ruta como prueba para obtener la ruta y crear la carpeta en la cual se crea el archivo- Bandera
         */
        
        System.out.println("El archivo serializado se encuentra en: "+rutaa);
        
        /**
         * Verificacion del archivo, en caso de no existir lo crea
         */
        
        File archivo = new File(rutaa);
        
        /**
         * Bloque Try-Catch, inicializamos lo necesario para escribir el archivo, se resume en una linea
         * Basado: http://www.sc.ehu.es/sbweb/fisica/cursoJava/fundamentos/archivos/objetos.htm
         */
        
        try (ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(rutaa))) {
            
            /**
             * Utilizamos el flujo de datos para escribir la lista de perros en el archivo
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

        }
        /**
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
         * Iniciamos un array vacio para guardar la informacion y devolverlo en un return
         */
        
        ArrayList<Perro> darPerros = new ArrayList<>();
        /**
         * Establecemos la ruta 
         */
        
        String ruta="/data/data.txt";// Nombramos la carpeta ya creada y el tipo de archivo que queremos crear -Datos conocidos
        
        /**
         * Creamos la ruta absoluta, utilizamos el contexto del servlet para obtener la path real, añadiendo la ruta relativa
         * Utilizamos la funcion getRealPath ya que la ruta absoluta es diferente en cada equipo por lo que se maneja por variable
         * Tomado de: https://docs.oracle.com/javaee/5/api/javax/servlet/ServletContext.html
         */
        
        String rutaa=context.getRealPath(ruta);
        
         /**
         * Busca el archivo
         */
        File archivo = new File(rutaa);
          
            /**
            * Bloque Try-Catch, inicializamos lo necesario para leer el archivo, se resume en una linea
            * Basado: http://www.sc.ehu.es/sbweb/fisica/cursoJava/fundamentos/archivos/objetos.htm
            */
            
            try (ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(rutaa))) {
                /**
                 * Establecemos el valor del array a lo que se leyo en el archivo que se convierte de tipo ArrayList<Perro>
                 */
                
                darPerros =  (ArrayList<Perro>) leyendoFichero.readObject();
                
                /**
                 * Bandera para indicar que se leyo exitosamente
                 */
                System.out.println("Se leyo el archivo");
                
            } 
            /**
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
    public static ArrayList<Perro> listarPerros(ServletContext context, String perroBuscar, String perroOrden) throws IOException {
         ArrayList<Perro> listaP = new ArrayList<>();
         listaP= deserializacion(context);

         if(perroBuscar != null){
            listaP=buscarPerroNombre(listaP,perroBuscar );
         }
         if(perroOrden!=null){
            listaP = ordenarListaPerros(listaP,perroOrden, context);
         }
         
         return listaP;  
         
    }
    /**
     * Metodo para buscar los perros por el nombre
     * 
     * @param nombre
     * @param context
     * @return 
     */
    public static Perro buscarPerroPorNombre(String nombre, ServletContext context ) throws IOException{
        ArrayList<Perro> listaP = new ArrayList<>();
        
        listaP= listarPerros(context, null, null);

        for(Perro perro : listaP){
            if(perro.getNombre().equals(nombre)){
                return perro;
            }
        }
        return null;
    }
    
    public static ArrayList<Perro> buscarPerroNombre(ArrayList<Perro> listaP,String nombreBuscar){

         ArrayList<Perro> perros= new ArrayList<>();
         
         for (Perro p : listaP) { 
            if(p.getNombre().equals(nombreBuscar)){
                 Perro perro = new Perro(p.getNombre(), p.getRaza(), p.getImagen()  , p.getPuntos(), p.getEdad());
                 perros.add(perro);             
            }
        }
       return perros; 
    }
    
    public static boolean perrosIguales(ArrayList<Perro> listaP, String nombrePerro){
        boolean ban=true; 
        for(Perro perro : listaP){
            if(perro.getNombre().equals(nombrePerro)){
                ban=false;
            }
            
        }
        return ban;
    }
        public static ArrayList<Perro> ordenarListaPerros(ArrayList<Perro> listaP,String orden, ServletContext context) throws IOException{

        ArrayList<Perro> perros= new ArrayList<>();
        /**
         * Agregar la nueva informacion en mayusculas
         */
        for (Perro p : listaP) { 
            Perro perro = new Perro(p.getNombre().toLowerCase(), p.getRaza().toLowerCase(), p.getImagen()  , p.getPuntos(), p.getEdad());        
            perros.add(perro);
        }
        
        switch (orden){
         
            case "nombre":
                Collections.sort(perros,Comparator.comparing( Perro::getNombre ) );
                break;
            case "edad":
                Collections.sort(perros,Comparator.comparing( Perro::getEdad ) );
            break;
            case "raza":
                Collections.sort(perros,Comparator.comparing( Perro::getRaza ) );
            break;
            case "puntos":
                Collections.sort(perros,Comparator.comparing( Perro::getPuntos ) );
            break;        
            
                
        }
        
        serializacion(perros, context);
        return perros;
    }
}
