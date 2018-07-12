/**
 * 
 */
package fr.epita.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.Exceptions.ConfigurationException;
import fr.epita.Exceptions.IdentityExceptions;

/**
 * @author Shruthi C
 *
 */
public class ConnectionService {

	private static  Connection connection;
	private static ResourceBundle props;
	
	/**
	 * ConnectionService
	 * @throws IdentityExceptions
	 */
	private ConnectionService() {    
			getConnection();
	}
	
	/**
	 * Get the Connection properties from the Configuration file
	 * @return User Configurations
	 * @throws ConfigurationException Throws Exception
	 */
	public static ResourceBundle getConnectionProperties()throws ConfigurationException{
		try {
			props = ResourceBundle.getBundle("fr.epita.config.config");
			if (props == null){
				throw new ConfigurationException();
			}
		}
		catch(Exception ex) {
			Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, ex);

		}
		return props;
	}
	
	/**
	 * Close the Connection
	 */
	public static void releaseConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Database Connection
	 * @return Connection
	 */
	public static Connection getConnection(){
		try {
			props = getConnectionProperties();
			if(props != null) {
				connection = DriverManager.getConnection(
						props.getString("connectionString"),
						props.getString("DbUser"), 
						props.getString("DbPassword"));
			}
			else
			{
				Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, props);
			}
			
		} catch (ConfigurationException|SQLException ex) {
			Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return connection;
		
	}

}
