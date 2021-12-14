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

public class HelloWD5 {

	public static void main(String[] args) throws Exception {

		// ドキュメントの追加
		// どんなメタデータを追加できるのか、検証する必要がある。

		WDUtils.loadEnv("ibm-credentials.env");

		IamAuthenticator authenticator = new IamAuthenticator(System.getProperty("DISCOVERY_APIKEY"));

		Discovery discovery = new Discovery("2019-04-30", authenticator);
		discovery.setServiceUrl(System.getProperty("DISCOVERY_URL"));

		String environmentId = "fc6aa9cd-e4a5-4856-82f7-24df189d8e03";
		String collectionId = "1c661dc1-69c1-49f2-8784-3c569b897eff";

//		String json = "{" //
//				+ "'document':{"
//				+ "'document_id':'001',"
//				+ "'title':'test'," // これは必須?
//				+ "'text':['これは日本語ドキュメントのテストです。']" //
//				+ "}"; //

		JsonObject jsonObj = new JsonObject();
//		{
////			jsonObj.addProperty("id", "001"); // 	The document contains a field named 'id'. Since the field name is reserved, it will be ignored.
////			jsonObj.addProperty("text", "これはテストです。"); // emotion: unsupported text language: ja
//			jsonObj.addProperty("text", "これはテストです。今日はテレビで野球を見た。"); // emotion: unsupported text language: ja
//			// text_ja はテキストとして処理されなかった。
//			jsonObj.addProperty("lang", "ja");
//		}
		{
			jsonObj.addProperty("lang", "ja");
			jsonObj.addProperty("text", "私は毎日学校に走って行っている。今日は家でテレビで野球をたくさん見た。"); // emotion: unsupported text language:
																				// ja
		}

		{
			JsonArray keywords = new JsonArray();
			{
				JsonObject kwd = new JsonObject();
				kwd.addProperty("text", "私");
				keywords.add(kwd);
			}
			jsonObj.add("keywords", keywords);
		}

//		String json = "{" //
//				+ "'document':{" //
//				+ "'id':'001'," + "'title':'test'," // これは必須?
//				+ "'text':'これは日本語ドキュメントのテストです。'," //
//				+ "\"crawl_date\": \"2021-10-24T13:09:48Z\"" //
//				+ "}"; //
//
//		json = json.replace("'", "\""); // たぶん必須

		System.err.println(jsonObj.toString());

		InputStream inputStream = new ByteArrayInputStream(jsonObj.toString().getBytes(StandardCharsets.UTF_8));

		AddDocumentOptions.Builder builder = new AddDocumentOptions.Builder(environmentId, collectionId);
		builder.file(inputStream);
		builder.filename("test" //
				+ "" + System.currentTimeMillis() //
				+ ".json");
		String metaJson;
		{
			JsonObject metaJsonObj = new JsonObject();
			metaJsonObj.addProperty("author", "Hiroki Oya");
//			{
//				JsonArray arr = new JsonArray();
//				arr.add("aaa"); // ["a","a","a"] に分解されてしまう。
//				arr.add("bbb");
//				metaJsonObj.add("tags", arr);
//			}
			metaJsonObj.addProperty("tags", "aaa");
			metaJsonObj.addProperty("tags", "bbb");
			metaJson = metaJsonObj.toString();
		}
		builder.metadata(metaJson);

		builder.fileContentType(HttpMediaType.APPLICATION_JSON);
		DocumentAccepted response = discovery.addDocument(builder.build()).execute().getResult();

		System.err.println(response);

	}

}
