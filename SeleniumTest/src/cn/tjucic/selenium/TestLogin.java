package cn.tjucic.selenium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestLogin {
	
	private WebDriver driver;
	 private String baseUrl;
	  

	  @Before
	  public void setUp() throws Exception {
		  String driverPath = System.getProperty("user.dir") + "/firefox/geckodriver.exe";
		  System.setProperty("webdriver.gecko.driver", driverPath);
		  driver = new FirefoxDriver();
		  baseUrl = "http://121.193.130.195:8800";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testBaidu() throws Exception {
	    driver.get(baseUrl + "/");
	    ExcelReader reader = new ExcelReader();
	    String excelUrl = System.getProperty("user.dir") + "/firefox/nameList.xlsx";
	    ArrayList<ArrayList<String>> list = reader.xlsx_reader(excelUrl, 1,2,3);
	    for(int i = 0; i < list.size(); i++) {
	    	WebElement id = driver.findElement(By.name("id"));
		    WebElement password = driver.findElement(By.name("password"));
		    WebElement btnLogin = driver.findElement(By.id("btn_login"));
		    id.clear();
		    password.clear();
		    id.sendKeys(list.get(i).get(0));
		    password.sendKeys(list.get(i).get(0).substring(4, 10));
//		    btnLogin.click();
		    btnLogin.sendKeys(Keys.ENTER);
		    assertEquals(list.get(i).get(0), driver.findElement(By.id("student-id")).getText());
		    assertEquals(list.get(i).get(1), driver.findElement(By.id("student-name")).getText());
		    assertEquals(list.get(i).get(2), driver.findElement(By.id("student-git")).getText());
	    }
	    
	  }

	  @After
	  public void tearDown() throws Exception {
//	    driver.quit();
//	    String verificationErrorString = verificationErrors.toString();
//	    if (!"".equals(verificationErrorString)) {
//	      fail(verificationErrorString);
//	    }
	  }

}
