package fr.epita.tests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TestDatabase {
	public static void main(String[] args) throws Exception {
		PreparedStatement pstmt = null;
		try {
			final Connection connection = getConnection();
			pstmt = connection
					.prepareStatement("INSERT INTO IDENTITIES (USERNAME, EMAIL, \"\r\n" + 
							"+ \"PASSWORD, FIRST_NAME, LAST_NAME)\"\r\n" + 
							"+\" values (?, ?, ?, ?, ?)");
			pstmt.setString(1, "shruthi");
			pstmt.setString(2, "shruthi@gmail.com");
			pstmt.setString(3, "shru");
			pstmt.setString(3, "Shruthi");
			pstmt.setString(3, "Chandrasekaran");
			pstmt.execute();
			connection.close();
		}
		finally {
			if (pstmt != null) pstmt.close();
		}
	}

	/**
	 * <h3>Description</h3>
	 * <p>
	 * This methods allows to ...
	 * </p>
	 *
	 * <h3>Usage</h3>
	 * <p>
	 * It should be used as follows :
	 *
	 * <pre>
	 * <code> ${enclosing_type} sample;
	 *
	 * //...
	 *
	 * sample.${enclosing_method}();
	 *</code>
	 * </pre>
	 * </p>
	 *
	 * @since $${version}
	 * @see Voir aussi $${link}
	 * @author ${user}
	 *
	 *         ${tags}
	 */
	@SuppressWarnings("unused")
	private static void testConnection() throws Exception {

		final String currentSchema = "";
		final Connection connection = getConnection();
		// Then I should get the "TEST" string in the currentSchema
		if (!currentSchema.equals("IdentityDb")) {
			throw new Exception("problem: connection not operational");
		}
	}

	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = "jdbc:derby://localhost:1527/IdentityDb;create=true";
		Connection connection = null;

		// When I connect
		connection = DriverManager.getConnection(url, "IdentityAdmin", "2399");
		return connection;
	}

}
