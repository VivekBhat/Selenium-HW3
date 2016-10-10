package selenium.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebTest {
	private static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Exception {
		// driver = new HtmlUnitDriver();
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// driver.close();
		// driver.quit();
		
		//uncomment these if you want to close the browser after tests are done
	}


	@Test
	public void closedStudies() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");

		// http://geekswithblogs.net/Aligned/archive/2014/10/16/selenium-and-timing-issues.aspx
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
		List<WebElement> spans = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));
		assertNotNull(spans);
		// System.out.println(spans.get(2) + "," + spans.get(1) );
		assertEquals(5, spans.size());
	}

	// The participant count of "Frustration of Software Developers" is 55
	@Test
	public void countParticipants() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[2]/section/div[1]/div[8]/div[2]/p/span[1]")));
		List<WebElement> count = driver
				.findElements(By.xpath("/html/body/div[2]/section/div[1]/div[8]/div[2]/p/span[1]"));
		System.out.println(count.get(0).getText());
		assertNotNull(count);
		assertEquals("55", count.get(0).getText());	//checking if count is 55
	}

	// This code looks for the open status and then clicks on "Participate" button.
	@Test
	public void clickOnParticipate() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='OPEN']")));

		List<WebElement> links = driver.findElements(By.xpath("//button[@class='btn btn-info']"));

		System.out.println("outside loop");

		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i));
			links.get(i).click();
		}
		assertNotNull(links);

		assertEquals(7, links.size());

	}

	//this code checks if the "Software Changes Survey" has a Amazon reward image.
	@Test
	public void amazonRewardImageTest() throws Exception {
		driver.get("http://www.checkbox.io/studies.html");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[2]/section/div[1]/div[12]/div[1]/div[1]/p[1]")));

		List<WebElement> spans = driver.findElements(By.xpath("/html/body/div[2]/section/div[1]/div[12]/div[1]/div[1]/p[1]"));
		// *[@id="dynamicStudies"]/div[12]/div[1]/div[1]/p[1]
		//Since image tag is associated with the "Reward:" text, we can search for that"
		assertNotNull(spans);
		assertEquals("Reward:", spans.get(0).getText());
	}

}
