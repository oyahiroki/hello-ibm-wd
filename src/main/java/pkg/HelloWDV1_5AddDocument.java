package pkg;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.AddDocumentOptions;
import com.ibm.watson.discovery.v1.model.DocumentAccepted;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;

public class HelloWDV1_5AddDocument {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));

		Discovery discovery = new Discovery("2019-04-30", authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "fc6aa9cd-e4a5-4856-82f7-24df189d8e03";
		String collectionId = "1c661dc1-69c1-49f2-8784-3c569b897eff";

		JsonObject jsonObj = new JsonObject();
		{
			jsonObj.addProperty("lang", "ja");
			jsonObj.addProperty("date", "2021-11-01T09:00:00Z");
			jsonObj.addProperty("text", "今日はとてもいい天気です。"); // emotion: unsupported text language:
		}

		InputStream inputStream = new ByteArrayInputStream(jsonObj.toString().getBytes(StandardCharsets.UTF_8));

		AddDocumentOptions.Builder builder = new AddDocumentOptions.Builder(environmentId, collectionId);
		builder.file(inputStream);
		builder.filename("test" //
				+ "" + System.currentTimeMillis() //
				+ ".json");
		{
			JsonObject metaJsonObj = new JsonObject();
			metaJsonObj.addProperty("author", "Hiroki Oya");
			jsonObj.add("metadata", metaJsonObj);
			builder.metadata(metaJsonObj.toString());
		}

		System.err.println(jsonObj.toString());

		builder.fileContentType(HttpMediaType.APPLICATION_JSON);
		DocumentAccepted response = discovery.addDocument(builder.build()).execute().getResult();

		System.err.println(response);

	}

}
