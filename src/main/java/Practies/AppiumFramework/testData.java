package Practies.AppiumFramework;

import org.testng.annotations.DataProvider;

public class testData {

	@DataProvider(name="input")
	public Object[][] getDataEntry() {
		Object[][] obj = new Object[][] {
			
			{	"hello"},{"XYZ" }
			
		};
		
		return obj;
	}
}
