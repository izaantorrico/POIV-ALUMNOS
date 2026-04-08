package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author torri
 */
public class Alumno {
    
    private String nombre;
    private String apellido;
    private int edad;
    private String curso;
    private String dni;

    public Alumno(String nombre, String apellido, int edad, String curso, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.curso = curso;
        this.dni = dni;
    
    }
    
    public String getDni(){
        return dni;
    }


  public String toFileString() {
        return nombre + "," + apellido + "," + edad + "," + curso + "," + dni;
    }

    // Convertir línea del archivo a objeto
    public static Alumno fromFileString(String linea) {
        String[] partes = linea.split(",");
        return new Alumno(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], partes[4]);
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " | Edad: " + edad + " | Curso: " + curso + " | DNI: " + dni;
    }
}