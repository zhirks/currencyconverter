package currency.converter.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import currency.converter.util.TestUtil;

import currency.converter.base.TestBase;

public class CurrencyConversionCalculatorPage extends TestBase {

	TestUtil testUtil = new TestUtil();

	@FindBy(xpath = "//a[@class='logo-wrapper']/img")
	WebElement curCalcLogo;

	@FindBy(xpath = "//h1[contains(text(),'Currency exchange calculator')]")
	WebElement currencyCalculatorHeader;

	@FindBy(xpath = "//div[@class='alert alert-info']")
	WebElement currencyExchangeNotes;

	@FindBy(xpath = "//form[@class='form-inline ng-pristine ng-valid']")
	WebElement rateTableFilter;

	// Sell section elements
	@FindBy(xpath = "//label[@class='ng-binding'][contains(text(),'Sell')]")
	WebElement sellLabel;

	/*
	 * @FindBy(xpath =
	 * "//div[@class='form-group'][1]/input[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']"
	 * ) WebElement sellInputField;
	 */

	@FindBy(xpath = "//div[@class='form-group'][1]/div[@class='ui-select-container ui-select-bootstrap dropdown ng-valid ng-not-empty']")
	WebElement sellCurDropdown;

	@FindBy(xpath = "//div[@class='form-group'][1]/div[@class='ui-select-container ui-select-bootstrap dropdown ng-valid ng-not-empty']/div/span")
	WebElement sellCurDropdownDefault;

	@CacheLookup
	@FindBy(xpath = "//span[@class='flag-icon-small flag-icon-lt']")
	WebElement countryDropdownIcon;

	@CacheLookup
	@FindBy(xpath = "//button[@id='countries-dropdown']")
	WebElement countryDropdown;

	@FindBy(xpath = "//div[@class='dropup open']/ul[@class='dropdown-menu']/li/a/span[@class='flag-icon-small flag-icon-gb']")
	WebElement countryValue_UK;

	@FindBy(xpath = "//tbody[@class='ng-scope']/tr/td")
	WebElement rateTableResults;

	@FindBy(xpath = "//div[@id='currency-exchange-app']/div/div/div[2]/div/form/div[1]/input")
	WebElement sellInputField;

	// div[@id='currency-exchange-app']/div/div/div[2]/div/form/div[3]/input
	@FindBy(xpath = "//div[@id='currency-exchange-app']/div/div/div[2]/div/form/div[1]/input")
	WebElement buyInputField;

	@FindBy(xpath = "//div[@class='form-group']/button[@class='btn ng-binding'][contains(text(),'Filter')]")
	WebElement filterBtn;

	@FindBy(xpath = "//div[@class='form-group']/button[@class='btn ng-binding'][contains(text(),'Filter')]")
	WebElement usdOfficialRate;

	public CurrencyConversionCalculatorPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public Boolean checkPageLogo() {
		Boolean pageLogoDisplayed = curCalcLogo.isDisplayed();
		return pageLogoDisplayed;
	}

	public String getPageHeader() {
		String pageHeader = currencyCalculatorHeader.getText();
		return pageHeader;
	}

	public Boolean checkRateTableFilterDisplay() {
		Boolean rateTableFilterFlag = rateTableFilter.isDisplayed();
		return rateTableFilterFlag;
	}

	public Boolean changeCountryDisplay() {
		testUtil.hoverElement(countryDropdownIcon);
		countryDropdownIcon.click();
		testUtil.hoverElement(countryDropdown);
		countryDropdown.click();
		testUtil.hoverElement(countryValue_UK);
		countryValue_UK.click();
		Boolean tableData = rateTableResults.isDisplayed();
		return tableData;
	}

	public String verifyRateTableFilter() {
		testUtil.hoverElement(sellCurDropdownDefault);
		String sellCurrDefaultVal = sellCurDropdownDefault.getText();
		return sellCurrDefaultVal;
	}

	public String verifyCurrencyNotes() {
		String notes = currencyExchangeNotes.getText();
		return notes;
	}

	public Boolean verifySellInput(String value) {
		testUtil.hoverElement(sellInputField);
		sellInputField.click();
		sellInputField.sendKeys(value);
		filterBtn.click();

		Boolean flag = buyInputField.getText().isEmpty();
		return flag;
	}

	public Boolean verifyBuyInput(String value) {
		testUtil.hoverElement(buyInputField);
		buyInputField.click();
		buyInputField.sendKeys(value);
		filterBtn.click();

		Boolean flag = sellInputField.getText().isEmpty();
		return flag;
	}

	public Boolean verifyBankProviderRateDiff_swedBank() {
		int i = 1;
		String payseraText = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[4]/span/span/span[1]")).getText();
		double payseraAmount = Double.parseDouble(payseraText);

		String swedBank = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[5]/span/span/span[1]")).getText();
		double swedBankAmount = Double.parseDouble(swedBank);
		Boolean swedBankFlag = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[5]/span/span/span[2]"))
				.isDisplayed();

		/*
		 * String citadele =
		 * driver.findElement(By.xpath("//tbody/tr['"+i+"']/td[7]/span/span/span[1]")).
		 * getText(); double citadeleAmount = Double.parseDouble(citadele); Boolean
		 * citadeleFlag =
		 * driver.findElement(By.xpath("//tbody/tr['"+i+"']/td[7]/span/span/span[2]")).
		 * isDisplayed();
		 * 
		 * String luminor =
		 * driver.findElement(By.xpath("//tbody/tr['"+i+"']/td[8]/span/span/span[1]")).
		 * getText(); double luminorAmount = Double.parseDouble(luminor); Boolean
		 * luminorFlag =
		 * driver.findElement(By.xpath("//tbody/tr['"+i+"']/td[8]/span/span/span[2]")).
		 * isDisplayed();
		 */

		if ((payseraAmount - swedBankAmount) > 0) {
			return swedBankFlag;
		}
		return swedBankFlag;

	}

	public Boolean verifyBankProviderRateDiff_citadeleBank() {
		int i = 1;
		String payseraText = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[4]/span/span/span[1]")).getText();
		double payseraAmount = Double.parseDouble(payseraText);

		String citadele = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[7]/span/span/span[1]")).getText();
		double citadeleAmount = Double.parseDouble(citadele);
		Boolean citadeleFlag = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[7]/span/span/span[2]"))
				.isDisplayed();

		if ((payseraAmount - citadeleAmount) > 0) {
			return citadeleFlag;
		}
		return citadeleFlag;

	}
	
	public Boolean verifyBankProviderRateDiff_luminorBank() {
		int i = 1;
		String payseraText = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[4]/span/span/span[1]")).getText();
		double payseraAmount = Double.parseDouble(payseraText);

		String luminor = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[8]/span/span/span[1]")).getText();
		double luminorAmount = Double.parseDouble(luminor);
		Boolean luminorFlag = driver.findElement(By.xpath("//tbody/tr['" + i + "']/td[8]/span/span/span[2]"))
				.isDisplayed();

		if ((payseraAmount - luminorAmount) > 0) {
			return luminorFlag;
		}
		return luminorFlag;

	}
}
