/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

import Interfaz.ITennisGame;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PruebaMarcador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Usando main para probar como consumiría un Marcador la clase "TennisGame" 
        //prueba manual mente mediante consola indicando los nombre o alias del jugador al instanciar un objeto de TennisGame
        ITennisGame nuevoJuego = new TennisGame("juan","pedro");
        boolean seguir= true;
        
        while (seguir) {            
            Scanner teclado = new Scanner(System.in);
            System.out.println("¿quien anoto?");
            String jugador = teclado.nextLine();
            //despues de obtener por consola
            nuevoJuego.anotacion(jugador);          
            //el marcador consume el score(lo puede hacer en cualquier momento para ver la puntuacion)
            String puntuacion_actual = nuevoJuego.score();
            System.out.println(puntuacion_actual);
            
        }
    
    }
    
}
