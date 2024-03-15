package br.com.avaliacao.acceptance;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import br.com.avaliacao.e2e.pages.BrowserFactory;

public class E2ETestBase {

	private static WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}

	@BeforeAll
	public static void setUpAll() {
		driver = new BrowserFactory().createWebDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@BeforeEach
	public void setUp() {
        driver.get("https://jsonplaceholder.typicode.com/");
	}

	@AfterEach
	public void cleanUp() {
        driver.get("https://jsonplaceholder.typicode.com/");
		driver.manage().deleteAllCookies();
	}

	@AfterAll
	public static void tearDown() {
		driver.close();
	}

}
