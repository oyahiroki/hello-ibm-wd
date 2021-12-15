package nlp4j.ibm.wd2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nlp4j.Document;
import nlp4j.crawler.JsonLineSeparatedCrawler;
import nlp4j.impl.DefaultDocument;
import nlp4j.util.DocumentUtil;
import pkg.WDUtils;

public class WD2DocumentImporterMain2 {

	public static void main(String[] args) throws Exception {

		WDUtils.loadEnv("ibm-credentials_discovery_cm.env");

		List<Document> docs = new ArrayList<Document>();
		List<Document> docs1;
		nlp4j.crawler.JsonLineSeparatedCrawler crawler = new JsonLineSeparatedCrawler();
		crawler.setProperty("file", "/usr/local/data/twitter/twitter_rakuten_json1.txt");
		docs1 = crawler.crawlDocuments();
		{
			int count = 0;
			for (Document doc : docs1) {

				Document d = new DefaultDocument();
				d.putAttribute("text", doc.getAttributeAsString("text"));
				d.putAttribute("created_at", doc.getAttributeAsDate("created_at"));
				d.putAttribute("retweet_count", doc.getAttribute("retweet_count"));
				docs.add(d);
				count++;
				if (count >= 300) {
					break;
				}
			}
		}

		{
			nlp4j.importer.DebugImporter importer = new nlp4j.importer.DebugImporter();
			importer.importDocuments(docs);
		}

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

		importer.importDocuments(docs);
		importer.commit();
		importer.close();

	}

}
