package bll;

import model.Order;

/**
 * Clasa care se ocupa cu validarea cantitatii din cadrul unei comenzi
 */
public class OrderQuantityValidator implements Validator<Order> {
    @Override
    public void validate(Order o) {
        QuantityValidator q = new QuantityValidator();
        q.validate(o.getQuantity());
    }

}
