package dao;

import model.Product;
import connection.ConnectionFactory;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;

/**
 * Clasa care extinde AbstractDAO, care particularizeaza rolul acesteia pe clasa Product
 */
public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }

    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT quantity");
        sb.append(" FROM warehouse.Product");
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public int getStock(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            int quantity = -2;
            while(resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }

            return quantity;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO: getStock " + e.getMessage());
        }

        return -1;
    }
}
