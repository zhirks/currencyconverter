package currency.converter.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import currency.converter.base.TestBase;

public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIMEOUT = 40;
	public static long IMPLICIT_WAIT = 40;

	public void hoverElement(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).build().perform();
	}


	public static void takeScreenshotAtEndOfTest() throws IOException {
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
		System.out.println(formatter.format(calendar.getTime()));
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		//FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + formatter.format(calendar.getTime()) + ".png"));
	}
	
	

}
