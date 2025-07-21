/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals.archivo;

/**
 *
 * @author Cristian
 */
import drivequestrentals.model.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class EscritorCSV {
    public static void guardarVehiculos(List<Vehiculo> flota) {
        try (FileWriter fw = new FileWriter("vehiculos.csv")) {
            for (Vehiculo v : flota) {
                String tipo = (v instanceof VehiculoCarga) ? "Carga" : "Pasajeros";
                String extra = "";
                if (v instanceof VehiculoCarga) {
                    extra = String.valueOf(((VehiculoCarga) v).getCapacidadCarga());
                } else {
                    extra = String.valueOf(((VehiculoPasajeros) v).getCapacidadPasajeros());
                }
                fw.write(tipo + ";" + v.getPatente() + ";" + v.getMarca() + ";" + v.getModelo() + ";" + v.getAño() + ";" + v.getEstado() + ";" + extra + "\n");
            }
            System.out.println("Datos guardados en vehiculos.csv");
        } catch (IOException e) {
            System.out.println("Error al guardar CSV: " + e.getMessage());
        }
    }
}
