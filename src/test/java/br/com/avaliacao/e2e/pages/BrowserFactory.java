package br.com.avaliacao.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowserFactory {

	public WebDriver createWebDriver() {
		String webdriver = System.getProperty("browser", "chrome");
		switch (webdriver) {
			case "firefox":
				return initFirefoxDriver();
			case "chrome":
				return initChromeDriver();
			default:
				return new HtmlUnitDriver();
		}
	}

	private  WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				"drivers/chromedriver.exe");
		return new ChromeDriver();
	}

	private  WebDriver initFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver",
				"drivers/geckodriver.exe");
		return new FirefoxDriver();
	}
}
