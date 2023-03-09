package bll;

import billGenerator.BillGenerator;
import dao.OrderDAO;
import model.Client;
import model.Order;
import dao.*;
import model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care realizeaza operatiile pe comenzi in raport cu baza de date
 */
public class OrderBLL {
    private final ClientDAO clientDAO;
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;
    private final ArrayList<Validator<Order>> validators;

    public OrderBLL() {
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
        validators = new ArrayList<>();
        validators.add(new OrderPriceValidator());
        validators.add(new OrderQuantityValidator());
    }

    /**
     * Metoda adauga o comanda in baza de date
     * Se va calcula pretul total, se decrementeaza stocul curent si se genereaza un bon (bill)
     * @param o comanda creata in prealabil
     * @return -1 in cazul in care clientul sau produsul nu exista, -2 in cazul in care cantitatea este invalida sau -3 in cazul in care id-ul comenzii exista deja
     */
    public int addOrder(Order o) {
        Client c = clientDAO.findById(o.getClientID());
        Product p = productDAO.findById(o.getProductID());
        Order newO = orderDAO.findById(o.getId());

        if(c == null || p == null) {
            return -1;
        }

        if(newO != null) {
            return -3;
        }

        if(p.getQuantity() < o.getQuantity()) {
            return -2;
        }

        o.setPrice(p.getPrice() * o.getQuantity());
        orderDAO.insert(o);
        productDAO.update("quantity", (p.getQuantity() - o.getQuantity()) + "", "id", p.getId() + "");
        BillGenerator bill = new BillGenerator(c, p, o);

        return 0;
    }

    /**
     * Metoda va genera o lista de comenzi din tabela Order
     * @return lista de comenzi
     * @throws NullPointerException in cazul in care nu exista comenzi
     */
    public List<Order> viewAllOrders() throws NullPointerException {
        List<Order> listOfOrders = orderDAO.findAll();
        if(listOfOrders == null) {
            throw new NullPointerException("Exception: No orders in the Order table!");
        }

        return listOfOrders;
    }
}
