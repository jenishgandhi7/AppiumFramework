package Practies.AppiumFramework;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.base_Utilities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.EC_CheckOutPage;
import pageObjects.EC_FormPage;
import pageObjects.EC_ShopingPage;

public class Ecommerce_Shopping_APP_tc extends base_class {
	static AndroidDriver<AndroidElement> driver;

	static void addtocart(String product) {

		String scrollViewContainer_finder = "new UiSelector().resourceIdMatches(\"com.androidsample.generalstore:id/rvProductList\")";
		String neededElement_finder = "new UiSelector().textMatches(\"" + product + "\")";

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(" + scrollViewContainer_finder + ")"
				+ ".scrollIntoView(" + neededElement_finder + ")"));

//		driver.findElement(MobileBy.AndroidUIAutomator(
		// "new UiScrollable(new
		// UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new
		// UiSelector().textMatches(\""+product+"\").instance(0))"));

		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

		for (int i = 0; i < count; i++) {
			String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

			if (text.equalsIgnoreCase(product)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}

		}
	}
	@BeforeTest
	public static void kiilALlNodes() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	}


	public static double getAmount(String value) {

		value = value.substring(1).trim();

		double amount2value = Double.parseDouble(value);

		return amount2value;

	}

	@Test
	public void testCase5() throws IOException, InterruptedException {
		// appium code
		base_Utilities.startServer();

		AndroidDriver<AndroidElement> driver = Capabilities("ApplicationName");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(5000);
		EC_FormPage fp = new EC_FormPage(driver);
		
		fp.openCountryList.click();
		fp.scrollToText("India");
		fp.selectionOfCounty.click();
		fp.nameField.sendKeys("Jenish Gandhi");
		driver.hideKeyboard();
		fp.GenderFemaleSelection.click();
		fp.SubmitBtn.click();
		
		EC_ShopingPage sp = new EC_ShopingPage(driver);
		sp.Product_Add_To_Cart.get(0).click();
		sp.Product_Add_To_Cart.get(0).click();
		sp.CartBtn.click();
		Thread.sleep(1000);
		

		EC_CheckOutPage Ck_out_page = new EC_CheckOutPage(driver);
		
		int count = Ck_out_page.Cart_Prd_Count.size();
		System.out.println(count);
		double totalprice = 0.0;

		for (int i = 0; i <= count; i++) {
			System.out.println(i);
			String text = Ck_out_page.Cart_Prd_Name.get(i).getText();
			System.out.println(text);
			String price = Ck_out_page.Cart_Prd_Prices.get(i)
					.getText();
			System.out.println(price);
			totalprice += getAmount(price);
		}

		System.out.println(totalprice);

		String totalAmt = Ck_out_page.TotalAmount.getText();
		double total_amt = getAmount(totalAmt);

		Assert.assertEquals(totalprice, total_amt, 0.01);

		// Mobile Gestures

		// Touch Tap action perfom
		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));

		TouchAction t = new TouchAction(driver);

		t.tap(tapOptions().withElement(element(checkbox))).perform();

		// Touch longpress action perform
		WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

		driver.findElement(By.id("android:id/button1")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

		// Change for Native App to Web application(Hybrid App)
		Thread.sleep(10000);
		Set<String> contexts = driver.getContextHandles();
		for (String ContextName : contexts) {
			System.out.println(ContextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		// Code for web selenium
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[1]/input"))
				.sendKeys("Selenium Appium");
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[1]/input")).sendKeys(Keys.ENTER);

		// Change for Web application(Hybrid App) to Native App

		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		
		base_Utilities.stopServer();

	}
	
	@AfterSuite
	public void closeEmulator() throws IOException{
        Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
	  }
	
}
