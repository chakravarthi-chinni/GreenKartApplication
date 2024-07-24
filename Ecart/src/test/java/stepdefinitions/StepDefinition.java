package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.product.com.AddItemToCart;
import org.product.com.LogInPage;
import org.product.com.SubmitOrder;
import org.testng.Assert;
import org.utils.com.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest{
	
	 public LogInPage lip;
	 public AddItemToCart aitc;
	 public SubmitOrder so;
	
	
	@Given("I landed in the ecommerse page")
	public void i_landed_in_the_ecommerse_page() throws IOException {
		lip=launchApplication();
	}
	
	@Given("^login with username (.+) and password (.+)$")
	public void login_with_username_and_password(String userName,String password) {
		lip.logInPage(userName,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		aitc = new AddItemToCart(driver,productName);
		aitc.listOfProducts();
		aitc.addToCart(productName);
	}
	
	@And("^check the product (.+) in the list and place the order$")
	public void check_the_product_in_the_list_and_place_the_order(String productName) throws InterruptedException {
		aitc.goToCart();
		Thread.sleep(150);
		aitc.checkCartList(productName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(150);
		aitc.checkOutProduct();
	    so=new SubmitOrder(driver);
	    so.countrySelection();
	    so.placedOrder();
	}
	@Then("finally get the confirmation message {string}")
	public void finally_get_the_confirmation_message(String string) {
		String texte=so.submissionSuccess();
	    Assert.assertTrue(texte.equalsIgnoreCase(string));
	    driver.close();
		
	}
	
	@Then("getting error message {string}")
	public void getting_error_message (String string) {
		Assert.assertEquals(string, lip.getErrorMessage());
		driver.close();
	}
	
	
}
