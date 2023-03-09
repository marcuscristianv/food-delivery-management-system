package presentation;

import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Clasa View care gestioneaza interfata grafica a aplicatiei
 */
public class View extends JFrame {
    private final JPanel mainPanel = new JPanel();

    //mainWindow
    private final JButton clientOperationsButton = new JButton("  Client Operations  ");
    private final JButton productOperationsButton = new JButton("Product Operations");
    private final JButton orderOperationsButton = new JButton("  Order Operations  ");

    //clientWindow
    private final JButton addClientButton = new JButton("  Add Client   ");
    private final JButton editClientButton = new JButton("  Edit Client   ");
    private final JButton deleteClientButton = new JButton("Delete Client");
    private final JButton viewAllClientsButton = new JButton("    View All    ");
    private final JButton backToMainButton = new JButton("     < Back     ");

    //productWindow
    private final JButton addProductButton = new JButton("  Add Product   ");
    private final JButton editProductButton = new JButton("  Edit Product   ");
    private final JButton deleteProductButton = new JButton("Delete Product");
    private final JButton viewAllProductsButton = new JButton("      View All       ");

    //orderWindow
    private final JButton addOrderButton = new JButton("   Add Order   ");
    private final JButton viewAllOrdersButton = new JButton("      View All     ");

    //addClientWindow
    private final JTextField idClientField = new JTextField(15);
    private final JTextField nameClientField = new JTextField(15);
    private final JTextField addressClientField = new JTextField(15);
    private final JButton backToClientButton = new JButton("     < Back     ");
    private final JButton okAddClientButton = new JButton("OK");

    //editClientWindow
    private final JButton okEditClientButton = new JButton("Search");

    //enterDataEditClientWindow
    private final JButton backToEditClientButton = new JButton("     < Back     ");
    private final JButton updateEditClientButton = new JButton("Update");

    //deleteClientWindow
    private final JButton okDeleteClientButton = new JButton("Search");

    //confirmDeleteClientWindow
    private final JButton confirmDeleteClientButton = new JButton("Delete");
    private final JButton backToDeleteClientButton = new JButton("     < Back     ");

    //addProductWindow
    private final JTextField idProductField = new JTextField(15);
    private final JTextField nameProductField = new JTextField(15);
    private final JTextField priceProductField = new JTextField(15);
    private final JTextField quantityProductField = new JTextField(15);
    private final JButton backToProductButton = new JButton("     < Back     ");
    private final JButton okAddProductButton = new JButton("OK");

    //editProductWindow
    private final JButton okEditProductButton = new JButton("Search");

    //enterDataEditProductWindow
    private final JButton backToEditProductButton = new JButton("     < Back     ");
    private final JButton updateEditProductButton = new JButton("Update");

    //deleteClientWindow
    private final JButton okDeleteProductButton = new JButton("Search");

    //confirmDeleteClientWindow
    private final JButton confirmDeleteProductButton = new JButton("Delete");
    private final JButton backToDeleteProductButton = new JButton("     < Back     ");

    //addOrderWindow
    private final JTextField idOrderField = new JTextField(15);
    private final JTextField clientIDOrderField = new JTextField(15);
    private final JTextField productIDOrderField = new JTextField(15);
    private final JTextField quantityOrderField = new JTextField(15);
    private final JButton backToOrderButton = new JButton("     < Back     ");
    private final JButton okAddOrderButton = new JButton("OK");

    public View() {
        mainWindow();
    }

