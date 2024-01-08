package testranner;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import loginpage.Luma;
import utilities.assertion;

public class Ranner extends assertion{

	@BeforeMethod
	public void close() {
		
		browser();
	}
	
	@Test
	public void order_jacket() throws InterruptedException {
		String Ui_username= userlogin();
		String error="user is not valid : "+Ui_username;
		Assert.assertEquals(Ui_username, "Welcome, anuj desai!",error);
		System.out.println("Ui_username : "+Ui_username);
		Luma.order_jacket();
		user_sing_up();
		driver.close();
	}
	
	
	@Test()
	public void place_order_summary() throws InterruptedException {
		String Ui_username= userlogin();
		Thread.sleep(3000l);
		Assert.assertEquals(Ui_username, "Welcome, anuj desai!","user name is miss mach.");
		System.out.println("Ui_username : "+Ui_username);
		Thread.sleep(3000l);
		Luma.Place_order();
		user_sing_up();
	}
	
	@Test()
	public void delete_order_from_Cart() throws InterruptedException {
		String Ui_username= userlogin();
		Thread.sleep(3000l);
		Assert.assertEquals(Ui_username, "Welcome, anuj desai!","user name is miss mach.");
		System.out.println("Ui_username : "+Ui_username);
		Thread.sleep(3000l);
		
	}

	
	@AfterMethod()
	public void browger() {
		driver.quit();
	}
	
	
	


	

}
