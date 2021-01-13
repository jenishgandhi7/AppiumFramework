package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EC_CheckOutPage {
	private AppiumDriver<AndroidElement> driver;

	public EC_CheckOutPage(AppiumDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/rvCartProductList")
	public List<WebElement> Cart_Prd_Count;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	public List<WebElement> Cart_Prd_Name;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	public List<WebElement> Cart_Prd_Prices;


	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement TotalAmount;

	

}
