package Utilities;

import java.io.IOException;
import java.net.ServerSocket;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base_Utilities {

	public static AppiumDriverLocalService service;
	private AppiumDriver<AndroidElement> driver;

	
	public base_Utilities(AppiumDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public static AppiumDriverLocalService startServer() {

		service = AppiumDriverLocalService.buildDefaultService();
		service.start();

		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void stopServer() {
		service.stop();
	}
	
	

}
