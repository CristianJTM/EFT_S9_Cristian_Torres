/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals.thread;

/**
 *
 * @author Cristian
 */
import drivequestrentals.model.*;
import drivequestrentals.archivo.LectorCSV;
import java.io.BufferedReader;
import java.util.List;

public class HiloCargaVehiculos extends Thread {

    private final FlotaVehiculos flotaVehiculos;
    private final int retrasoMs;

    public HiloCargaVehiculos(FlotaVehiculos flotaVehiculos, int retrasoMs) {
        this.flotaVehiculos = flotaVehiculos;
        this.retrasoMs = retrasoMs;
    }

    @Override
    public void run() {
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] Iniciando carga...");
            Thread.sleep(retrasoMs);  // Simula que este hilo empieza más tarde
            LectorCSV.cargarVehiculos(flotaVehiculos);
            System.out.println("[" + Thread.currentThread().getName() + "] Carga completada.");
        } catch (Exception e) {
            System.out.println("[" + Thread.currentThread().getName() + "] Error: " + e.getMessage());
        }
    }
}
