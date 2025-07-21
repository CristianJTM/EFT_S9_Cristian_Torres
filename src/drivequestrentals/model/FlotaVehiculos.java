/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals.model;

/**
 *
 * @author Cristian
 */
import drivequestrentals.model.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import drivequestrentals.archivo.*;
import drivequestrentals.excepciones.PatenteNoEncontradaException;

public class FlotaVehiculos {

    private List<Vehiculo> flota = Collections.synchronizedList(new ArrayList<>());
    private Map<String, Vehiculo> mapaPatentes = Collections.synchronizedMap(new HashMap<>());

    public boolean agregarVehiculo(Vehiculo v) {
        if (mapaPatentes.containsKey(v.getPatente())) {
            return false;
        }
        mapaPatentes.put(v.getPatente(), v);
        flota.add(v);
        return true;
    }

    public void listarVehiculos() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehículos cargados en la flota.");
            return;
        }

        flota.forEach(Vehiculo::mostrarDatos);
    }

    public boolean existePatente(String patente) {
        return mapaPatentes.containsKey(patente);
    }

    public Vehiculo buscarPorPatente(String patente) throws PatenteNoEncontradaException {
        Vehiculo v = mapaPatentes.get(patente);
        if (v == null) {
            throw new PatenteNoEncontradaException("Patente no encontrada en el sistema: " + patente);
        }
        return v;
    }

    public void arriendo(String patente, int dias) {
        try {
            Vehiculo v = buscarPorPatente(patente);
            if (v.getEstado().equals("Disponible")) {
                v.setDiasArriendo(dias);
                v.setEstado("En Prestamo");
                System.out.println("Arriendo registrado para: " + patente);
                EscritorCSV.guardarVehiculos(flota);
            } else {
                System.out.println("No disponible.");
            }
        } catch (PatenteNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void devolucion(String patente) throws PatenteNoEncontradaException {
        Vehiculo v = buscarPorPatente(patente);

        if (v.getEstado().equalsIgnoreCase("En Prestamo")) {
            v.setEstado("Disponible");
            v.setDiasArriendo(0);
            System.out.println("Devolución registrada: " + patente);
            EscritorCSV.guardarVehiculos(flota);
        } else {
            System.out.println("No está en préstamo.");
        }
    }

    public List<Vehiculo> getFlota() {
        return flota;
    }
}
