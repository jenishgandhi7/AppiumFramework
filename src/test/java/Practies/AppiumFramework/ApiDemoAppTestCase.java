package Practies.AppiumFramework;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import Utilities.base_Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.Preference;
import pageObjects.Preference_dependencies;

public class ApiDemoAppTestCase extends base_class{
	
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
		
		Preference_dependencies pd = new Preference_dependencies(driver);
		pd.WiFi.click();
		pd.WiFi_settings.click();
		pd.edit.sendKeys("xyz");
		pd.ok.click();

		base_Utilities.stopServer();
		
		
	}
	
	
}
