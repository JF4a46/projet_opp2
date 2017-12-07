/*package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Client;

public class ClientDAO {

	private SqlDao sqlDao;
	private static String insertQuerry = "INSERT INTO client "
			+ "(nom, prenom, telephone_maison, cellulaire, telephone_bureau, date_naissance, courriel, adresse, permis_conduire, classe_conduite, programme_point) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	private static String selectByPhoneQuery = "SELECT id FROM client WHERE telephone_maison LIKE ? OR cellulaire LIKE ? OR telephone_bureau LIKE ?";

	public ClientDAO() {
		this.sqlDao = SqlDao.getInstance();
	}

	public void save(Client client) {
		try {
			PreparedStatement statement = this.sqlDao.getConnection().prepareStatement(insertQuerry);
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
//			statement.setString(3, client.getTelephoneMaison());
//			statement.setString(4, client.getCellulaire());
//			statement.setString(5, client.getTelephoneBureau());
//			statement.setDate(6, new Date(client.getDateNaissance().getTime()));
//			statement.setString(7, client.getCourriel());
//			statement.setString(8, client.getAdresse());
			statement.setString(9, client.getPermisConduire());
			statement.execute();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getClientIdByPhone(String phone) {

		ArrayList<Integer> clientId = new ArrayList<Integer>();
		try {
			PreparedStatement statement = this.sqlDao.getConnection().prepareStatement(selectByPhoneQuery);

			statement.setString(1, phone);
			statement.setString(2, phone);
			statement.setString(3, phone);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				clientId.add(rs.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientId;
	}

}
*/