package Practies.AppiumFramework;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.base_Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.Preference;
import pageObjects.Preference_dependencies;

public class ApiDemoAppTestCase extends base_class{
	Preference_dependencies pd;
	@Test
	public void APIDemoTC() throws IOException, InterruptedException {
		// appium code
		base_Utilities.startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("ApiDemoApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
		Thread.sleep(5000);
		HomePage HP = new HomePage(driver);
		HP.Preference.click();
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();

		Preference pref = new Preference(driver);
		pref.Preference_dependencies.click();
		


		//driver.findElement(By.xpath("//android.widget.TextView[@text='WiFi']")).click();
		//driver.findElement(By.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
		//driver.findElement(By.id("android:id/edit")).sendKeys("Xyz");
		//driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click(); 	
		

//		pd.WiFi.click();
//		pd.WiFi_settings.click();
//		pd.edit.sendKeys("xyz");
//		pd.ok.click();

		
		
	}
	
	@Test(dataProvider = "input", dataProviderClass = testData.class, dependsOnMethods= {"APIDemoTC"})
	public void Preference_dependencies(String input) throws InterruptedException {
		pd = new Preference_dependencies(driver);
		Thread.sleep(1000);
		pd.WiFi.click();
				
		pd.WiFi_settings.click();
		pd.edit.clear();;
		pd.edit.sendKeys(input);
		pd.ok.click();
		pd.WiFi.click();


	}
	
	
	/*
	 * @AfterClass public void stopServer(String input) {
	 * base_Utilities.stopServer();
	 * 
	 * 
	 * }
	 */
	
	
}
