package bll;

import model.Product;

/**
 * Clasa care se ocupa cu validarea pretului din cadrul unui produs
 */
public class ProductPriceValidator implements Validator<Product> {
    @Override
    public void validate(Product p) {
        PriceValidator v = new PriceValidator();
        v.validate(p.getPrice());
    }
}
