package userManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import conexion.Conexion;

public class UserManager {

    // Method to get user details by username and password from the database
    public static UserDetails getUserDetails(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserDetails userDetails = null;

        try {
            // Establish a connection to the database
            connection = Conexion.getConnection();

            // Prepare the SQL statement
            String query = "SELECT id, usuario, password, rol, fecha_inicio_empresa FROM usuariosasi_guizar WHERE usuario = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if the result set contains any rows
            if (resultSet.next()) {
                // Retrieve user details from the result set
                int id = resultSet.getInt("id");
                String rol = resultSet.getString("rol");
                java.sql.Date fechaInicioEmpresa = resultSet.getDate("fecha_inicio_empresa");

                // Create a UserDetails object
                userDetails = new UserDetails(id, username, password, rol, fechaInicioEmpresa);
            }
        } finally {
            // Close the database resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return userDetails;
    }
}
