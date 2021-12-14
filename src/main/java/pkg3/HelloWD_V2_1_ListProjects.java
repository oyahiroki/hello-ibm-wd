package pkg3;

import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsOptions;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsResponse;
import com.ibm.watson.discovery.v2.Discovery;
import com.ibm.watson.discovery.v2.model.ListProjectsResponse;

import pkg.WDUtils;

public class HelloWD_V2_1_ListProjects {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials_discovery_cm.env");

		{
//			IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
//			com.ibm.watson.discovery.v1.Discovery discovery = new com.ibm.watson.discovery.v1.Discovery("2019-04-30",
//					authenticator);
//			discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));
//
//			ListEnvironmentsOptions options = new ListEnvironmentsOptions.Builder().build();
//			ListEnvironmentsResponse listResponse //
//					= discovery.listEnvironments(options).execute().getResult(); // throws Exception
//
//			System.err.println(listResponse);
		}

		{
			IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
			Discovery v2Discovery = new Discovery("2020-08-30", authenticator);
			v2Discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));
			ListEnvironmentsOptions options = new ListEnvironmentsOptions.Builder().build();
			Response<ListProjectsResponse> listResponse //
					= v2Discovery.listProjects().execute();
			System.err.println(listResponse.getStatusCode());
			System.err.println(listResponse.getResult());

		}
	}

}
