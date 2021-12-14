package pkg;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListCollectionsOptions;
import com.ibm.watson.discovery.v1.model.ListCollectionsResponse;

public class HelloWD2b {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
		com.ibm.watson.discovery.v1.Discovery discovery = new com.ibm.watson.discovery.v1.Discovery("2019-04-30",
				authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "fc6aa9cd-e4a5-4856-82f7-24df189d8e03";

		ListCollectionsOptions listOptions = new ListCollectionsOptions.Builder(environmentId).build();
		ListCollectionsResponse listResponse = discovery.listCollections(listOptions).execute().getResult();

		System.err.println(listResponse);

	}

}
