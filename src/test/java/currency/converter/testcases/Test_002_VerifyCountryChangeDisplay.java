package currency.converter.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import currency.converter.base.TestBase;
import currency.converter.pages.CurrencyConversionCalculatorPage;

public class Test_002_VerifyCountryChangeDisplay extends TestBase {

	public Test_002_VerifyCountryChangeDisplay() {
		super();
	}

	CurrencyConversionCalculatorPage currExCalcPage;

	@BeforeMethod
	public void setup() {
		initialization();
		currExCalcPage = new CurrencyConversionCalculatorPage();
	}

	@Test(priority = 1)
	public void verifyCountryChangeTest() {
		// Verifying default currency is loaded in drop-down for the selected country
		String countryCurrency = data.getStringData("country", 1, 2);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='flag-icon-small flag-icon-lt']")));

		// Check if rate table is not empty
		Boolean rateTableData = currExCalcPage.changeCountryDisplay();
		Assert.assertNotNull(rateTableData, "Table is empty!");

		// Check if default currency loaded
		String sellCurrDropVal = currExCalcPage.verifyRateTableFilter();
		Assert.assertEquals(sellCurrDropVal, countryCurrency);
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		driver.quit();
	}

}
