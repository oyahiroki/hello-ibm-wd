package pkg;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;

public class HelloWD4 {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));

		Discovery discovery = new Discovery("2019-04-30", authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "system";
		String collectionId = "news-en";

		QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
		queryBuilder //
				.query("enriched_text.concepts.text:\"artificial intelligence\"")//
				.count(1)//
				.highlight(true)//
				.passagesFields("enriched_text.keywords.text,author")//
				.aggregation( //
						"nested(enriched_title.semantic_roles)" //
								+ ".filter(enriched_title.semantic_roles.subject.entities.type::Company)" //
								+ ".filter(enriched_title.semantic_roles.action.normalized:acquire)" //
								+ ".nested(enriched_title.semantic_roles.object.entities)" //
								+ ".filter(enriched_title.semantic_roles.object.entities.type::Company)" //
								+ ".term(enriched_title.semantic_roles.object.entities.text,count:20)" //
				);
		QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute().getResult();

		System.err.println(queryResponse);

	}

}
