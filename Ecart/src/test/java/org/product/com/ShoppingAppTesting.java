package org.product.com;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdbc.com.JDBCConnection;
import org.jsondata.com.ConvertJson;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utils.com.BaseTest;



public class ShoppingAppTesting extends BaseTest {
	
	// 3286262ba4ef4573b56641b03f5af256 paswd of jenkins
	String productName="ADIDAS ORIGINAL";

	@Test(groups= {"functuional"},dataProvider="data2")
	public void ShoppingApplication(HashMap<String, String> input) throws  InterruptedException {

		lip.logInPage(input.get("email"),input.get("pass"));
		AddItemToCart aitc = new AddItemToCart(driver,productName);
		aitc.listOfProducts();
		//aitc.view();
		aitc.addToCart(productName);
		aitc.goToCart();
		Thread.sleep(150);
		//CheckoutPage cop=new CheckoutPage(driver);
		aitc.checkCartList(productName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(150);
		aitc.checkOutProduct();
	    SubmitOrder so=new SubmitOrder(driver);
	    so.countrySelection();
	    so.placedOrder();
	    String texte=so.submissionSuccess();
	    Assert.assertTrue(texte.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}

	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"\\reports"+testCaseName+".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir")+"\\reports"+testCaseName+".png";
	}

	//normal dataprovider type1  hardcoding values
	@DataProvider(name="data")
	public String[][] getData() {
		return new String[][] {{"chakri123@gmail.com","Chakri@123"}};
	}
	
	//dataprovider type2 json
	@DataProvider(name="data2")
	public Object[][] getData2() throws IOException {
		ConvertJson cj=new ConvertJson();
		List<HashMap<String, String>> data=cj.jsonData();
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	//dataprovider type 3 jdbc
//	@DataProvider(name="data3")
//	public Object[][] getData3() throws SQLException {
//		JDBCConnection jdbc=new JDBCConnection();
//		Object[] data=jdbc.jdbcConnection();
//		return new Object[][] {{data[0],data[1]}};
//	}

}





/*
@DataProvider
public  Object[][] getData() throws IOException {
	JsonData jd=new JsonData();
	List<HashMap<String, String>> rawData=jd.jsonData();
	Object[][] data=new Object[][]{{rawData.get(0)}};
	return data;
}

@DataProvider
public String[][] getData() {
	return new String[][] { { "chakri123@gmail.com", "Chakri@123", "ADIDAS ORIGINAL" },
			{ "chakri123@gmail.com", "Chakri@123", "ADIDAS ORIGINAL" } };
}

*/


























//HashMap<String, String> hm=new HashMap<String,String>();
//hm.put("email", "chakri123@gmail.com");
//hm.put("pswd", "Chakri@123");
//hm.put("productName", "ADIDAS ORIGINAL");
//
//HashMap<String, String> hm1=new HashMap<String,String>();
//hm1.put("email", "chakri123@gmail.com");
//hm1.put("pswd", "Chakri@123");
//hm1.put("productName", "ADIDAS ORIGINAL");
