/*package controlers;

import java.util.ArrayList;
import java.util.Date;

import daos.ClientDAO;
import daos.ClientStatement;
import models.Client;

public class ClientController {

	private ClientDAO clientDao;
	private ClientStatement clientStatement;

	public ClientController() {
		this.clientDao = new ClientDAO();
		this.clientStatement = new ClientStatement();
	}

	public void saveClient(Client client) {
		//this.clientDao.save(client);
		this.clientStatement.save(client);
	}

	public Client creerClient(String nom, String prenom, String telephone, String permisConduire) {

		Client client = new Client(nom, prenom, telephone, permisConduire);

		this.saveClient(client);
		return client;
	}

	public Client searchClientByName(String prenom, String nom) {
		return null;
	}

	public ArrayList<Integer> searchClientIdByPhone(String telephone) {
		//return this.clientDao.getClientIdByPhone(telephone);
		return this.clientStatement.getClientIdByPhone(telephone);
	}

}
*/