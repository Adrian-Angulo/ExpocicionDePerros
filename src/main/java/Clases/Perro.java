/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Esta es la clase perro que tiene todos los atributos de un perro
 * @author ADRIAN CASTILLO
 */
public class Perro {
    private String nombre;
    private String raza;
    private String imagen;
    private int puntos;
    private int edad;

    /**
     *
     */
    public Perro() {
    }

    /**
     *
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
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public String getRaza() {
        return raza;
    }

    /**
     *
     * @return
     */
    public String getImagen() {
        return imagen;
    }

    /**
     *
     * @return
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     *
     * @return
     */
    public int getEdad() {
        return edad;
    }
    /**
     * 
     * @param p
     * @return 
     */
    int compararPorNombre( Perro p){
        return 0;
    }
    /**
     * 
     * @param p
     * @return 
     */
    int comprarPorRaza(Perro p){
        return 0;
    }
    /**
     * 
     * @param p
     * @return 
     */
    int comprarPorPuntos(Perro p){
        return 0;
    }
    /**
     * 
     * @param p
     * @return 
     */
    int compararPorEdad( Perro p){
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Perro{" + "nombre=" + nombre + ", raza=" + raza + ", imagen=" + imagen + ", puntos=" + puntos + ", edad=" + edad + '}';
    }
    /**
     * 
     */
    private void verificarInvariante(){
        
    }
    
}
