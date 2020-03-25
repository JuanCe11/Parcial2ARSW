package edu.eci.arsw.coronavirus.controller;

public class CoronavirtusException extends Exception {
    public static String NOT_FOUND = "No encontrado";
    public static String CONNECTION_FAILED = "Problema de conexion con API";

    public CoronavirtusException(String message){
        super(message);
    }
}
