package bll;

import model.Product;

/**
 * Clasa care se ocupa cu validarea cantitatii din cadrul unui produs
 */
public class ProductQuantityValidator implements Validator<Product> {
    @Override
    public void validate(Product p) {
        QuantityValidator v = new QuantityValidator();
        v.validate(p.getQuantity());
    }
}
