package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EC_FormPage {
	private AppiumDriver<AndroidElement> driver;

	public EC_FormPage(AppiumDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	public WebElement nameField;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	public WebElement GenderMaleSelection;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	public WebElement GenderFemaleSelection;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Afghanistan']")
	public WebElement openCountryList;
	
	/*
	 * @AndroidFindBy(uiAutomator =
	 * "new UiScrollable(new UiSelector()).scrollIntoView(text(\\\"India\\\"));")
	 * public WebElement ScrollIntoView;
	 */

	@AndroidFindBy(xpath = "//*[@text='India']")
	public WebElement selectionOfCounty;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement SubmitBtn;
	
	// Scroll To Element using Text for Android
	public void scrollToText(String containedText) {
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
						+ containedText + "\").instance(0))"));
	}

}
