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

public class VehiculoCarga extends Vehiculo implements CalculoBoleta{
    private double capacidadCarga; // toneladas
    private final double TARIFA = 35000; // por día

    public VehiculoCarga(String patente, String marca, String modelo, int año, double capacidadCarga) {
        super(patente, marca, modelo, año);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() { return capacidadCarga; }

    @Override
    public void mostrarDatos() {
        System.out.println("Carga: " + patente + " - " + marca + " - " + modelo + " - Año: " + año +
                " - Capacidad: " + capacidadCarga + " ton - Estado: " + estado);
    }

    @Override
    public void calcularBoleta() {
        double bruto = TARIFA * diasArriendo;
        double desc = bruto * DESCUENTO_CARGA;
        double neto = (bruto - desc) * (1 + IVA);
        System.out.println("==========================================================================");
        System.out.println("Boleta Vehículo Carga:");
        System.out.println("Bruto: $" + bruto + " | Descuento: $" + desc + " | Neto + IVA: $" + neto);
        System.out.println("==========================================================================");
    }
    
}
