package bll;

import model.Order;

/**
 * Clasa care se ocupa cu validarea pretului din cadrul unei comenzi
 */
public class OrderPriceValidator implements Validator<Order> {
    @Override
    public void validate(Order o) {
        PriceValidator p = new PriceValidator();
        p.validate(o.getPrice());
    }
}
