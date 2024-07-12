package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hospitalmanagement.Patient;
import com.hospitalmanagement.DatabaseConnection;

@WebServlet(name = "PatientServlet", urlPatterns = { "/PatientServlet" })
public class PatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PatientServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            Patient patient = new Patient(conn);

            patient.setName(name);
            patient.setAge(age);
            patient.setGender(gender);

            patient.addPatient();

            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
