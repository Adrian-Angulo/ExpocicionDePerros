package Clases;
/**
 * Import necesitado para serializar
 */
import java.io.Serializable;

/**
 * Esta es la clase perro que tiene todos los atributos de un perro
 * Implementa Serializable -> Permite la serializacion de los objetos
 * @author Juan Calpa, María Casanova y Adrian Castillo
 */

public class Perro implements Serializable{
    
    /**
     * Atributos privados del perro
     */
    
    private String nombre;
    private String raza;
    private String imagen;
    private int puntos;
    private int edad;



    /**
     * Constructor Perro
     * @param nombreP
     * @param razaP
     * @param imagenP
     * @param puntosP
     * @param edadP
     */
    
    public Perro(String nombreP, String razaP, String imagenP, int puntosP, int edadP) {
        this.nombre = nombreP;
        this.raza = razaP;
        this.imagen = imagenP;
        this.puntos = puntosP;
        this.edad = edadP;
    }
    /**
     * Constructor Vacio
     */
    public Perro() {
    }
    /**
     * getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * getRaza
     * @return raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * getImagen
     * @return imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * getPuntos
     * @return puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * getEdad
     * @return edad
     */
    public int getEdad() {
        return edad;
    }
    /**
     * compararPorNombre
     * @param p
     * @return 0
     */
    int compararPorNombre( Perro p){
        return 0;
    }
    /**
     * comprarPorRaza
     * @param p
     * @return 0
     */
    int comprarPorRaza(Perro p){
        return 0;
    }
    /**
     * comprarPorPuntos
     * @param p
     * @return 0
     */
    int comprarPorPuntos(Perro p){
        return 0;
    }
    /**
     * compararPorEdad
     * @param p
     * @return 0
     */
    int compararPorEdad( Perro p){
        return 0;
    }

    /**
     * toString
     * @return Perro
     */
    @Override
    public String toString() {
        return "Perro{" + "nombre=" + nombre + ", raza=" + raza + ", imagen=" + imagen + ", puntos=" + puntos + ", edad=" + edad + '}';
    }
    /**
     * verificarInvariante
     */
    private void verificarInvariante(){
        
    }
    
}
