package saucedemo.org;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login extends BaseClass{
	 SoftAssert s = new SoftAssert();
@BeforeClass
@Parameters("browsername")
private void launch(String browsername) {
	switch (browsername.toLowerCase()) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		break;
	case "edge":
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		break;
	default:
		System.out.println("invalid browser! Launching chrome as default");
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
	}
	waits();
	maxwin();
	geturl("https://www.saucedemo.com/");
			
}
@BeforeMethod ()
private void timeanddate() {
	dateandtime();
}

@Test 
private void username() {
	waits();
			WebElement usrnm = driver.findElement(By.xpath("//input[@name='user-name']"));
			filltxtbox(usrnm, "standard_user");
			s.assertEquals("standard_user", "standard_user");
			
			WebElement pass = driver.findElement(By.xpath("//input[@placeholder='Password']"));
			filltxtbox(pass, "secret_sauce");
			s.assertEquals("secret_sauce", "secret_sauce");
			
			WebElement button = driver.findElement(By.xpath("//input[@type='submit']"));
			btn(button);
			
			waits();
			WebElement title = driver.findElement(By.className("title"));
			String text = title.getText();
			s.assertEquals(text,"Products", "Login failed: Title mismatch");
			
			s.assertAll();
}

@AfterMethod
private void timeandd() {
	dateandtime();
}
@AfterClass
private void teardown() {
	//driver.quit();
}


}
