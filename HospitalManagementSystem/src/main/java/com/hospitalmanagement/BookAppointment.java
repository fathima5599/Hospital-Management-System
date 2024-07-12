package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAppointment {
	 private Connection connection;
	    private Patient patient;
	    private Doctor doctor;

	    public BookAppointment(Connection connection, Patient patient, Doctor doctor) {
	        this.connection = connection;
	        this.patient = patient;
	        this.doctor = doctor;
	    }

	    public void bookAppointment() throws SQLException {
	        // Implement the method to book an appointment
	    }

	    public boolean checkAvailability(int doctorId, String appointmentDate) throws SQLException {
	        String query = "SELECT COUNT(1) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, doctorId);
	            ps.setString(2, appointmentDate);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next() && rs.getInt(1) == 0;
	            }
	        }
	    }

	    public void bookAppointment(int patientId, int doctorId, String appointmentDate) throws SQLException {
	        if (!patient.getPatientById(patientId)) {
	            System.out.println("Please provide valid patient id.");
	            return;
	        }

	        if (!doctor.getDoctorById(doctorId)) {
	            System.out.println("Please provide valid doctor id.");
	            return;
	        }

	        if (!checkAvailability(doctorId, appointmentDate)) {
	            System.out.println("Doctor not available.");
	            return;
	        }

	        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, patientId);
	            ps.setInt(2, doctorId);
	            ps.setString(3, appointmentDate);
	            if (ps.executeUpdate() > 0) {
	                System.out.println("Appointment booked successfully.");
	            } else {
	                System.out.println("Appointment not booked successfully.");
	            }
	        }
	    }
	}


