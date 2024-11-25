package rest;

import restConfig.ConfigManager;

public class AuthUtil {

	// Method to get Bearer Token from config file
	public static String getBearerToken() {
		return ConfigManager.getProperty("auth.bearer.token"); // Fetch from config
	}

	// Method to set Authorization header
	public static String getAuthHeader() {
		return "Bearer " + getBearerToken();
	}

}
