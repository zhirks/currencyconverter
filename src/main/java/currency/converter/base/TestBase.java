package currency.converter.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import currency.converter.util.ExcelDataProvider;
import currency.converter.util.WebEventListener;
import currency.converter.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public static EventFiringWebDriver event_driver;
	public static WebEventListener eventListener;
	public static ExcelDataProvider data;
	public WebDriverWait wait;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\RATutor\\eclipse-workspace\\Troubleshoot_PaymentService\\CurrencyConverter\\src\\main\\java\\currency\\converter\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		event_driver = new EventFiringWebDriver(driver);
		// Use the WebEventListener class to be registered on EventFiringWebDriver
		eventListener = new WebEventListener();
		event_driver.register(eventListener);
		driver = event_driver;

		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
		data = new ExcelDataProvider();
	}

}
