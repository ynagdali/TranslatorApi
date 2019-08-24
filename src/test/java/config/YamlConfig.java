package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YamlConfig {
	
	private String apisubscriptionKey ;
    private String translatorapiurl;
    private String bingtranslator;
    
	public String getBingtranslator() {
		return bingtranslator;
	}
	public void setBingtranslator(String bingtranslator) {
		this.bingtranslator = bingtranslator;
	}
	public String getApisubscriptionKey() {
		return apisubscriptionKey;
	}
	public void setApisubscriptionKey(String apisubscriptionKey) {
		this.apisubscriptionKey = apisubscriptionKey;
	}
	public String getTranslatorapiurl() {
		return translatorapiurl;
	}
	public void setTranslatorapiurl(String translatorapiurl) {
		this.translatorapiurl = translatorapiurl;
	}
        
}