package pkg;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListCollectionsOptions;
import com.ibm.watson.discovery.v1.model.ListCollectionsResponse;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;

public class HelloWD3 {

	public static void main(String[] args) throws Exception {

		HelloWD0.loadEnv();

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
		com.ibm.watson.discovery.v1.Discovery discovery = new com.ibm.watson.discovery.v1.Discovery("2019-04-30",
				authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "system";

		String collectionId = "news-en";

		QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
		queryBuilder.query("text:log4j").count(10).highlight(true).passagesFields("enriched_text.keywords.text,author");
		QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute().getResult();

		System.err.println(queryResponse);

	}

}
