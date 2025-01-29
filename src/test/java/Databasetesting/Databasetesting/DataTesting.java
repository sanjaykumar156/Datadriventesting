package Databasetesting.Databasetesting;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DataTesting extends Databaseutilities {
	static WebDriver driver;
	public static void main(String[] args) {
		try {
			properties.load(file);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(properties.getProperty("url"));
			String expectedusername=properties.getProperty("username");
			String exceptedpassword= properties.getProperty("password");
			String actualusername=Databaseutilities.getdatafromdb("SELECT username FROM data WHERE id = 1");
			String actualpassword=Databaseutilities.getdatafromdb("SELECT password FROM data WHERE id = 1");
			Assert.assertEquals(expectedusername, actualusername);
			Assert.assertEquals(exceptedpassword, actualpassword);
			WebElement username = driver.findElement(By.id("txtusername"));
			WebElement password= driver.findElement(By.id("txtpassword"));
			username.clear();
			username.sendKeys(actualusername);
			password.clear();
			password.sendKeys(actualpassword);
			driver.findElement(By.id("btnlogin")).click();
			String text = driver.findElement(By.xpath("//h3[normalize-space()='Home']")).getText();
			if(text.equals("Home")) {
				System.out.println("successfully extract data from database login is succefully");
			}else {
				System.out.println("failed to extract data from database");
			}	
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (driver != null) {
	            driver.quit();
	        }
		}
	}
}
