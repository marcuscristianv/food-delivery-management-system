package connection;
import java.sql.*;
import java.util.logging.*;

/**
 * Clasa care are functionalitatea de a crea, de a folosi si inchide o conexiune in relatie cu baza de date
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/?user=root";
    private static final String USER = "root";
    private static final String PASS = "admin";

    private static final ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Eroare la conectare!");
            LOGGER.log(Level.WARNING, "Eroare la conectare!");
        }
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Eroare la inchiderea conexiunii!");
                LOGGER.log(Level.WARNING, "Eroare la inchiderea conexiunii!");
            }
        }
    }

    public static void close(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Eroare la inchiderea statement-ului!");
                LOGGER.log(Level.WARNING, "Eroare la inchiderea statement-ului!");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Eroare la inchiderea resultSet-ului!");
                LOGGER.log(Level.WARNING, "Eroare la inchiderea resultSet-ului!");
            }
        }
    }
}
