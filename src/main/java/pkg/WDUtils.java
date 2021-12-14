package pkg;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class WDUtils {
	/**
	 * Load and set System Properties from File
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public static void loadEnv(String fileName) throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream(new File(fileName)));

		for (String key : props.stringPropertyNames()) {
			Object v = props.get(key);
			System.setProperty(key.toString(), v.toString());
		}
	}
}
