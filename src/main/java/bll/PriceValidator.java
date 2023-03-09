package bll;

/**
 * Clasa care se ocupa cu validarea pretului
 */
public class PriceValidator implements Validator<Integer> {
    @Override
    public void validate(Integer price) throws IllegalArgumentException {
        if(price <= 0) {
            throw new IllegalArgumentException("Exception: Invalid price!");
        }
    }
}
