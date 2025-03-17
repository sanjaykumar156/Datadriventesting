package Databasetesting.Databasetesting;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Responsivenessheck {
	static WebDriver driver;
	static String devicename;
	public static void main(String[] args) throws InterruptedException {
		try {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://testautomationpractice.blogspot.com/");
			HashMap<String,Dimension> hashmap=new HashMap<String,Dimension>();
			hashmap.put("pixel17",new Dimension(412,915));
			hashmap.put("iphone12", new Dimension(390,844));
			hashmap.put("ipad air", new Dimension(820,1180));
			try {
				for(Map.Entry<String,Dimension> dimension:hashmap.entrySet()) {
					devicename=dimension.getKey().toLowerCase();
					driver.manage().window().setSize(dimension.getValue());
					switch(devicename) {
					case "pixel17":
						boolean checkbox=driver.findElement(By.xpath("//a[normalize-space()='GUI Elements']")).isDisplayed();
						Assert.assertTrue(checkbox);
						break;
					case "iphone12":
						WebElement textbox=driver.findElement(By.id("name"));
						textbox.sendKeys("sanjay");
						break;
					case "ipad air":
						 boolean textCheck = driver.findElement(By.xpath("//h2[normalize-space()='Mouse Hover']")).isDisplayed();
						 Assert.assertTrue(textCheck);
                         break;
                        default:
                        	throw new IllegalArgumentException("no matching device found"+devicename);
					}
					 System.out.println("Test passed for: " +devicename);
			}	
		}catch(Exception e){
			 System.out.println("Test failed for " +devicename + ": " +e.getMessage());
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
				driver.quit();
			}
		}	
}
}