package com.tranlatortext.selenium.translatortextapi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class PageObject {

protected WebDriver driver;
private WebDriverWait wait;

	public PageObject(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	public void selectInputLang(String inputLang) {
		Select inDrop = new Select(driver.findElement(By.id("tta_srcsl")));
		inDrop.selectByValue(inputLang);
	}
	
	public void selectOutputLang(String outputLang) {
		Select outDrop = new Select(driver.findElement(By.id("tta_tgtsl")));
		outDrop.selectByValue(outputLang);
	}
	
	public void enterInputText(String inputText) {
		WebElement inputField = driver.findElement(By.id("tta_input")); 
		inputField.clear();
		inputField.click();
		inputField.sendKeys(inputText);
	}
	
	public String getConvertedText(String inputLang, String outputLang, String inputText ){
		selectInputLang(inputLang);
		selectOutputLang(outputLang);
		enterInputText(inputText);
		WebElement element = driver.findElement(By.id("tta_output"));
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(element,"...")));
		String output = element.getAttribute("value");
		return output;
	}


	
}