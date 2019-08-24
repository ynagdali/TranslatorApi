package com.tranlatortext.selenium.translatortextapi;

import java.io.IOException;
import java.util.*;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import config.CsvReader;
import config.Yamlmapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTestClass {

	private WebDriver driver;
	private WebDriverWait wait;
	private Yamlmapper yamlMapper;
	private CallTranslatorTextApi translateRequest;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		yamlMapper = new Yamlmapper();
		driver.get(yamlMapper.getBingTranslatorUrl());
		translateRequest = new CallTranslatorTextApi(yamlMapper);
	}

	@AfterClass
	public void cleanUp() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "testData")
	public void testCoversion(String testNo, String fromlanguage, String tolanguage, String inputtext) {
		String apiResponse = "";
		if (!testNo.equals("TestNo")) {

			try {
				String response = translateRequest.Post(fromlanguage, tolanguage, inputtext);
				apiResponse = translateRequest.parseJson(response);
			} catch (Exception e) {
				apiResponse = "";
				e.printStackTrace();
			}

			PageObject pageObj = new PageObject(driver, wait);
			System.out.println("This is test case number : " + testNo);
			String convertedText = pageObj.getConvertedText(fromlanguage, tolanguage, inputtext);
			System.out.println("covertedText via Bing "+convertedText);
			System.out.println("covertedText via Api "+apiResponse);
			Assert.assertEquals(convertedText.toLowerCase(), apiResponse.toLowerCase());
			System.out.println("\n\n");
		}

	}

	@DataProvider(name = "testData")
	public Iterator<String[]> provider() throws InterruptedException, IOException {
		CsvReader csv = new CsvReader();
		Iterator<String[]> itr = csv.getTestDataFromCsv();
		return itr;
	}
}
