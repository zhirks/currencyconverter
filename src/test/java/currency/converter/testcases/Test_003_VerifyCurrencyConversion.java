package currency.converter.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import currency.converter.base.TestBase;
import currency.converter.pages.CurrencyConversionCalculatorPage;

public class Test_003_VerifyCurrencyConversion extends TestBase {

	public Test_003_VerifyCurrencyConversion() {
		super();
	}

	CurrencyConversionCalculatorPage currExCalcPage;

	@BeforeMethod
	public void setup() {
		initialization();
		currExCalcPage = new CurrencyConversionCalculatorPage();
	}

	@Test(priority = 1)
	public void verifyConversionRateDifference() {
		Boolean flag = currExCalcPage.verifyBankProviderRateDiff_swedBank();
		Assert.assertTrue(flag);

		Boolean cflag = currExCalcPage.verifyBankProviderRateDiff_citadeleBank();
		Assert.assertTrue(cflag);

		Boolean lflag = currExCalcPage.verifyBankProviderRateDiff_luminorBank();
		Assert.assertTrue(lflag);
	}
	
	@Test(priority = 2)
	public void verifyConversionRate() {
		Boolean flag = currExCalcPage.verifyBankProviderRateDiff_swedBank();
		Assert.assertTrue(flag);
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		driver.quit();
	}

}
