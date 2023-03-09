package dao;

import model.Client;

/**
 * Clasa care extinde AbstractDAO, care particularizeaza rolul acesteia pe clasa Client
 */
public class ClientDAO extends AbstractDAO<Client> {
    public ClientDAO() {
        super(Client.class);
    }

}
