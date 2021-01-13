package Practies.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class base_class {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	
	
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(10000);
	}
	public static AndroidDriver<AndroidElement> Capabilities(String AppName) throws IOException, InterruptedException {
		// Install App into emulator
		// Load Goblal.properties file
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Practies\\AppiumFramework\\global.properties");
		Properties pro = new Properties();
		pro.load(file);

		// Search Application in directory folder
		File appDir = new File("src");
		File appFile = new File(appDir, (String) pro.get(AppName));

		// Android Device connection base capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("fullReset", false);
		cap.setCapability("noReset", true);

		//String Device_name = (String) pro.get("deviceName");
		String Device_name = System.getProperty("deviceName");
	
		if(Device_name.contains("Emulator")) {
			cap.setCapability("avd",Device_name);
			Thread.sleep(10000);
			
		}
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Device_name);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		// cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
		cap.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
	
		// Connect Appium using URL
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		return driver;
	}
	
	
	public static void getScreenshots(String testName) throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\screenshot\\"+testName+".png"));
	}
	
	
}
