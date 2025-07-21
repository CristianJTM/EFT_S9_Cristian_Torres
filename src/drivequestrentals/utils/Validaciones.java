/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals.utils;

import java.util.Scanner;

/**
 *
 * @author Cristian
 */
public class Validaciones {
    private static Scanner scanner = new Scanner(System.in);
    //Metodo para validar valores numericos
    public static int valorValido(int min, int max) {
        int opcion;
        boolean valido = false;
        while (!valido) {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
                if (max < min) {
                    if (opcion >= min) {
                        valido = true;
                        return opcion;
                    } else {
                        System.out.println("Opcion invalida.");
                    }
                } else {
                    if (opcion >= min && opcion <= max) {
                        valido = true;
                        return opcion;
                    } else {
                        System.out.println("Opcion invalida.");
                    }
                }
            } else {
                System.out.println("Debe ingresar un número entero.");
                scanner.nextLine();
            }
        }
        return 0;
    }
    
    //Metodo para evitar parametros vacios
    public static String textoNoVacio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El campo no puede estar vacio.");
            }
        } while (texto.isEmpty());
        return texto;
    }
    
    
     public static boolean validarPatente(String patente) {
        patente = patente.toUpperCase();
        boolean valida = false;

        if (patente.matches("^[A-Z]{2}\\d{4}$")) {
            valida = true; // vieja: AB1234
        } else if (patente.matches("^[A-Z]{4}\\d{2}$")) {
            valida = true; // nueva: ABCD12
        }

        if (!valida) {
            System.out.println("Formato inválido. Ej: AB1234 o ABCD12");
        }
        return valida;
    }
}
