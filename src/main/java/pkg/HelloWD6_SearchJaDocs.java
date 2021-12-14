package pkg;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListCollectionsOptions;
import com.ibm.watson.discovery.v1.model.ListCollectionsResponse;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;

public class HelloWD6_SearchJaDocs {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
		com.ibm.watson.discovery.v1.Discovery discovery = new com.ibm.watson.discovery.v1.Discovery("2019-04-30",
				authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "fc6aa9cd-e4a5-4856-82f7-24df189d8e03";
		String collectionId = "1c661dc1-69c1-49f2-8784-3c569b897eff";

		QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
		queryBuilder.query("text:テスト") //
				.count(10).highlight(true).passagesFields("enriched_text.keywords.text,author");
		QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute().getResult();

		System.err.println(queryResponse);

	}

}
