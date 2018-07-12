/**
 * 
 */
/**
 * @author Shruthi C
 *
 */
package fr.epita.Services.config;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Config{
	Properties configFile = new Properties();
	InputStream input = null;
	private static final Logger log = Logger.getLogger(Config.class.getName());
	public static final PrintStream myout =  new PrintStream(new FileOutputStream(FileDescriptor.out));
	
	
	public Config() {
		
		try {
			String filename = "";
			input = this.getClass().getClassLoader().
					  getResourceAsStream(filename);
    		if(input==null){
    	            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, filename);
    		    return;
    		}

    		//load a properties file from class path, inside static method
    		configFile.load(input);
			
		}
		catch(Exception ex) {
			log.log(Level.SEVERE, null, ex);
		}
	}
	public String getProperty(String key) {
		return this.configFile.getProperty(key);
	}
}