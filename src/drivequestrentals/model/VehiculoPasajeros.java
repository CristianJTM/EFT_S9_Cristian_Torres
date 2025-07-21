/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals.model;

/**
 *
 * @author Cristian
 */
import drivequestrentals.interfaces.CalculoBoleta;

public class VehiculoPasajeros extends Vehiculo implements CalculoBoleta {

    private int capacidadPasajeros;
    private final double TARIFA = 28000; // por día

    public VehiculoPasajeros(String patente, String marca, String modelo, int año, int capacidadPasajeros) {
        super(patente, marca, modelo, año);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Pasajeros: " + patente + " - " + marca + " - " + modelo + " - Año: " + año
                + " - Capacidad: " + capacidadPasajeros + " pax - Estado: " + estado);
    }

    @Override
    public void calcularBoleta() {
        double bruto = TARIFA * diasArriendo;
        double desc = bruto * DESCUENTO_PASAJEROS;
        double neto = (bruto - desc) * (1 + IVA);
        System.out.println("==========================================================================");
        System.out.println("Boleta Vehículo Pasajeros:");
        System.out.println("Bruto: $" + bruto + " | Descuento: $" + desc + " | Neto + IVA: $" + neto);
        System.out.println("==========================================================================");
    }
}
