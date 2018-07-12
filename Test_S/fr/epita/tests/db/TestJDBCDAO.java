package fr.epita.tests.db;

import java.util.List;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Services.DAO;


public class TestJDBCDAO {
	public static void main(String[] args) throws Exception {

		// given
		final DAO dao = null;
		final DataIdentity quentin = new DataIdentity("","shruthi","shruthi@gmail.com","shru","Shruthi","Chandrasekaran");
		dao.create(quentin);

		final DataIdentity criteria = new DataIdentity("shruthi", "shruthi@gmail.com");

		// when
		final List<DataIdentity> resultList = dao.search(criteria);

		// then
		boolean found = false;
		for (final DataIdentity identity : resultList) {
			if (identity.getUid().equals(quentin.getUid())) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("the search method did not work");
		}
		System.out.println("search successful!");

	}
}
