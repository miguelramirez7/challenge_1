package challenge;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Jugador {
    //ATRIBUTOS
    private String nombre;
    private int numero_jugador;
    private int puntos=0;
    private int puntaje_set=0;
    private int cant_set_ganados=0;
    
    //CONSTRUCTOR
    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    //METODOS DE ENCAPSULAMIENTO

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero_jugador() {
        return numero_jugador;
    }

    public void setNumero_jugador(int numero_jugador) {
        this.numero_jugador = numero_jugador;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntaje_set() {
        return puntaje_set;
    }

    public void setPuntaje_set(int puntaje_set) {
        this.puntaje_set = puntaje_set;
    }

    public int getCant_set_ganados() {
        return cant_set_ganados;
    }

    public void setCant_set_ganados(int cant_set_ganados) {
        this.cant_set_ganados = cant_set_ganados;
    }
    
    
}
