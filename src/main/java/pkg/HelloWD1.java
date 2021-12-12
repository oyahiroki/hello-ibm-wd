package pkg;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsOptions;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsResponse;
import com.ibm.watson.discovery.v2.Discovery;

public class HelloWD1 {

	public static void main(String[] args) throws Exception {

		HelloWD0.loadEnv();

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
		com.ibm.watson.discovery.v1.Discovery discovery = new com.ibm.watson.discovery.v1.Discovery("2019-04-30",
				authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		ListEnvironmentsOptions options = new ListEnvironmentsOptions.Builder().build();
		ListEnvironmentsResponse listResponse = discovery.listEnvironments(options).execute().getResult();

		System.err.println(listResponse);
	}

}
