/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

import Interfaz.ITennisGame;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class TennisGame implements  ITennisGame{
    //ATRIBUTOS
    private static Jugador jugador_1; //jugador 1 
    private static Jugador jugador_2; // jugador 2
    private static int PUNTAJE_FINAL_GAME=4;// con 4 puntos ganas un game siesque el contrincante tiene diferencia de 2 puntos (pude cambiar)
    private static int PUNTAJE_FINAL_SET=6;// con 6 puntos puedes ganar un set(puede cambiar)
    private static int numero_set; //contador q indicara el set que nos encontramos
    private static String ganador=""; //guarda el nombre del ganador
    private static int[][] sets = new int[5][2];
    /**siendo sets[numero del set][A o B que es (0 o 1)]
     * y sets[][] es el puntaje del set del jugador que se indique
     * solo tenemos que indicar: 
     * sets[numero_set][0] para darnos el puntaje de A en ese numero de set
     * sets[numero_set][1] para darnos el puntaje de A en ese numero de set
     */
    
    //CONSTRUCTOR
    public TennisGame(String nombre_jugador_1,String nombre_jugador_2) {
        //instanciamos jugador e iniciamos el juego
        jugador_1 = new Jugador(nombre_jugador_1);
        jugador_2 = new Jugador(nombre_jugador_2);
        jugador_1.setNumero_jugador(0);
        jugador_2.setNumero_jugador(1);
        numero_set =0;
        iniciarSets();
    }
    
    //METODOS
    /*metodo static usado para comparar si se gano un set o no a partir de los puntos obtenidos*/
    private static void compararPuntajesGame(){
        if (jugador_1.getPuntos() > 2 || jugador_2.getPuntos() > 2) {
            if (jugador_1.getPuntos() == jugador_2.getPuntos()) {
                    PUNTAJE_FINAL_GAME++;
            }
            if(jugador_1.getPuntos()>3 ||jugador_2.getPuntos()>3){
                if ((diferenciaDePuntosGame() >= 2)) {  
                        verificarGanadorUnGame();
                }
            }
       }
    }
    //metodo que compara los puntajes en un Set, para ver si se da Tie Break o NO
    private static void compararPuntajeSet(){
        if(jugador_1.getPuntaje_set()>4 || jugador_2.getPuntaje_set()>4){ //jugador_1.getPuntaje_set()
            if(numero_set<4){
                    if(jugador_1.getPuntaje_set()==jugador_2.getPuntaje_set()){
                        //Se hace el TIEBREAK
                        PUNTAJE_FINAL_SET=7;
                    }
                    verificarGanadorUnSet();

            }else{
                //no se hace TIE-BREAK
                    if(jugador_1.getPuntaje_set()==jugador_2.getPuntaje_set()){
                         //No se hace TIEBREAK
                         PUNTAJE_FINAL_SET++;
                    }
                    if(diferenciaDePuntosSet()>=2){
                        verificarGanadorUnSet();
                    }
            }
        }
    }
    /*metodos para realizar diferencia de puntajes*/
    private static int diferenciaDePuntosGame(){
        return jugador_1.getPuntos() - jugador_2.getPuntos()>0 ? jugador_1.getPuntos() -jugador_2.getPuntos(): -(jugador_1.getPuntos() - jugador_2.getPuntos());
    }
    private static int diferenciaDePuntosSet(){
        return jugador_1.getPuntaje_set() - jugador_2.getPuntaje_set()>0 ? jugador_1.getPuntaje_set() - jugador_2.getPuntaje_set(): -(jugador_1.getPuntaje_set() - jugador_2.getPuntaje_set());
    } 
    //metodo donde reiniciamos puntaje para iniciar otro game en un set
    private static void ganoUnGame(String jugador){
        jugador_1.setPuntos(0);
        jugador_2.setPuntos(0);
        PUNTAJE_FINAL_GAME=4;
        if(jugador==jugador_1.getNombre()){
            jugador_1.setPuntaje_set(jugador_1.getPuntaje_set()+1);
            sets[numero_set][jugador_1.getNumero_jugador()]++;    
        }
        if(jugador==jugador_2.getNombre()){
            jugador_2.setPuntaje_set(jugador_2.getPuntaje_set()+1);
            sets[numero_set][jugador_2.getNumero_jugador()]++;
        }
        compararPuntajeSet();
    }
    //metodo realizado cuando un jugador gana un SET completo
    private static void ganoSet(String jugador){
        jugador_1.setPuntaje_set(0);
        jugador_2.setPuntaje_set(0);
        PUNTAJE_FINAL_SET=6;
        numero_set++;
        if(jugador==jugador_1.getNombre()){
            jugador_1.setCant_set_ganados(jugador_1.getCant_set_ganados()+1);//
        }
        if(jugador==jugador_2.getNombre()){
            jugador_2.setCant_set_ganados(jugador_2.getCant_set_ganados()+1);//
        }
        verificarGanadorTenis();
        terminarJuego();
    }
    //metodo que indica quien gano juego
    private static void terminarJuego(){
        if(ganador!=""){
            System.out.println("GANO: "+ganador);
        }
    }
    //verificar si existe un ganador del juego en General
    private static void verificarGanadorTenis(){
        if(jugador_1.getCant_set_ganados()>2||jugador_2.getCant_set_ganados()>2){//jugador_1.getCant_set_ganados()
            if(jugador_1.getCant_set_ganados()==3){
                ganador=jugador_1.getNombre();
            }
            if(jugador_2.getCant_set_ganados()==3){
                ganador=jugador_2.getNombre();
            }
        }
    }
    
    //metodo que confirma si existe ganador de un SET
    private static void verificarGanadorUnSet(){
        if (jugador_1.getPuntaje_set()==PUNTAJE_FINAL_SET) {
            ganoSet(jugador_1.getNombre());
        }
        if (jugador_2.getPuntaje_set()==PUNTAJE_FINAL_SET) {
            ganoSet(jugador_2.getNombre());
        }
    }
    //metodo que confirma que un jugador gano un Game osea paso los 40 puntos de un game(y se diferencia en 2 puntos del contrincante)
    private static void verificarGanadorUnGame(){
        if(jugador_1.getPuntos()==PUNTAJE_FINAL_GAME){
            ganoUnGame(jugador_1.getNombre());
        }
        if(jugador_2.getPuntos()==PUNTAJE_FINAL_GAME){
            ganoUnGame(jugador_2.getNombre());
        }
    }
    //iniciar en 0 los sets
    private static void iniciarSets(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                sets[i][j]=0;
            }
        }
    }
    //metodo usado en el score para imprimir el puntaje actual
    public  static String imprimirSets(){
        String impresion_set="";
        int limite= (ganador!="")?numero_set:numero_set+1;
        for (int i = 0; i < limite; i++) {
            impresion_set +="\n"+(i+1)+": ";
            for (int j = 0; j < 2; j++) {
                impresion_set +="jugador: "+nombrarJugador(j)+" "+sets[i][j]+" ";   
            }
            impresion_set +=" ";
            impresion_set +="\n";
        }
        
        return impresion_set;
    }
    //metodo que apartir del numero de jugador nos devuelve el nombre del jugador (usado en imprimirSets())
    private static String nombrarJugador(int jugador){
        if (jugador==0) {
            return jugador_1.getNombre();
        }else    {
            return jugador_2.getNombre();
        }
    }
    //como la logica maneja puntos 1,2,3... y een tenis 0,15,30,40.. se hace la conversion para mostrar en el marcador
    private static String convertirPuntosTenis(int punto){
        switch(punto){
            case 0:
                    return "love";
            case 1:
                    return "15";
            case 2:
                    return "30";
            case 3:
                    return "40";
            default: //definimos el que esta en ventaja si igualan 40-40
                    if(punto==jugador_1.getPuntos()){
                        return punto>jugador_2.getPuntos()?"ad":"40";
                    }else{
                        return punto>jugador_1.getPuntos()?"ad":"40";
                    }
                    
        }
    }
    
    //METODOS PUBLICOS USADOS(CONSUMIDOS) POR UN MARCADOR
    //METODO QUE CONSUME EL MARCADOR PARA INDICAR QUE JUGADOR ANOTÓ
    @Override
    public String anotacion(String jugador_anotador){ 
        //Verificando que jugador anoto para guardar su puntuacion
        if(ganador==""){
            if(jugador_anotador.equalsIgnoreCase(jugador_1.getNombre())){
                        jugador_1.setPuntos(jugador_1.getPuntos()+1);
                        compararPuntajesGame(); 
                        return "punto guardado para A";
            }else if(jugador_anotador.equalsIgnoreCase(jugador_2.getNombre())){
                        jugador_2.setPuntos(jugador_2.getPuntos()+1);
                        compararPuntajesGame();
                        return "punto guardado para B";
            }else{
            
                        return "Error: jugador indicado es incorrecto";
            }  
        }else{
            return "Existe Ganador - Jego Terminado";
        }
        
    }
    
    //METODO QUE CONSUME EL MARCADOR PARA OBTENER LA PUNTUACION ACTUAL(SCORE)
    @Override
    public String score() {
        if(ganador!=""){
            return "SCORE: "+"\n"+nombrarJugador(jugador_1.getNumero_jugador())+" "+convertirPuntosTenis(jugador_1.getPuntos())+"\n"+nombrarJugador(jugador_2.getNumero_jugador())+" "+convertirPuntosTenis(jugador_2.getPuntos())
                    +"\n"+"SET : "+imprimirSets()+"GANADOR: "+ganador;
            
        }else{
            return "SCORE: "+"\n"+nombrarJugador(jugador_1.getNumero_jugador())+" "+convertirPuntosTenis(jugador_1.getPuntos())+"\n"+nombrarJugador(jugador_2.getNumero_jugador())+" "+convertirPuntosTenis(jugador_2.getPuntos())
                    +"\n"+"SET : "+imprimirSets();
        }
        
    }
}
