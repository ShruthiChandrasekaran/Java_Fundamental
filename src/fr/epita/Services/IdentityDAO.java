/**
 * 
 */
package fr.epita.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Exceptions.IdentityExceptions;
import fr.epita.launcher.Launcher;

/**
 * @author Shruthi C
 *
 */
public class IdentityDAO {

	private Connection connection;
	private static final Logger log = Logger.getLogger(Launcher.class.getName());
	
	/**
	 * IdentityDAO - Get connection
	 */
	public IdentityDAO() {
		connection = ConnectionService.getConnection();
	}

	/**
	 * Identity Creation
	 * @param entity DataIdentity
	 * @throws Exception Create Operation
	 */
	public void create(DataIdentity entity) throws Exception{
		
		connection = ConnectionService.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO IDENTITIES (USERNAME, EMAIL, "
					+ "PASSWORD, FIRST_NAME, LAST_NAME)"
					+" VALUES (?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, entity.getUsername());
			statement.setString(2, entity.getEmail());
			statement.setString(3, entity.getPassword());
			statement.setString(4, entity.getFirstname());
			statement.setString(5, entity.getLastname());
			statement.executeUpdate();
			
			
			ConnectionService.releaseConnection();
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);
		}
		finally {
			if (statement != null) statement.close();
		}

	}
	
	/**
	 * Identity Update
	 * @param entity DataIdentity
	 * @throws IdentityExceptions Update Exception
	 * @throws SQLException Exception for SQL related things
	 */
	public void update(DataIdentity entity) throws IdentityExceptions, SQLException{
		connection = ConnectionService.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "UPDATE IDENTITIES "
					+ "SET username=?,"
					+ "EMAIL=?,"
					+ "PASSWORD=?"
					+ "WHERE IDENTITY_ID=?";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, entity.getUsername());
			statement.setString(2, entity.getEmail());
			statement.setString(3, entity.getPassword());
			statement.setString(4, entity.getUid());
			statement.execute();
			statement.close();
		
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);
		}
		finally {
			if (statement != null) statement.close();
		}
	}

	/**
	 * Identity Delete
	 * @param identity DataIdentity
	 * @throws IdentityExceptions Delete Exception
	 * @throws SQLException Exception for SQL related things
	 */
	public void delete(DataIdentity identity) throws IdentityExceptions, SQLException{
		connection = ConnectionService.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "DELETE FROM IDENTITIES "
					+" WHERE IDENTITY_ID=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, identity.getUid());
			statement.executeUpdate();
			statement.close();
			ConnectionService.releaseConnection();
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);			
		}
		finally {
			if (statement != null) statement.close();
		}
	}

	/**
	 * List all the Identities
	 * @return All Identities from Identity table
	 * @throws SQLException Exception for SQL related things
	 */
	public List<DataIdentity> readAll() throws SQLException{
		connection = ConnectionService.getConnection();
		Map<String, DataIdentity> identities = new HashMap<String, DataIdentity>();
		ResultSet result = null;
		try {
			PreparedStatement read = readPreparedStatement(connection);
			result = read.executeQuery();
			
			while(result.next()){
				String uid = result.getString("identity_id");
				String userName = result.getString("userName");
				String email = result.getString("email");
				String password = result.getString("password");
				String firstname = result.getString("first_name");
				String lastname = result.getString("last_name");
				
				DataIdentity identity;
					if (identities.containsKey(uid)){
						identity = identities.get(uid);
					}
					else{
						identity = new DataIdentity(uid, userName, 
								email, password,firstname,lastname);
						identities.put(identity.getUid(), identity);
					}
					identities.put(identity.getUid(), identity);
			}
			
			ConnectionService.releaseConnection();
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);
			
		}
		finally {
			if (result != null) result.close();
		}
		List<DataIdentity> resultList = new ArrayList<>();
		for (DataIdentity i :identities.values()){
			resultList.add(i);
		}

		return resultList;
	}
	
	private PreparedStatement readPreparedStatement(Connection connection) throws SQLException {
		String sql = "SELECT * FROM IDENTITIES";
		return connection.prepareStatement(sql);
	}

	/**
	 * Find an particular Identity
	 * @param id User Id
	 * @return Data of particular User ID
	 * @throws IdentityExceptions Search Exception
	 * @throws SQLException Exception for SQL related things
	 */
	public DataIdentity find(Object id) throws IdentityExceptions, SQLException {
		DataIdentity identity = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		connection = ConnectionService.getConnection();
		try {
			String sql = "SELECT * FROM IDENTITIES WHERE IDENTITY_ID = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(id.toString()));
			
			try {
				result = statement.executeQuery();
				if (result.next()){
					String uid = result.getString("identity_id");
					String displayName = result.getString("username");
					String email = result.getString("email");
					String psswd = result.getString("password");
					String firstname = result.getString("first_name");
					String lastname = result.getString("last_name");
					identity = new DataIdentity(uid, displayName, email,psswd,firstname,lastname);
				}
			}
			finally {
				if (result != null) result.close();
			}
			ConnectionService.releaseConnection();
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);
		}
		finally {
			if (statement != null) statement.close();
		}
		return identity;
	}

	/**
	 * Delete all the Identities
	 * @return Delete
	 * @throws IdentityExceptions Delete all Exception
	 * @throws SQLException Exception for SQL related things
	 */
	public List<DataIdentity> deleteAll() throws IdentityExceptions, SQLException{
		connection = ConnectionService.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "DELETE FROM IDENTITIES";
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			statement.close();
			ConnectionService.releaseConnection();
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);
		}
		finally {
			if (statement != null) statement.close();
		}
		return Collections.emptyList();
	}

	/**
	 * Check User exists
	 * @param userName Username
	 * @param emailUser Email Id
	 * @return Data if exists
	 * @throws SQLException Exception for SQL related things
	 */
	public DataIdentity checkUserExists(String userName, String emailUser) throws SQLException {
		DataIdentity identityValue = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		connection = ConnectionService.getConnection();
		try {
			String sql = "SELECT * FROM IDENTITIES WHERE USERNAME = ? OR EMAIL = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setString(2, emailUser);
			
			try {
				result = statement.executeQuery();
				if (result.next()){
					String username = result.getString("username");
					String email = result.getString("email");
					identityValue = new DataIdentity(username, email);
				}
				ConnectionService.releaseConnection();
			}
			finally {
				if (result != null) result.close();
			}
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, null, e);
		}
		finally {
			if (statement != null) statement.close();
		}
		return identityValue;
	}
}
