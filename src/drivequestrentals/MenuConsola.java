/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals;

/**
 *
 * @author Cristian
 */
import drivequestrentals.model.*;
import java.util.Scanner;
import drivequestrentals.archivo.*;
import drivequestrentals.interfaces.CalculoBoleta;
import drivequestrentals.utils.Validaciones;
import drivequestrentals.thread.HiloCargaVehiculos;
import drivequestrentals.excepciones.PatenteNoEncontradaException;
import java.io.BufferedReader;
import java.io.FileReader;

public class MenuConsola {

    private final FlotaVehiculos flotaVehiculos;
    private final Scanner sc;

    public MenuConsola(FlotaVehiculos flotaVehiculos) {
        this.flotaVehiculos = flotaVehiculos;
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== DRIVEQUEST RENTALS =====");
            System.out.println("1. Cargar vehículos desde archivo (con hilos)");
            System.out.println("2. Agregar vehículo");
            System.out.println("3. Listar vehículos");
            System.out.println("4. Arrendar vehículo");
            System.out.println("5. Devolver vehículo");
            System.out.println("0. Salir (guarda flota)");
            System.out.print("Elige opción: ");
            opcion = Validaciones.valorValido(0, 5);

            switch (opcion) {
                case 1 ->
                    cargarConHilos();
                case 2 ->
                    agregarVehiculo();
                case 3 ->
                    flotaVehiculos.listarVehiculos();
                case 4 ->
                    gestionarArriendo();
                case 5 ->
                    gestionarDevolucion();
                case 0 ->
                    EscritorCSV.guardarVehiculos(flotaVehiculos.getFlota());
                default ->
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void cargarConHilos() {
        Thread t1 = new HiloCargaVehiculos(flotaVehiculos, 0);
        Thread t2 = new HiloCargaVehiculos(flotaVehiculos, 500);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Carga completada con hilos simples.");
    }

    private void agregarVehiculo() {
        String patente;
        do {
            System.out.print("Patente: ");
            patente = sc.nextLine().trim().toUpperCase();
        } while (!Validaciones.validarPatente(patente));

        // Verificar duplicado antes de seguir pidiendo datos
        if (flotaVehiculos.existePatente(patente)) {
            System.out.println("ERROR: La patente ya existe en la flota.");
            return;
        }

        System.out.print("Tipo (1=Carga, 2=Pasajeros): ");
        int tipo = Validaciones.valorValido(1, 2);

        String marca = Validaciones.textoNoVacio("Marca: ");
        String modelo = Validaciones.textoNoVacio("Modelo: ");
        System.out.print("Año (2000-2025): ");
        int año = Validaciones.valorValido(2000, 2025);

        boolean agregado = false;

        if (tipo == 1) {
            System.out.print("Capacidad carga (ton): ");
            double cap = Validaciones.valorValido(1, 100000);
            VehiculoCarga v = new VehiculoCarga(patente, marca, modelo, año, cap);
            agregado = flotaVehiculos.agregarVehiculo(v);
        } else {
            System.out.print("Capacidad pasajeros: ");
            int cap = Validaciones.valorValido(1, 1000);
            VehiculoPasajeros v = new VehiculoPasajeros(patente, marca, modelo, año, cap);
            agregado = flotaVehiculos.agregarVehiculo(v);
        }

        if (agregado) {
            EscritorCSV.guardarVehiculos(flotaVehiculos.getFlota());
            System.out.println("Vehículo agregado y guardado.");
        } else {
            System.out.println("No se pudo agregar el vehículo (posible duplicado).");
        }
    }

    private void gestionarArriendo() {
    if (flotaVehiculos.getFlota().isEmpty()) {
        System.out.println("No hay vehículos en la flota");
        return;
    }

    try {
        System.out.print("Patente: ");
        String patente = sc.nextLine().trim().toUpperCase();

        if (!Validaciones.validarPatente(patente)) {
            System.out.println("Patente inválida.");
            return;
        }

        Vehiculo v = flotaVehiculos.buscarPorPatente(patente);
        if (v == null) {
            System.out.println("No existe un vehículo con esa patente.");
            return;
        }

        if (!v.getEstado().equalsIgnoreCase("Disponible")) {
            System.out.println("El vehículo no está disponible para arriendo.");
            return;
        }

        System.out.print("Días de arriendo: ");
        int dias = Validaciones.valorValido(1, 365);

        flotaVehiculos.arriendo(patente, dias);

        if (v instanceof CalculoBoleta cb) {
            cb.calcularBoleta();
        }

    } catch (PatenteNoEncontradaException e) {
        System.out.println("Error: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("Días de arriendo inválidos.");
    }
}

    private void gestionarDevolucion() {
        if (flotaVehiculos.getFlota().isEmpty()) {
            System.out.println("No hay vehiculos en la flota");
        } else {
            try {
                System.out.print("Patente: ");
                String patente = sc.nextLine().trim().toUpperCase();

                if (!Validaciones.validarPatente(patente)) {
                    System.out.println("Patente inválida.");
                    return;
                }

                flotaVehiculos.devolucion(patente);

            } catch (PatenteNoEncontradaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
