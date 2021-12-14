package pkg3;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsOptions;
import com.ibm.watson.discovery.v1.model.ListEnvironmentsResponse;
import com.ibm.watson.discovery.v2.Discovery;
import com.ibm.watson.discovery.v2.model.AddDocumentOptions;
import com.ibm.watson.discovery.v2.model.DocumentAccepted;
import com.ibm.watson.discovery.v2.model.ListCollectionsOptions;
import com.ibm.watson.discovery.v2.model.ListCollectionsResponse;
import com.ibm.watson.discovery.v2.model.ListProjectsResponse;

import pkg.WDUtils;

public class HelloWD_V2_3_AddADocument {

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

		JsonObject json = new JsonObject();
		{
			json.addProperty("lang", "ja");
			json.addProperty("text", "これはテストです。明日はいい天気です。私は走って学校に行きます。");
		}

		// https://cloud.ibm.com/apidocs/discovery-data?code=java#adddocument

		String projectId = "7b043ca3-7eab-46e4-8f17-ffb61c0f4d5e";
		String collectionId = "742f7980-b185-75d3-0000-017db207a8eb";
		
		collectionId = "c2fb58fb-b107-bfc0-0000-017db995672e";
		
		String fileName = "hello" + System.currentTimeMillis() + ".json";

		{
			IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));
			Discovery v2Discovery = new Discovery("2020-08-30", authenticator);
			v2Discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));
			AddDocumentOptions options = new AddDocumentOptions.Builder() //
					.projectId(projectId) //
					.collectionId(collectionId) //
					.file(new ByteArrayInputStream(json.toString().getBytes(StandardCharsets.UTF_8))) //
					.filename(fileName) //
					.fileContentType("application/json") //
					.build(); //

			DocumentAccepted response = v2Discovery.addDocument(options).execute().getResult();

			System.out.println(response);

		}
	}

}
