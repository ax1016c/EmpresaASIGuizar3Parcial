// LoginServlet.java
package userManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import conexion.Conexion;
import java.sql.SQLException;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

    // Retrieve user details from the database
    try {
        // Assuming UserManager has a method to fetch user details by username and password
        UserDetails user = UserManager.getUserDetails(username, password);

        if (user != null) {
            // Create a session for the user
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("id", user.getId());
            session.setAttribute("rol", user.getRole());
            session.setAttribute("fecha_inicio_empresa", user.getStartDate());

            // Redirect the user to a welcome page
            response.sendRedirect("welcome.jsp");
        } else {
            // User not found or invalid credentials, handle accordingly
            // For simplicity, this example assumes the credentials are valid
        }
    } catch (SQLException e) {
        // Handle SQLException
        e.printStackTrace();        
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