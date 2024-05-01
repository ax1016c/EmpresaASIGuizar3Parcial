package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import userManagement.UserDetails;

public class Conexion {

    private static Connection conexion = null;
    private static PreparedStatement preparedStatement = null;
    private static Statement s = null;
    private static ResultSet rs = null;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    // Constructor
    public Conexion() throws SQLException {
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bdexaasi_guizar", "tomcat", "tomcat");
            s = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("ERROR: No se pudo conectar a la base de datos: " + e.getMessage());
            throw e;
        }
    }
    
    // Method to obtain the connection
    public static Connection getConnection() {
        return conexion;
    }

    // Method to execute a query and return the ResultSet
    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = s.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("ERROR: Fallo en SQL: " + e.getMessage());
            throw e;
        }
        return resultSet;
    }

    // Method to execute an update or insert query with parameters
    public static void setQuery(String query, Object... parameters) throws SQLException {
        try {
            preparedStatement = conexion.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: Fallo en SQL: " + e.getMessage());
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to close the database connection
    public static void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
        
    // Method to get user details from the database
    public UserDetails getUserDetails(String username, String password) throws SQLException {
        UserDetails userDetails = null;
        Connection connection = null;

        try {
            connection = getConnection();

            String query = "SELECT id, rol, fecha_inicio_empresa FROM usuariosasi_guizar WHERE usuario = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String rol = rs.getString("rol");
                java.sql.Date fechaInicioEmpresa = rs.getDate("fecha_inicio_empresa");

                userDetails = new UserDetails(id, username, password, rol, fechaInicioEmpresa);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return userDetails;
    }
}
