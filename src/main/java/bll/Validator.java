package bll;

/**
 * Interfata care propune implementarea de validatoare
 * @param <T> este obiectul pe care se realizeaza validarea
 */
public interface Validator<T> {
    public void validate(T t);
}
