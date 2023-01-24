package com.qualitystream.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest 
{
	private WebDriver driver;
	By videoLinkLocator = By.cssSelector("a[href='https://www.ibm.com/es-es/topics/software-testing']");
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chorme.driver", ".//srv/test//resources//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
	}
	
	@Test
	public void testGooglePage()
	{
		WebElement searchbox = driver.findElement(By.name("q"));
		searchbox.clear();
		searchbox.sendKeys("pruebas de software");
		searchbox.submit();
		
		//Implicit wait
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		//Explicit wait
		//WebDriverWait ewait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//ewait.until(ExpectedConditions.titleContains("pruebas de software"));
		
		//assertEquals("pruebas de software - Buscar con Google", driver.getTitle());
		
		//FluentWait
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
//		WebElement video = fwait.until(new Function<WebDriver,WebElement>(){
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(videoLinkLocator);
//			}
//		})
		   WebElement video = fwait.until(new Function<WebDriver,WebElement>()
				   {
			   public WebElement apply(WebDriver driver) {
				   return driver.findElement(videoLinkLocator);
			   }
				   }
				   );
		   assertTrue(driver.findElement(videoLinkLocator).isDisplayed());
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
		
	}
	
	
	
}
