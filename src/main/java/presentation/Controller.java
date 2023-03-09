package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Clasa Controller, care se ocupa de legatura dintre model si view, implementand actionListener-e pentru interfata (butoane, text fields etc)
 */
public class Controller {
    private final View v;

    public Controller(View v) {
        this.v = v;

        //mainWindow
        v.addclientOperationsListener(new ClientOperationsListener());
        v.addProductOperationsListener(new ProductOperationsListener());
        v.addOrderOperationsListener(new OrderOperationsListener());



        //clientWindow
        v.addClientListener(new AddClientListener());
        v.addEditClientListener(new EditClientListener());
        v.addDeleteClientListener(new DeleteClientListener());
        v.addViewAllClientsListener(new ViewAllClientsListener());
        v.addBackToMainListener(new BackToMainListener());

        //addClientWindow
        v.addOkAddClientListener(new OkAddClientListener());

        //editClientWindow
        v.addOkEditClientListener(new OkEditClientListener());

        //enterDataEditClientWindow
        v.addBackToEditClientButton(new BackToEditClientButtonListener());
        v.addUpdateEditClientButton(new UpdateEditClientButtonListener());

        //deleteClientWindow
        v.addOkDeleteClientButton(new OkDeleteClientButtonListener());

        //confirmDeleteClientWindow
        v.addBackToDeleteClientButton(new BackToDeleteClientButtonListener());

        //viewAllClientsWindow
        v.addBackToClientListener(new BackToClientListener());
        v.addConfirmDeleteClientButton(new ConfirmDeleteClientButtonListener());



        //productWindow
        v.addProductButtonListener(new ProductButtonListener());
        v.addEditProductButton(new EditProductButtonListener());
        v.addDeleteProductButtonListener(new DeleteProductButtonListener());
        v.addViewAllProductsButtonListener(new ViewAllProductsButtonListener());

        //addProductWindow
        v.addBackToProductButtonListener(new BackToProductButtonListener());
        v.addOkAddProductButtonListener(new OkAddProductButtonListener());

        //editProductWindow
        v.addOkEditProductButton(new OkEditProductButton());

        //enterDataEditProductWindow
        v.addBackToEditProductButton(new BackToEditProductButton());
        v.addUpdateEditProductButtonListener(new UpdateEditProductButtonListener());

        //deleteProductWindow
        v.addOkDeleteProductButtonListener(new OkDeleteProductButtonListener());

        //confirmDeleteProductWindow
        v.addBackToDeleteProductButton(new BackToDeleteProductButton());
        v.addConfirmDeleteProductButton(new ConfirmDeleteProductButton());



        //orderWindow
        v.addOrderButtonListener(new OrderButtonListener());

        //addOrderWindow
        v.addBackToOrderButtonListener(new BackToOrderButtonListener());
        v.addOkAddOrderButton(new OkAddOrderButton());

        //viewAllOrdersWindow
        v.addViewAllOrdersButtonListener(new ViewAllOrdersButtonListener());
    }

