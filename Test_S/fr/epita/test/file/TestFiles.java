package fr.epita.test.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import fr.epita.Datamodel.DataIdentity;
import fr.epita.Services.DAO;

public class TestFiles {
	public static void main(String[] args) throws Exception {
		// testWriteThenDisplayList();

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
	private static DataIdentity readIdentityFromConsole(final Scanner scanner) {
		final DataIdentity identity = new DataIdentity();
		System.out.println("please input the user name : ");
		String line = scanner.nextLine();
		identity.setUserName(line);
		System.out.println("please input the email");
		line = scanner.nextLine();
		identity.setEmail(line);
		System.out.println("please input first name");
		line = scanner.nextLine();
		identity.setFirstname(line);
		System.out.println("please input last name");
		line = scanner.nextLine();
		identity.setLastname(line);
		System.out.println("please input password");
		line = scanner.nextLine();
		identity.setPassword(line);
		return identity;
	}

	/**
	 * <h3>Description</h3>
	 * <p>This methods allows to ...</p>
	 *
	 * <h3>Usage</h3>
	 * <p>It should be used as follows :
	 *
	 * <pre><code> ${enclosing_type} sample;
	 *
	 * //...
	 *
	 * sample.${enclosing_method}();
	 *</code></pre>
	 * </p>
	 *
	 * @since $${version}
	 * @see Voir aussi $${link}
	 * @author ${user}
	 *
	 * ${tags}
	 */
	private static void writeIdentity(final DataIdentity identity, final PrintWriter writer) {
		writer.println("---");
		writer.println(identity.getUsername());
		writer.println(identity.getEmail());
		writer.println(identity.getFirstname());
		writer.println(identity.getLastname());
		writer.println(identity.getPassword());
		writer.println("---");
	}

	/**
	 * <h3>Description</h3>
	 * <p>This methods allows to ...</p>
	 *
	 * <h3>Usage</h3>
	 * <p>It should be used as follows :
	 *
	 * <pre><code> ${enclosing_type} sample;
	 *
	 * //...
	 *
	 * sample.${enclosing_method}();
	 *</code></pre>
	 * </p>
	 *
	 * @since $${version}
	 * @see Voir aussi $${link}
	 * @author ${user}
	 *
	 * ${tags}
	 */
	private static PrintWriter initializePrintWriter(final File file) throws IOException, FileNotFoundException {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		final FileOutputStream fos = new FileOutputStream(file, true);
		final PrintWriter writer = new PrintWriter(fos);
		return writer;
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
	private static void basicFileTest() throws IOException, FileNotFoundException {
		final File directory = new File("C:\\tmp\\2018s1");
		if (directory.exists()) {
			System.out.println("the directory exists");
		} else {
			System.out.println("the directory does not exist, cannot continue");
			return;
		}
		final File file = new File("C:\\tmp2\\2018s1\\identities.txt");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		final FileOutputStream fos = new FileOutputStream(file, true);
		final PrintWriter writer = new PrintWriter(fos);
		writer.println("hello!");
		writer.flush();
		fos.close();
		writer.close();
	}
}
