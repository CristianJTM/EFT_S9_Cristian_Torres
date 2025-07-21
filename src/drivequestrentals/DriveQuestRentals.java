/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package drivequestrentals;

/**
 *
 * @author Cristian
 */
import drivequestrentals.model.*;
import drivequestrentals.MenuConsola;

public class DriveQuestRentals {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.UnsupportedEncodingException{
        // TODO code application logic here
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        FlotaVehiculos flotaVehiculos = new FlotaVehiculos();
        MenuConsola menu = new MenuConsola(flotaVehiculos);
        menu.mostrarMenu();
    }

}
