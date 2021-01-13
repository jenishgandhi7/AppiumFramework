package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Preference_dependencies {
	public Preference_dependencies(AppiumDriver<AndroidElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='WiFi']")
	public WebElement WiFi;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='WiFi settings']")
	public WebElement WiFi_settings;

	@AndroidFindBy(id="android:id/edit")
	public WebElement edit;
	

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public WebElement ok;
}
