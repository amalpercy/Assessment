package package14;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

@Listeners(package14.Listeners1.class)
public class Assessment1 {
	public static WebDriver driver = null;
	public static String expectedUrl = "https://sakshingp.github.io/assignment/home.html";
	public boolean login = false;
	
	@BeforeMethod
	public void launchBrowser(){
		
		System.setProperty("webdriver.edge.driver", "C:\\bd\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	@AfterMethod
	public void closingBrowser(){
			driver.close();
		}
		
	@Test(priority = 1, dataProvider = "database")
	public void login_test(String userName, String passWord)
	{
	        driver.get("https://sakshingp.github.io/assignment/login.html");
		// provide User name
	        driver.findElement(By.id("username")).sendKeys(userName);
		// provide Password
	        driver.findElement(By.id("password")).sendKeys(passWord);
		// clicking on login button
	        driver.findElement(By.id("log-in")).click();
				      String actualUrl = driver.getCurrentUrl();
						if(!userName.isEmpty() && !passWord.isEmpty()) {
							AssertJUnit.assertTrue(actualUrl.equals(expectedUrl));
							Reporter.log("Login Successful");
							
							login = true;
							if(login==true) {
								page2();
							}
						}
						else {
							AssertJUnit.assertFalse(actualUrl.equals(expectedUrl));
							Reporter.log("Login Failed");
						}
	}
		 

	  @Test(priority = 2)
			public void page2() {
							
			driver.get(expectedUrl);
			driver.findElement(By.id("amount")).click();

			//Checking if the element under amount column are sorted or not 
			ArrayList<Float> actualList = new ArrayList<>();
			List<WebElement> webElementList = driver.findElements(By.className("text-danger"));
			webElementList.addAll(driver.findElements(By.className("text-success")));
			for(WebElement we : webElementList) {
					String element = we.getText();
					element = element.replaceAll(" ","");
					element = element.replaceAll("[^\\d.-]","");
					actualList.add(Float.parseFloat(element));
			}
	
			 ArrayList<Float> expectedList = new ArrayList<>();
				for(Float s : actualList) {
				expectedList.add(s);
		}
				Collections.sort(expectedList);
				try {
					AssertJUnit.assertEquals(actualList, expectedList);
					Reporter.log("List is Sorted");
			}
					catch(AssertionError e){
					Assert.assertNotEquals(actualList, expectedList);
				    Reporter.log("List is Unsorted");
				}
		}
						
	@DataProvider(name="database")
	 public Object[][] dataSet(){
	   return new Object[][] {
								{"", ""},
								{"percy", ""},
								{"", "xyz"},
								{"percy", "xyz"}
		};
	}
}
