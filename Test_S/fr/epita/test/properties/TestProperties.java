package fr.epita.test.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;


/**
 * <h3>Description</h3>
 * <p>This class allows to ...</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre></p>
 * 
 * @since $${version}
 * @see also $${link}
 * @author ${user}
 *
 * ${tags}
 */
public class TestProperties {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		final Properties properties = new Properties();
		properties.load(new FileInputStream(new File("Test_S/test.properties")));
		final Set<Object> keySet = properties.keySet();
		System.out.println(properties.getProperty("db.url"));
		System.out.println(keySet);
	}
}
