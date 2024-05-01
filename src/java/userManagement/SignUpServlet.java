package userManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import conexion.Conexion;
import java.sql.SQLException;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    private Conexion conexion;

    @Override
    public void init() throws ServletException {
        try {
            conexion = new Conexion();
        } catch (SQLException e) {
            throw new ServletException("Error initializing servlet", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String startDate = request.getParameter("startDate");

        // Debugging: Print the received parameters
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);
        System.out.println("Start Date: " + startDate);

        // Validate the input data (not shown)
        // For simplicity, this example assumes the data is valid

        // Prepare the SQL query using PreparedStatement for security
        String query = "INSERT INTO usuariosasi_guizar (usuario, password, rol, fecha_inicio_empresa) VALUES (?, ?, ?, ?)";

        try {
            // Use PreparedStatement for better security and performance
            conexion.setQuery(query, username, password, role, startDate);

            // Redirect the user to the login page
            response.sendRedirect("signup_success.jsp");
        } catch (SQLException e) {
            
        }
    }

    @Override
    public void destroy() {
        try {
            conexion.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}