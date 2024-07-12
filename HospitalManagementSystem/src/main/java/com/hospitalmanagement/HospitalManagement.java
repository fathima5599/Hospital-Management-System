package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            Patient patient = new Patient(conn);
            Doctor doctor = new Doctor(conn);
            BookAppointment appointment = new BookAppointment(conn, patient, doctor);

            while (true) {
                System.out.println("==Hospital Management System===");
                System.out.println("1. Add Patient");
                System.out.println("2. Add Doctor");
                System.out.println("3. View Patients");
                System.out.println("4. View Doctors");
                System.out.println("5. Book Appointment");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int ch = sc.nextInt();
                sc.nextLine();  // Consume newline

                switch (ch) {
                    case 1:
                        System.out.print("Enter patient name: ");
                        String pname = sc.nextLine();
                        System.out.print("Enter patient age: ");
                        int age = sc.nextInt();
                        sc.nextLine();  // Consume newline
                        System.out.print("Enter patient gender: ");
                        String gender = sc.nextLine();
                        patient.setName(pname);
                        patient.setAge(age);
                        patient.setGender(gender);
                        patient.addPatient();
                        break;
                    case 2:
                        System.out.print("Enter doctor name: ");
                        String dname = sc.nextLine();
                        System.out.print("Enter doctor department: ");
                        String department = sc.nextLine();
                        doctor.addDoctor(dname, department);
                        break;
                    case 3:
                        patient.viewPatients();
                        break;
                    case 4:
                        doctor.viewDoctors();
                        break;
                    case 5:
                        System.out.print("Enter patient ID: ");
                        int patientId = sc.nextInt();
                        System.out.print("Enter doctor ID: ");
                        int doctorId = sc.nextInt();
                        sc.nextLine();  // Consume newline
                        System.out.print("Enter appointment date (YYYY-MM-DD): ");
                        String appointmentDate = sc.nextLine();
                        appointment.bookAppointment(patientId, doctorId, appointmentDate);
                        break;
                    case 6:
                        sc.close();
                        return;
                    default:
                        System.out.println("Please enter a valid choice.");
                        break;
                }
            }
        }
    }
}
