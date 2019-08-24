package com.tranlatortext.selenium.translatortextapi;

import java.io.*;
import org.json.*;
import okhttp3.*;
import okhttp3.OkHttpClient;
import config.Yamlmapper;

public class CallTranslatorTextApi {

	private String subscriptionKey;
	private String url;
	
	private OkHttpClient client ;
	
	CallTranslatorTextApi(Yamlmapper mapper)
	{
		this.client = new OkHttpClient();
		this.subscriptionKey = mapper.getSubscriptionKey()  ;
		this.url = mapper.getTransalatorApiUrl();
	}
	
	public String Post(String inputLang, String outputLang, String inputText) throws IOException {
		String finalurl = url + "&from=" + inputLang + "&to=" + outputLang;
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "[{\n\t\"Text\": \"" + inputText + "\"\n}]");
		Request request = new Request.Builder().url(finalurl).post(body)
				.addHeader("Ocp-Apim-Subscription-Key", subscriptionKey).addHeader("Content-type", "application/json")
				.build();
		Response response = client.newCall(request).execute();
		finalurl = "";
		return response.body().string().toString();
	}

	public String parseJson(String json_text) {
		JSONArray jarry = new JSONArray(json_text);
		String convertedText = "";
		JSONObject jobj = jarry.getJSONObject(0);
		JSONArray jsonarr = (JSONArray) jobj.get("translations");
		JSONObject jobobj = jsonarr.getJSONObject(0);
		convertedText = jobobj.get("text").toString();
		return convertedText;
	}
}
