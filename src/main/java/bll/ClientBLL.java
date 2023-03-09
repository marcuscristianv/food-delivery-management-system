package bll;

import dao.ClientDAO;
import model.Client;
import java.sql.SQLException;
import java.util.List;

/**
 * Clasa care realizeaza operatiile pe clienti in raport cu baza de date
 */
public class ClientBLL {
    private final ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * Metoda adauga un client in baza de date
     * @param c este clientul propriu-zis, creat anterior
     * @return se va returna -1, in caz de esec
     */
    public int addClient (Client c) {
        return clientDAO.insert(c);
    }

    /**
     * Metoda modifica informatiile despre un client, dat prin coloana ID
     * @param newName noul nume
     * @param newAddress noua adresa
     * @param id id-ul dupa care se face cautarea in tabela
     * @throws SQLException in cazul in care nu s-a putut gasi clientul cautat
     */
    public void editClient(String newName, String newAddress, String id) throws SQLException {
        clientDAO.update("name", newName, "id", id);
        clientDAO.update("address", newAddress, "id", id);
    }

    /**
     * Metoda va sterge un client dat prin ID
     * @param id identificatorul dupa care se cauta
     */
    public void deleteClient(String id) {
        clientDAO.delete("id", id);
    }

    /**
     * Metoda va genera o lista de clienti din tabela Client
     * @return lista de clienti
     * @throws NullPointerException in cazul in care nu exista niciun client
     */
    public List<Client> viewAllClients() throws NullPointerException {
        List<Client> listOfClients = clientDAO.findAll();
        if(listOfClients == null) {
            throw new NullPointerException("Exception: No clients in the Client table!");
        }

        return listOfClients;
    }

    /**
     * Metoda cauta in tabela clientul dupa ID
     * @param id identificatorul dupa care se cauta
     * @return clientul in caz de succes
     * @throws NullPointerException in cazul in care nu exista niciun client
     */
    public Client findClientById(int id) throws NullPointerException {
        Client c = clientDAO.findById(id);
        if(c == null) {
            throw new NullPointerException("Exception: Client ID " + id + " does not exist in the Client table!");
        }

        return c;
    }
}
