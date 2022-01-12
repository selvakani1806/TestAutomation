package com.rs.qa.automation.tests.cartpage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {

	private WebDriver driver;
	private String runplatform;
	private String chromeDriverpath;

	@BeforeTest
	public void setUp() throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "config.properties")));

		prop.getProperty("RUN_ENV");
		runplatform = prop.getProperty("RUN_PLATFORM");

		if (runplatform.equalsIgnoreCase("WINDOWS")) {
			chromeDriverpath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "Windows" + File.separator + "chromedriver.exe";
		}

		else {
			chromeDriverpath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "Linux" + File.separator + "chromedriver";
		}

		System.setProperty("webdriver.chrome.driver", chromeDriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 1)
	public void SampleOrderTest() throws InterruptedException, IOException {

		try {
			driver.manage().deleteAllCookies();
			driver.get("https://secure.rosettastone.com/us_en_store_view/checkout/cart/add/sku/92207/category_id/all");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception e) {

			Assert.assertTrue(false);
		}

	}

	@AfterTest(alwaysRun = true)
	public void closeBrowserConnection() throws Exception {

		driver.quit();
	}
}
