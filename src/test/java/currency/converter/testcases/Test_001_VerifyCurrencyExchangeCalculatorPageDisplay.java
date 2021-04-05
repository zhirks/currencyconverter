package currency.converter.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import currency.converter.base.TestBase;
import currency.converter.pages.CurrencyConversionCalculatorPage;

public class Test_001_VerifyCurrencyExchangeCalculatorPageDisplay extends TestBase {

	public Test_001_VerifyCurrencyExchangeCalculatorPageDisplay() {
		super();
	}

	CurrencyConversionCalculatorPage currExCalcPage;

	@BeforeMethod
	public void setup() {
		initialization();
		currExCalcPage = new CurrencyConversionCalculatorPage();
	}

	@Test(priority = 1)
	public void verifyCurrencyCalculatorTitle() {
		String pageTitleExpected = data.getStringData("calculator", 1, 0);
		Assert.assertEquals(currExCalcPage.getPageTitle(), pageTitleExpected);
	}

	@Test(priority = 2)
	public void verifyCurrencyCalculatorLogo() {
		Boolean pageLogoFlag = currExCalcPage.checkPageLogo();
		Assert.assertTrue(pageLogoFlag);
	}

	@Test(priority = 3)
	public void verifyCurrencyCalculatorHeader() {
		String pageHeaderExpected = data.getStringData("calculator", 1, 1);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Currency exchange calculator')]")));
		Assert.assertEquals(currExCalcPage.getPageHeader(), pageHeaderExpected);
		currExCalcPage.getPageHeader();
	}

	@Test(priority = 4)
	public void verifyRateTableFilterDisplay() {
		Boolean rateTableFlag = currExCalcPage.checkRateTableFilterDisplay();
		Assert.assertTrue(rateTableFlag);
	}

	@Test(priority = 5)
	public void verifyCurrencyPageNote() {
		String currencyNotes = currExCalcPage.verifyCurrencyNotes();
		Assert.assertEquals(currencyNotes, "Test");
	}

	@Test(priority = 6)
	public void verifySellInputField() {
		Boolean buyFlag = currExCalcPage.verifySellInput("1000");
		Assert.assertTrue(buyFlag);
	}

	@Test(priority = 7)
	public void verifyBuyInputField() {
		Boolean sellFlag = currExCalcPage.verifyBuyInput("1000");
		Assert.assertTrue(sellFlag);
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		driver.quit();
	}

}
