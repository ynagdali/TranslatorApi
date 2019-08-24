package config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Yamlmapper {

	@Autowired
	private YamlConfig ymlConfig;

	public Yamlmapper() {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		try {
			ymlConfig = mapper.readValue(new File("application.yml"), YamlConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSubscriptionKey() {
		return (String) ymlConfig.getApisubscriptionKey();
	}

	public String getTransalatorApiUrl() {
		return (String) ymlConfig.getTranslatorapiurl();
	}
	
	public String getBingTranslatorUrl() {
		return (String) ymlConfig.getBingtranslator();
	}

}
