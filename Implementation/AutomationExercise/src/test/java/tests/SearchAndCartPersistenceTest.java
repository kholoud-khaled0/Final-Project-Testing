package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.AddToCartPage;
import pages.LoginPage;
import java.util.List;
import org.openqa.selenium.WebElement;

public class SearchAndCartPersistenceTest extends TestBase {

    HomePage homeObject;
    ProductsPage productsObject;
    AddToCartPage cartObject;
    LoginPage loginObject;


	@DataProvider(name = "loginData")
	public Object [][] testData(){
		
		Object[][] data = new Object[][] {
			{"kholoud111@gmail.com","kholoud155312"}
			
		};
		 
		return data;
	}
	

    @Test(priority = 1,dataProvider = "loginData")
    public void searchAndVerifyCartAfterLogin(String email , String password) throws InterruptedException {
        homeObject = new HomePage(driver);
        productsObject = new ProductsPage(driver);
        cartObject = new  AddToCartPage(driver);
        loginObject = new LoginPage(driver);

       
        homeObject.openProductsPage();

        Assert.assertTrue(productsObject.allProductsHeader.isDisplayed());

        productsObject.searchForProduct("top");
        Assert.assertTrue(productsObject.searchedProductsHeader.isDisplayed());

        List<WebElement> searchResults = productsObject.getSearchResultProducts();
        Assert.assertFalse(searchResults.isEmpty());

        productsObject.addSearchResultsToCart();

        productsObject.openCartPage();
        Assert.assertFalse(cartObject.getAllCartRows().isEmpty());

        homeObject.openLoginPage();
        loginObject.userCanLogin(email , password); 
//
//        homeObject.openCartPage();
//        Assert.assertFalse(cartObject.getAllCartRows().isEmpty());
    }
}
