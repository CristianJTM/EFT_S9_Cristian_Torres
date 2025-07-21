/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drivequestrentals.model;

/**
 *
 * @author Cristian
 */
public abstract class Vehiculo {

    static Vehiculo get(String patente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 protected String patente;
    protected String marca;
    protected String modelo;
    protected int año;
    protected int diasArriendo;
    protected String estado; // Disponible o En Prestamo

    public Vehiculo() {}

    public Vehiculo(String patente, String marca, String modelo, int año) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.estado = "Disponible";
    }

    // Getters y setters
    public String getPatente() { return patente; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAño() { return año; }
    public int getDiasArriendo() { return diasArriendo; }
    public String getEstado() { return estado; }

    public void setDiasArriendo(int diasArriendo) { this.diasArriendo = diasArriendo; }
    public void setEstado(String estado) { this.estado = estado; }

    public abstract void mostrarDatos();    
}
