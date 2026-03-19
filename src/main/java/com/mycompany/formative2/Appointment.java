/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.formative2;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author kobus
 */
public class Appointment {
    private int id;
    private int customerId;
    private String vin;
    private String serviceType;
    private LocalDate date;
    
    static private int totalAppointments = -1;
    
    // Constructor method
    public Appointment(int customerId, String vin,String serviceType, LocalDate date) {
        totalAppointments++;
        
        this.id = totalAppointments;
        this.customerId = customerId;
        this.vin = vin;
        this.serviceType = serviceType;
        this.date = date;

    }
    
    // Overriding toString method
    @Override
    public String toString(){
        // Formatting date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.date.format(formatter);
        
        // Return
        String result = "Appointment is schedueled for " + formattedDate + " for vehicle with VIN: " + this.vin;
        return result;
    }
}
