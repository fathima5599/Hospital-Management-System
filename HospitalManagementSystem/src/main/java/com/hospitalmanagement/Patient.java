package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
    private Connection conn;
    private String name;
    private int age;
    private String gender;

    public Patient(Connection conn) {
        this.conn = conn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addPatient() throws SQLException {
        String query = "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            
            if (ps.executeUpdate() > 0) {
                System.out.println("Patient details added successfully.");
            } else {
                System.out.println("Failed to add patient details.");
            }
        }
    }

    public void viewPatients() throws SQLException {
        String query = "SELECT * FROM patients";
        
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            System.out.println("Patient details:");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                
                System.out.println("Patient Id: " + id);
                System.out.println("Patient Name: " + name);
                System.out.println("Patient Age: " + age);
                System.out.println("Patient Gender: " + gender);
            }
        }
    }

    public boolean getPatientById(int id) throws SQLException {
        String query = "SELECT COUNT(1) FROM patients WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        
        return false;
    }
}
