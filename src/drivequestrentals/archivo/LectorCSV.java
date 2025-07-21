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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorCSV {

    public static void cargarVehiculos(FlotaVehiculos flotaVehiculos) {
        try (BufferedReader br = new BufferedReader(new FileReader("vehiculos.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String tipo = datos[0];
                String patente = datos[1];
                String marca = datos[2];
                String modelo = datos[3];
                int año = Integer.parseInt(datos[4]);
                String estado = datos[5];

                if (tipo.equals("Carga")) {
                    double capacidad = Double.parseDouble(datos[6]);
                    VehiculoCarga vc = new VehiculoCarga(patente, marca, modelo, año, capacidad);
                    vc.setEstado(estado);
                    flotaVehiculos.agregarVehiculo(vc);
                } else {
                    int capacidad = Integer.parseInt(datos[6]);
                    VehiculoPasajeros vp = new VehiculoPasajeros(patente, marca, modelo, año, capacidad);
                    vp.setEstado(estado);
                    flotaVehiculos.agregarVehiculo(vp);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer CSV: " + e.getMessage());
        }
    }

    public static Vehiculo parseLinea(String linea) {
        try {
            String[] partes = linea.split(";");
            String tipo = partes[0].trim();
            String patente = partes[1].trim();
            String marca = partes[2].trim();
            String modelo = partes[3].trim();
            int anio = Integer.parseInt(partes[4].trim());
            String estado = partes[5].trim();
            int diasArriendo = 0;

            if (tipo.equalsIgnoreCase("Carga")) {
                double capacidad = Double.parseDouble(partes[6].trim());
                VehiculoCarga v = new VehiculoCarga(patente, marca, modelo, anio, capacidad);
                v.setEstado(estado);
                return v;
            } else if (tipo.equalsIgnoreCase("Pasajeros")) {
                int capacidad = Integer.parseInt(partes[6].trim());
                VehiculoPasajeros v = new VehiculoPasajeros(patente, marca, modelo, anio, capacidad);
                v.setEstado(estado);
                return v;
            } else {
                System.out.println("Tipo desconocido: " + tipo);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error parseando línea: " + linea);
            return null;
        }
    }

}
