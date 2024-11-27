package restConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	private static Properties props = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
