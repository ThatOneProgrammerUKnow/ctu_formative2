/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.formative2;

/**
 *
 * @author kobus
 */
public class Customer {
    private int id;
    private String name;
    private String contactNumber;
    
    static private int totalCustomers = -1;
    
    public Customer(String name, String contactNumber){
        totalCustomers++;
        this.id=totalCustomers;
        this.name = name;
        this.contactNumber = contactNumber;
    }
    
    @Override
    public String toString(){
        return "Customer: " + name + "\nContact number: " +contactNumber;
    }
    
    public int getId(){
        return this.id;
    }
}
