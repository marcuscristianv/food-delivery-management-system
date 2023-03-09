package dao;

import model.Order;

/**
 * Clasa care extinde AbstractDAO, care particularizeaza rolul acesteia pe clasa Order
 */
public class OrderDAO extends AbstractDAO<Order> {
    public OrderDAO() {
        super(Order.class);
    }
}
