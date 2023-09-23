/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADRIAN CASTILLO
 */
public class ExpocicionPerros extends Perro {

    ArrayList<Perro> darPerros = new ArrayList<>(); // lista que almacena los perros de la clase perro

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

    public ArrayList<Perro> getDarPerros() {
        return darPerros;
    }

    public void setDarPerros(ArrayList<Perro> darPerros) {
        this.darPerros = darPerros;
    }

    public void serializacion(List<Perro> darPerros, String ruta) {
        //Creamos un flujo de salida de objetos
        try (ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(ruta))) {

            //Utilizamos el flujo de datos para escribir la lista de perros en el archivo
            escribiendoFichero.writeObject(darPerros);
            System.out.println("Se escribio el archivo");

        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo");
        }
    }

}
