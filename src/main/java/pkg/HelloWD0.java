package pkg;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class HelloWD0 {

	public static void loadEnv() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream(new File("ibm-credentials.env")));

		for (String key : props.stringPropertyNames()) {
			Object v = props.get(key);
			System.setProperty(key.toString(), v.toString());
		}
	}

	public static void main(String[] args) throws Exception {
	}

}
