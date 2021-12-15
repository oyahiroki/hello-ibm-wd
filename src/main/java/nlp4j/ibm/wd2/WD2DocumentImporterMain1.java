package nlp4j.ibm.wd2;

import java.util.Date;

import nlp4j.Document;
import nlp4j.impl.DefaultDocument;
import nlp4j.util.DocumentUtil;
import pkg.WDUtils;

public class WD2DocumentImporterMain1 {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials_discovery_cm.env");

		Document doc = new DefaultDocument();
		{
			doc.putAttribute("date", new Date());
			doc.putAttribute("text", "今日はいい天気です．");
			doc.putAttribute("lang", "ja");
			doc.putAttribute("filename", "test.txt");
		}

		System.err.println(DocumentUtil.toJsonObject(doc));

		// Oya_CM
		String projectId = "7b043ca3-7eab-46e4-8f17-ffb61c0f4d5e";
		// Oya_CM
		String collectionId = "742f7980-b185-75d3-0000-017db207a8eb";
		String DISCOVERY_APIKEY = System.getProperty("DISCOVERY_APIKEY");
		String DISCOVERY_URL = System.getProperty("DISCOVERY_URL");

		WD2DocumentImporter importer = new WD2DocumentImporter();
		{
			importer.setProperty("projectId", projectId);
			importer.setProperty("collectionId", collectionId);
			importer.setProperty("DISCOVERY_APIKEY", DISCOVERY_APIKEY);
			importer.setProperty("DISCOVERY_URL", DISCOVERY_URL);
		}

		importer.importDocument(doc);
		importer.commit();

		importer.close();

	}

}
