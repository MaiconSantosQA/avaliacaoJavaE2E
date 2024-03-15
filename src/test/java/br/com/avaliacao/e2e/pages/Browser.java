package br.com.avaliacao.e2e.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Browser {

	private WebDriver driver;

	public Browser() {
		this.driver = new BrowserFactory().createWebDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	protected WebDriver getDriver() {
		return driver;
	}
	
	public HomePage getHomePage() {
		return new HomePage(driver);
	}

	public void clean() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
