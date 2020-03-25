package edu.eci.arsw.coronavirus.controller;

public class CoronavirusException extends Exception {
    public static String NOT_FOUND = "No encontrado";
    public static String CONNECTION_FAILED = "Problema de conexion con API";

    public CoronavirusException(String message){
        super(message);
    }
}
