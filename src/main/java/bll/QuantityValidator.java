package bll;

/**
 * Clasa care se ocupa cu validarea cantitate
 */
public class QuantityValidator implements Validator<Integer> {
    @Override
    public void validate(Integer qty) throws IllegalArgumentException {
        if(qty <= 0) {
            throw new IllegalArgumentException("Exception: Invalid quality!");
        }
    }
}
