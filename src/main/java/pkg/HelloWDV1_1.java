package pkg;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsOptions;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsResponse;

public class HelloWDV1_1 {

	/**
	 * V1 List Environments
	 */
	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
		Discovery discovery = new Discovery("2019-04-30", authenticator); // V1
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		ListEnvironmentsOptions options = new ListEnvironmentsOptions.Builder().build();
		ListEnvironmentsResponse listResponse = discovery.listEnvironments(options).execute().getResult();

		System.err.println(listResponse);
	}

}
