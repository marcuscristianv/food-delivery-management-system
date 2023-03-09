package bll;

import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care realizeaza operatiile pe produse in raport cu baza de date
 */
public class ProductBLL {
    private final ProductDAO productDAO;
    private final ArrayList<Validator<Product>> validators;

    public ProductBLL() {
        productDAO = new ProductDAO();
        validators = new ArrayList<>();
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
    }

    /**
     * Metoda instantiaza un validator de tip pret si il verifica
     * @param price reprezinta pretul testat
     */
    private void validatePrice(int price) {
        PriceValidator v = new PriceValidator();
        v.validate(price);
    }

    /**
     * Metoda instantiaza un validator de tip cantitate si il verifica
     * @param qty reprezinta cantitatea testata
     */
    private void validateQuantity(int qty) {
        QuantityValidator v = new QuantityValidator();
        v.validate(qty);
    }

    /**
     * Metoda instantiaza un validator de tip produs si il verifica
     * @param p reprezinta produsul testat
     */
    private void validateProduct(Product p) {
        for(Validator<Product> v: validators) {
            v.validate(p);
        }
    }

    /**
     * Metoda care adauga un nou produs in tabela Product
     * @param p este produsul creat in prealabil
     * @return se va returna -1, in caz de esec
     */
    public int addProduct(Product p) {
        validateProduct(p);
        return productDAO.insert(p);
    }

    /**
     * Metoda modifica informatiile despre un produs, dat prin coloana ID
     * @param newName noul nume
     * @param newPrice noul pret
     * @param newQuantity noua cantitate
     * @param id id-ul dupa care se face cautarea in tabela
     * @return -1 sau -2 in cazurile in care pretul sau cantitatea sunt invalide
     */
    public int editProduct(String newName, String newPrice, String newQuantity, String id) {
        try {
            validatePrice(Integer.parseInt(newPrice));
        } catch (IllegalArgumentException ex) {
            return -1;
        }

        try {
            validateQuantity(Integer.parseInt(newQuantity));
        } catch (IllegalArgumentException ex) {
            return -2;
        }

        productDAO.update("name", newName, "id", id);
        productDAO.update("price", newPrice, "id", id);
        productDAO.update("quantity", newQuantity, "id", id);
        return 0;
    }

    /**
     * Metoda va sterge un produs data prin ID
     * @param id indentificatorul dupa care se cauta in tabela Product
     */
    public void deleteProduct(String id) {
        productDAO.delete("id", id);
    }

    /**
     * Metoda va genera o lista de produse din tabela Products
     * @return lista de produse
     * @throws NullPointerException in cazul in care nu exista niciun produs
     */
    public List<Product> viewAllProducts() throws NullPointerException {
        List<Product> listOfProducts = productDAO.findAll();
        if(listOfProducts == null) {
            throw new NullPointerException("Exception: No products in the Product table!");
        }

        return listOfProducts;
    }

    /**
     * Metoda cauta in tabela produsul dupa ID
     * @param id identificatorul dupa care se cauta
     * @return produsul in caz de succes
     * @throws NullPointerException in cazul in care nu exista niciun produs
     */
    public Product findProductById(int id) throws NullPointerException {
        Product p = productDAO.findById(id);
        if(p == null) {
            throw new NullPointerException("Exception: Product ID " + id + " does not exist in the Product table!");
        }

        return p;
    }
}
