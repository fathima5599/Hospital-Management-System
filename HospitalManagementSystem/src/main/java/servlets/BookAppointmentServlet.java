package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospitalmanagement.DatabaseConnection;

@WebServlet("/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public BookAppointmentServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connection = DatabaseConnection.initializeDatabase();
        } catch (SQLException e) {
            throw new ServletException("Unable to establish database connection.", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/bookAppointment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientName = request.getParameter("patientName");
        String doctorName = request.getParameter("doctorName");

        String message;
        try {
            int patientId = getIdByName("patients", patientName);
            int doctorId = getIdByName("doctors", doctorName);

            if (patientId == -1) {
                message = "Please provide a valid patient name.";
            } else if (doctorId == -1) {
                message = "Please provide a valid doctor name.";
            } else {
                bookAppointment(patientId, doctorId);
                message = "Appointment booked successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "An error occurred while booking the appointment.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
    }

    private int getIdByName(String table, String name) throws SQLException {
        String query = "SELECT id FROM " + table + " WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1; // Return -1 if not found
    }

    private void bookAppointment(int patientId, int doctorId) throws SQLException {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, CURDATE(), 'Booked')";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        DatabaseConnection.closeDatabase(connection);
    }
}