    class ClientOperationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.clientWindow();
        }
    }

    class ProductOperationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.productWindow();
        }
    }

    class OrderOperationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.orderWindow();
        }
    }

    class AddClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.addClientWindow();
            v.getIdClientField().setEditable(true);
            v.getNameClientField().setEditable(true);
            v.getAddressClientField().setEditable(true);
        }
    }

    class EditClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.editClientWindow();
            v.getIdClientField().setEditable(true);
        }
    }

    class DeleteClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.deleteClientWindow();
            v.getIdClientField().setEditable(true);
        }
    }

    class ViewAllClientsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ClientBLL clientBLL = new ClientBLL();
            try {
                List<Client> clients = clientBLL.viewAllClients();
                v.viewAllClientsWindow(clients);
            } catch (NullPointerException ex) {
                v.viewAllClientsWindow(null);
            }
        }
    }

    class BackToMainListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.mainWindow();
        }
    }

    class BackToClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.clientWindow();
        }
    }

    class OkAddClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ClientBLL client = new ClientBLL();
            try {
                if(v.getIdClientField().getText() == "" || v.getNameClientField().getText() == "" || v.getAddressClientField().getText() == "") {
                    throw new NumberFormatException("");
                }
                int retValue = client.addClient(new Client(Integer.parseInt(v.getIdClientField().getText()), v.getNameClientField().getText(), v.getAddressClientField().getText()));
                if(retValue == -1) {
                    throw new Exception("id already exists");
                }
                v.showError("Success!");
            } catch (NumberFormatException ex1) {
                v.showError("Invalid fields!");
            } catch (Exception ex2) {
                v.showError("id already exists in the Client table!");
            }
        }
    }

    class OkEditClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ClientBLL client = new ClientBLL();
            try {
                Client c = client.findClientById(Integer.parseInt(v.getIdClientField().getText()));
                v.showError("Client (id " + v.getIdClientField().getText() + ") was found!");
                v.enterDataEditClientWindow(c);
            } catch (NumberFormatException ex1) {
                v.showError("Invalid id (not a number)!");
            } catch (NullPointerException ex2) {
                v.showError("Searched id does not exist in the Client table!");
            }
        }
    }

    class BackToEditClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.editClientWindow();
        }
    }

    class UpdateEditClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ClientBLL client = new ClientBLL();
            try {
                if(v.getNameClientField().getText() == "" || v.getAddressClientField().getText() == "") {
                    throw new NumberFormatException("");
                }
                client.editClient(v.getNameClientField().getText(), v.getAddressClientField().getText(), v.getIdClientField().getText());
                v.showError("Success!");
            } catch (NumberFormatException ex1) {
                v.showError("Invalid fields!");
            } catch (SQLException ex2) {
                v.showError("Error at editing client!");
            }
        }
    }

    class OkDeleteClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ClientBLL client = new ClientBLL();
            try {
                Client c = client.findClientById(Integer.parseInt(v.getIdClientField().getText()));
                v.showError("Client (id " + v.getIdClientField().getText() + ") was found!");
                v.confirmDeletionClientWindow(c);
            } catch (NumberFormatException ex1) {
                v.showError("Invalid id (not a number)!");
            } catch (NullPointerException ex2) {
                v.showError("Searched id does not exist in the Client table!");
            }
        }
    }

    class BackToDeleteClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.deleteClientWindow();
        }
    }

    class ConfirmDeleteClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ClientBLL client = new ClientBLL();
            client.deleteClient(v.getIdClientField().getText());
            v.showError("Success!");
        }
    }

    class ProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.addProductWindow();
        }
    }

    class BackToProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.productWindow();
        }
    }

    class OkAddProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductBLL product = new ProductBLL();
            try {
                if(v.getIdProductField().getText() == "" || v.getNameProductField().getText() == "" || v.getPriceProductField().getText() == "" || v.getQuantityProductField().getText() == "") {
                    throw new NumberFormatException("");
                }
                int retValue = product.addProduct(new Product(Integer.parseInt(v.getIdProductField().getText()), v.getNameProductField().getText(), Integer.parseInt(v.getPriceProductField().getText()), Integer.parseInt(v.getQuantityProductField().getText())));
                if(retValue == -1) {
                    throw new NumberFormatException("");
                }
                v.showError("Success!");
            } catch (NumberFormatException ex1) {
                v.showError("Invalid fields!");
            } catch (IllegalArgumentException ex2) {
                v.showError("Invalid fields!");
            }
        }
    }

    class EditProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.editProductWindow();
        }
    }

    class OkEditProductButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductBLL product = new ProductBLL();
            try {
                Product p = product.findProductById(Integer.parseInt(v.getIdProductField().getText()));
                v.showError("Product (id " + v.getIdProductField().getText() + ") was found!");
                v.enterDataEditProductWindow(p);
            } catch (NumberFormatException ex1) {
                v.showError("Invalid id (not a number)!");
            } catch (NullPointerException ex2) {
                v.showError("Searched id does not exist in the Product table!");
            }
        }
    }

    class BackToEditProductButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.editProductWindow();
        }
    }

    class UpdateEditProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductBLL product = new ProductBLL();
            try {
                if(v.getNameProductField().getText() == "" || v.getPriceProductField().getText() == "" || v.getQuantityProductField().getText() == "") {
                    throw new NumberFormatException("");
                }
                int retVal = product.editProduct(v.getNameProductField().getText(), v.getPriceProductField().getText(), v.getQuantityProductField().getText(), v.getIdProductField().getText());
                if(retVal == -1 || retVal == -2) {
                    throw new Exception("Invalid price or quantity!");
                }
                v.showError("Success!");
            } catch (NumberFormatException ex1) {
                v.showError("Invalid fields!");
            } catch (Exception ex2) {
                v.showError(ex2.getMessage());
            }
        }
    }

    class DeleteProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.deleteProductWindow();
        }
    }

    class OkDeleteProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductBLL product = new ProductBLL();
            try {
                Product p = product.findProductById(Integer.parseInt(v.getIdProductField().getText()));
                v.showError("Product (id " + v.getIdProductField().getText() + ") was found!");
                v.confirmDeletionProductWindow(p);
            } catch (NumberFormatException ex1) {
                v.showError("Invalid id (not a number)!");
            } catch (NullPointerException ex2) {
                v.showError("Searched id does not exist in the Product table!");
            }
        }
    }

    class BackToDeleteProductButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.deleteProductWindow();
        }
    }

    class ConfirmDeleteProductButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductBLL product = new ProductBLL();
            product.deleteProduct(v.getIdProductField().getText());
            v.showError("Success!");
        }
    }

    class ViewAllProductsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ProductBLL productBLL = new ProductBLL();
            try {
                List<Product> products = productBLL.viewAllProducts();
                v.viewAllProductsWindow(products);
            } catch (NullPointerException ex) {
                v.viewAllProductsWindow(null);
            }
        }
    }

    class OrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.addOrderWindow();
        }
    }

    class BackToOrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            v.orderWindow();
        }
    }

    class OkAddOrderButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            OrderBLL order = new OrderBLL();
            try {
                if(v.getIdClientField().getText() == "" || v.getNameClientField().getText() == "" || v.getAddressClientField().getText() == "" || Integer.parseInt(v.getQuantityOrderField().getText()) <= 0) {
                    throw new NumberFormatException("Invalid fields!");
                }
                int retValue = order.addOrder(new Order(Integer.parseInt(v.getIdOrderField().getText()), Integer.parseInt(v.getClientIDOrderField().getText()), Integer.parseInt(v.getProductIDOrderField().getText()), Integer.parseInt(v.getQuantityOrderField().getText()), 0));
                if(retValue == -1) {
                    throw new ArrayIndexOutOfBoundsException("Client/Product id not found in the database!");
                } else if(retValue == -2) {
                    throw new ArithmeticException("Low stock!");
                } else if(retValue == -3) {
                    throw new NullPointerException("Order id already exists in the Order table!");
                }
                v.showError("Success!\n(bill generated)");
            } catch (NumberFormatException ex1) {
                v.showError("Invalid fields");
            } catch (ArithmeticException ex3) {
                v.showError(ex3.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex2) {
                v.showError(ex2.getMessage());
            } catch (NullPointerException ex4) {
                v.showError(ex4.getMessage());
            }
        }
    }

    class ViewAllOrdersButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            OrderBLL orderBLL = new OrderBLL();
            try {
                List<Order> orders = orderBLL.viewAllOrders();
                v.viewAllOrdersWindow(orders);
            } catch (NullPointerException ex) {
                v.viewAllOrdersWindow(null);
            }
        }
    }
}