    public void mainWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Choose an operation"));
        mainPanel.add(clientOperationsButton);
        mainPanel.add(productOperationsButton);
        mainPanel.add(orderOperationsButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250, 165);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void clientWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client Menu"));
        mainPanel.add(addClientButton);
        mainPanel.add(editClientButton);
        mainPanel.add(deleteClientButton);
        mainPanel.add(viewAllClientsButton);
        backToMainButton.setText("     < Back     ");
        mainPanel.add(backToMainButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(195, 235);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void productWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product Menu"));
        mainPanel.add(addProductButton);
        mainPanel.add(editProductButton);
        mainPanel.add(deleteProductButton);
        mainPanel.add(viewAllProductsButton);
        backToMainButton.setText("       < Back        ");
        mainPanel.add(backToMainButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(195, 235);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void addClientWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client id            "));
        idClientField.setText("");
        idClientField.setEditable(true);
        mainPanel.add(idClientField);
        mainPanel.add(new JLabel("Client name     "));
        nameClientField.setText("");
        mainPanel.add(nameClientField);
        mainPanel.add(new JLabel("Client address"));
        addressClientField.setText("");
        mainPanel.add(addressClientField);
        mainPanel.add(backToClientButton);
        mainPanel.add(okAddClientButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 160);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void editClientWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client id"));
        idClientField.setText("");
        idClientField.setEditable(true);
        mainPanel.add(idClientField);
        mainPanel.add(backToClientButton);
        mainPanel.add(okEditClientButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(235, 130);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void enterDataEditClientWindow(Client c) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client id            "));
        idClientField.setText(c.getId() + "");
        idClientField.setEditable(false);
        mainPanel.add(idClientField);
        mainPanel.add(new JLabel("Client name     "));
        nameClientField.setText(c.getName());
        nameClientField.setEditable(true);
        mainPanel.add(nameClientField);
        mainPanel.add(new JLabel("Client address"));
        addressClientField.setText(c.getAddress());
        addressClientField.setEditable(true);
        mainPanel.add(addressClientField);
        mainPanel.add(backToEditClientButton);
        mainPanel.add(updateEditClientButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 160);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void deleteClientWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client id"));
        idClientField.setText("");
        idClientField.setEditable(true);
        mainPanel.add(idClientField);
        mainPanel.add(backToClientButton);
        mainPanel.add(okDeleteClientButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(235, 130);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void confirmDeletionClientWindow(Client c) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client id            "));
        idClientField.setText(c.getId() + "");
        idClientField.setEditable(false);
        mainPanel.add(idClientField);
        mainPanel.add(new JLabel("Client name     "));
        nameClientField.setText(c.getName());
        nameClientField.setEditable(false);
        mainPanel.add(nameClientField);
        mainPanel.add(new JLabel("Client address"));
        addressClientField.setText(c.getAddress());
        addressClientField.setEditable(false);
        mainPanel.add(addressClientField);
        mainPanel.add(backToDeleteClientButton);
        mainPanel.add(confirmDeleteClientButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 160);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void viewAllClientsWindow(List<Client> clientList) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Client Table"));

        List<String> cols = new ArrayList<>();
        cols.add("id");
        cols.add("name");
        cols.add("address");

        List<String[]> values = new ArrayList<>();
        if(clientList != null) {
            for(int i = 0; i < clientList.size(); i++) {
                values.add(new String[] {clientList.get(i).getId() + "", clientList.get(i).getName(), clientList.get(i).getAddress()});
            }
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), cols.toArray());
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(table.getTableHeader(), BorderLayout.NORTH);

        backToMainButton.setText("       < Back        ");
        mainPanel.add(backToClientButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(520, 540);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void addProductWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product id            "));
        idProductField.setText("");
        idProductField.setEditable(true);
        mainPanel.add(idProductField);
        mainPanel.add(new JLabel("Product name     "));
        nameProductField.setText("");
        nameProductField.setEditable(true);
        mainPanel.add(nameProductField);
        mainPanel.add(new JLabel("Product price      "));
        priceProductField.setText("");
        priceProductField.setEditable(true);
        mainPanel.add(priceProductField);
        mainPanel.add(new JLabel("Product quantity "));
        quantityProductField.setText("");
        quantityProductField.setEditable(true);
        mainPanel.add(quantityProductField);

        mainPanel.add(backToProductButton);
        mainPanel.add(okAddProductButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 190);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void editProductWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product id"));
        idProductField.setText("");
        idProductField.setEditable(true);

        mainPanel.add(idProductField);
        mainPanel.add(backToProductButton);
        mainPanel.add(okEditProductButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(235, 130);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void enterDataEditProductWindow(Product p) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product id                  "));
        idProductField.setText(p.getId() + "");
        idProductField.setEditable(false);
        mainPanel.add(idProductField);
        mainPanel.add(new JLabel("Product name          "));
        nameProductField.setText(p.getName());
        nameProductField.setEditable(true);
        mainPanel.add(nameProductField);
        mainPanel.add(new JLabel("Product price           "));
        priceProductField.setText(p.getPrice() + "");
        priceProductField.setEditable(true);
        mainPanel.add(priceProductField);
        mainPanel.add(new JLabel("Product quantity     "));
        quantityProductField.setText(p.getQuantity() + "");
        quantityProductField.setEditable(true);
        mainPanel.add(quantityProductField);

        mainPanel.add(backToEditProductButton);
        mainPanel.add(updateEditProductButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 190);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void deleteProductWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product id"));
        idProductField.setText("");
        idProductField.setEditable(true);
        mainPanel.add(idProductField);
        mainPanel.add(backToProductButton);
        mainPanel.add(okDeleteProductButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(235, 130);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void confirmDeletionProductWindow(Product p) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product id            "));
        idProductField.setText(p.getId() + "");
        idProductField.setEditable(false);
        mainPanel.add(idProductField);
        mainPanel.add(new JLabel("Product name     "));
        nameProductField.setText(p.getName());
        nameProductField.setEditable(false);
        mainPanel.add(nameProductField);
        mainPanel.add(new JLabel("Product price      "));
        priceProductField.setText(p.getPrice() + "");
        priceProductField.setEditable(false);
        mainPanel.add(priceProductField);
        mainPanel.add(new JLabel("Product quantity "));
        quantityProductField.setText(p.getQuantity() + "");
        quantityProductField.setEditable(false);
        mainPanel.add(quantityProductField);

        mainPanel.add(backToDeleteProductButton);
        mainPanel.add(confirmDeleteProductButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(370, 180);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void viewAllProductsWindow(List<Product> productList) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Product Table"));

        List<String> cols = new ArrayList<>();
        cols.add("id");
        cols.add("name");
        cols.add("price");
        cols.add("quantity");

        List<String[]> values = new ArrayList<>();
        if(productList != null) {
            for(int i = 0; i < productList.size(); i++) {
                values.add(new String[] {productList.get(i).getId() + "", productList.get(i).getName(), productList.get(i).getPrice() + "", productList.get(i).getQuantity() + ""});
            }
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), cols.toArray());
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(table.getTableHeader(), BorderLayout.NORTH);

        backToMainButton.setText("       < Back        ");
        mainPanel.add(backToProductButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(510, 530);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void orderWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Order Menu"));

        backToMainButton.setText("      < Back       ");
        mainPanel.add(addOrderButton);
        mainPanel.add(viewAllOrdersButton);
        mainPanel.add(backToMainButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(195, 170);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void addOrderWindow() {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Order id                "));
        idOrderField.setText("");
        idOrderField.setEditable(true);
        mainPanel.add(idOrderField);
        mainPanel.add(new JLabel("Client id                 "));
        clientIDOrderField.setText("");
        clientIDOrderField.setEditable(true);
        mainPanel.add(clientIDOrderField);
        mainPanel.add(new JLabel("Product id            "));
        productIDOrderField.setText("");
        productIDOrderField.setEditable(true);
        mainPanel.add(productIDOrderField);
        mainPanel.add(new JLabel("Order Quantity    "));
        quantityOrderField.setText("");
        quantityOrderField.setEditable(true);
        mainPanel.add(quantityOrderField);
        // calculeaza pretul

        mainPanel.add(backToOrderButton);
        mainPanel.add(okAddOrderButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 190);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void viewAllOrdersWindow(List<Order> orderList) {
        mainPanel.removeAll();
        setVisible(true);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(new JLabel("Order Table"));

        List<String> cols = new ArrayList<>();
        cols.add("id");
        cols.add("clientID");
        cols.add("productID");
        cols.add("quantity");
        cols.add("price");

        List<String[]> values = new ArrayList<>();
        if(orderList != null) {
            for(int i = 0; i < orderList.size(); i++) {
                values.add(new String[] {orderList.get(i).getId() + "", orderList.get(i).getClientID() + "", orderList.get(i).getProductID() + "", orderList.get(i).getQuantity() + "", orderList.get(i).getPrice() + ""});
            }
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), cols.toArray());
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(table.getTableHeader(), BorderLayout.NORTH);

        backToMainButton.setText("       < Back        ");
        mainPanel.add(backToOrderButton);

        this.add(mainPanel);
        this.setTitle("Orders Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540, 530);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }

    //listeners
    public void addclientOperationsListener(ActionListener l) {
        clientOperationsButton.addActionListener(l);
    }

    public void addProductOperationsListener(ActionListener l) {
        productOperationsButton.addActionListener(l);
    }

    public void addOrderOperationsListener(ActionListener l) {
        orderOperationsButton.addActionListener(l);
    }

    public void addClientListener (ActionListener l) {
        addClientButton.addActionListener(l);
    }

    public void addEditClientListener (ActionListener l) {
        editClientButton.addActionListener(l);
    }

    public void addDeleteClientListener (ActionListener l) {
        deleteClientButton.addActionListener(l);
    }

    public void addViewAllClientsListener(ActionListener l) {
        viewAllClientsButton.addActionListener(l);
    }

    public void addBackToMainListener(ActionListener l) {
        backToMainButton.addActionListener(l);
    }

    public void addBackToClientListener(ActionListener l) {
        backToClientButton.addActionListener(l);
    }

    public void addOkAddClientListener(ActionListener l) {
        okAddClientButton.addActionListener(l);
    }

    public void addOkEditClientListener(ActionListener l) {
        okEditClientButton.addActionListener(l);
    }

    public void addBackToEditClientButton(ActionListener l) {
        backToEditClientButton.addActionListener(l);
    }

    public void addUpdateEditClientButton(ActionListener l) {
        updateEditClientButton.addActionListener(l);
    }

    public void addOkDeleteClientButton(ActionListener l) {
        okDeleteClientButton.addActionListener(l);
    }

    public void addBackToDeleteClientButton(ActionListener l) {
        backToDeleteClientButton.addActionListener(l);
    }

    public void addConfirmDeleteClientButton(ActionListener l) {
        confirmDeleteClientButton.addActionListener(l);
    }

    public void addProductButtonListener(ActionListener l) {
        addProductButton.addActionListener(l);
    }

    public void addBackToProductButtonListener(ActionListener l) {
        backToProductButton.addActionListener(l);
    }

    public void addOkAddProductButtonListener(ActionListener l) {
        okAddProductButton.addActionListener(l);
    }

    public void addEditProductButton(ActionListener l) {
        editProductButton.addActionListener(l);
    }

    public void addOkEditProductButton(ActionListener l) {
        okEditProductButton.addActionListener(l);
    }

    public void addBackToEditProductButton(ActionListener l) {
        backToEditProductButton.addActionListener(l);
    }

    public void addUpdateEditProductButtonListener(ActionListener l) {
        updateEditProductButton.addActionListener(l);
    }

    public void addOkDeleteProductButtonListener(ActionListener l) {
        okDeleteProductButton.addActionListener(l);
    }

    public void addDeleteProductButtonListener(ActionListener l) {
        deleteProductButton.addActionListener(l);
    }

    public void addBackToDeleteProductButton(ActionListener l) {
        backToDeleteProductButton.addActionListener(l);
    }

    public void addConfirmDeleteProductButton(ActionListener l) {
        confirmDeleteProductButton.addActionListener(l);
    }

    public void addViewAllProductsButtonListener(ActionListener l) {
        viewAllProductsButton.addActionListener(l);
    }

    public void addOrderButtonListener(ActionListener l) {
        addOrderButton.addActionListener(l);
    }

    public void addBackToOrderButtonListener(ActionListener l) {
        backToOrderButton.addActionListener(l);
    }

    public void addOkAddOrderButton(ActionListener l) {
        okAddOrderButton.addActionListener(l);
    }

    public void addViewAllOrdersButtonListener(ActionListener l) {
        viewAllOrdersButton.addActionListener(l);
    }

    public JTextField getIdClientField() {
        return idClientField;
    }

    public JTextField getNameClientField() {
        return nameClientField;
    }

    public JTextField getAddressClientField() {
        return addressClientField;
    }

    public JTextField getIdProductField() {
        return idProductField;
    }

    public JTextField getNameProductField() {
        return nameProductField;
    }

    public JTextField getPriceProductField() {
        return priceProductField;
    }

    public JTextField getQuantityProductField() {
        return quantityProductField;
    }

    public JTextField getIdOrderField() {
        return idOrderField;
    }

    public JTextField getClientIDOrderField() {
        return clientIDOrderField;
    }

    public JTextField getProductIDOrderField() {
        return productIDOrderField;
    }

    public JTextField getQuantityOrderField() {
        return quantityOrderField;
    }

    public void showError(String err) {
        JOptionPane.showMessageDialog(this, err);
    }
}
