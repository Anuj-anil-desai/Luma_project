package loginpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.assertion;

public class Luma extends assertion {


	static By qtyname=By.xpath("//span[@class='cart-price']");
	
	static	By email=By.xpath("(//input[@class='input-text'])[3]");
	static	By name=By.xpath("//input[@name='firstname']");
	static	By lastname=By.xpath("//input[@name='lastname']");
	static	By company=By.xpath("//input[@name='company']");
	static	By add=By.xpath("//input[@name='street[0]']");
	static	By add1=By.xpath("//input[@name='street[1]']");
	static	By add2=By.xpath("//input[@name='street[2]']");
	static	By city=By.xpath("//input[@name='city']");
	static	By state=By.name("region_id");
	static By zip=By.xpath("//input[@name='postcode']");
	static By country=By.name("country_id");
	static	By phone=By.xpath("//input[@name='telephone']");
	static	By radio=By.xpath("//input[@type='radio']");
	static By button=By.xpath("//button/span[contains(text(),'Next')]");
	static By text=By.xpath("//div[contains(text(),'Shipping Address')]");
	static By placeorder=By.xpath("//button/span[contains(text(),'Place Order')]");
	static By pymentext=By.xpath("//div[contains(text(),'Payment Method')]");

	static By empty_cart_text=By.xpath("//strong[@class='subtitle empty']");
	static By delete_icon=By.xpath("//div[@class='secondary']/a[@href='#']");
	static By pop_up_Button=By.xpath("//button/span[contains(text(),'OK')]");
	
	public static void order_jacket() throws InterruptedException {
		
		try {
			driver.findElement(By.id("search")).sendKeys("jacket",Keys.ENTER);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,500);");
			driver.findElement(By.xpath("(//strong[@class='product name product-item-name'])[12]")).click();
			
			Thread.sleep(3000l);
			
			String product_name=driver.findElement(By.xpath("//span[@data-ui-id=\"page-title-wrapper\"]")).getText();
			String price=driver.findElement(By.xpath("//span[@data-price-type='finalPrice']/span[contains(text(),'$84.00')]")).getText();
			driver.findElement(By.id("option-label-size-143-item-168")).click();
			
			Thread.sleep(2000l);
			
			driver.findElement(By.id("option-label-color-93-item-58")).click();
			js.executeScript("window.scrollBy(0,500);");
			driver.findElement(By.id("product-addtocart-button")).click();
			Thread.sleep(3000l);
			js.executeScript("window.scrollBy(0,-500);");
			
			Thread.sleep(3000l);
			driver.findElement(By.xpath("(//span[@class=\"counter qty\"])[1]")).click();

			//validation text message form UI
			String add_to_kart_productname=driver.findElement(By.xpath("(//div[@class='product-item-details']//a)[1]")).getText();
			Thread.sleep(5000l);
			String add_to_kart_productprice=driver.findElement(By.xpath("//span[@class='minicart-price']/span")).getText();
		
			
			
			//assert to validate UI message and expected message 
			
			Assert.assertEquals(product_name, add_to_kart_productname,"product name miss mach");
			Assert.assertEquals(price, add_to_kart_productprice,"product price is Miss mach");
			
			System.out.println("product name : "+product_name+" : add_to_kart_productname : "+add_to_kart_productname);
			System.out.println("product price : "+price+" : add_to_kart_productprice : "+add_to_kart_productprice);
			
		}
		
		finally {
			System.out.println("order_jacket method complet  ");
		}
		
	}
	
	
	public static void Place_order() throws InterruptedException {

		try {
			
			String [] data=data();
			Thread.sleep(3000l);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.findElement(By.xpath("(//span[@class='counter qty'])[1]")).click();
			driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.presenceOfElementLocated(text));
			Thread.sleep(5000l);
			sendkey(name,data[0]);
			sendkey(lastname, data[1]);
			sendkey(company,data[2]);
			sendkey(add,data[3]);
			sendkey(add1,data[4]);
			sendkey(add2,data[5]);
			sendkey(city, data[6]);
		
			driver.findElement(By.xpath("(//select[@class='select'])[2]")).click();
			WebElement country=driver.findElement(By.xpath("(//select[@class='select'])[2]/option[contains(text(),'India')][2]"));
			country.click();
			Thread.sleep(2000l);
			
			driver.findElement(By.xpath("(//select[@class='select'])[1]")).click();
			WebElement state=driver.findElement(By.xpath("(//select[@class='select'])[1]/option[contains(text(),'Maharashtra')]"));
			state.click();
			Thread.sleep(2000l);
			sendkey(phone,data[8]);
			sendkey(zip,data[7]);
			
			driver.findElement(button).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(placeorder));
			String placebutton=driver.findElement(placeorder).getText();
			String pymenttext=driver.findElement(pymentext).getText();
			driver.navigate().back();
			System.out.println("order place button :"+placebutton+" : pyment method Text : "+pymenttext);
			Assert.assertEquals(placebutton, "Place Order","Button text not found : ");
			Assert.assertEquals(pymenttext, "Payment Method","Text missmach : ");
			System.out.println("Order place test case complete");
			driver.navigate().back();

		}
		finally{

			System.out.println("place_order function complete : ");
		}
	}
	static void sendkey(By ele,String str) {
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(str,Keys.TAB);
	}
	
	static String[] data() {
		
		String[] data= {
				"google",
				"testdeta",
				"pharmarack",
				"Wadmukwadi",
				"Dighi-Alandi road",
				"laxminarayan nagar",
				"Pune",
				"411510",
				"7769925160",
				"googleself@gmail.com"};

		return data;
		
	}
	
	public void Delete_order_form_AddToCart() throws InterruptedException {
		
		Thread.sleep(3000l);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.findElement(By.xpath("(//span[@class='counter qty'])[1]")).click();
		driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']")).click();
		String text=driver.findElement(empty_cart_text).getText();
		
		
		Assert.assertEquals(text, "You have no items in your shopping cart.","Text message missmach");
	}

}
