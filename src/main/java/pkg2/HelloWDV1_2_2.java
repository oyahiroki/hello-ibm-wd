package pkg2;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListCollectionsOptions;
import com.ibm.watson.discovery.v1.model.ListCollectionsResponse;

import pkg.WDUtils;

public class HelloWDV1_2_2 {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials_tok_ad.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
		com.ibm.watson.discovery.v1.Discovery discovery = new com.ibm.watson.discovery.v1.Discovery("2019-04-30",
				authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "a4ee60a8-2a7b-4e35-a5b3-7c7b0d86e434"; // ←自分で作った環境

		ListCollectionsOptions listOptions = new ListCollectionsOptions.Builder(environmentId).build();
		ListCollectionsResponse listResponse = discovery.listCollections(listOptions).execute().getResult();

		System.err.println(listResponse);

	}

}
