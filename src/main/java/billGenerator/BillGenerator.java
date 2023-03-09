package billGenerator;

import model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa care genereaza un bon pentru fiecare comanda introdusa in baza de date, pe baza unui client
 */
public class BillGenerator {
    private final Client client;
    private final Product product;
    private final Order order;

    public BillGenerator(Client client, Product product, Order order) {
        this.client = client;
        this.product = product;
        this.order = order;

        generate();
    }

    /**
     * Se preiau datele despre client, numele produsului ales, cantitatea
     * dupa care se calculeaza pretul total si se scrie in fisierul generat dupa ID
     */
    private void generate() {
        File file = new File("bill_" + order.getId() + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write("ORDER ID: " + order.getId() + "\n");
            fileWriter.write("CLIENT NAME: " + client.getName() + "\n");
            fileWriter.write("__________________________________________\n");
            fileWriter.write("PRODUCT NAME: " + product.getName() + "\n");
            fileWriter.write("BOUGHT QUANTITY: " + order.getQuantity() + "\n");
            fileWriter.write("TOTAL (PAID): " + order.getPrice() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare la generarea bonului!");
        }
    }

}
