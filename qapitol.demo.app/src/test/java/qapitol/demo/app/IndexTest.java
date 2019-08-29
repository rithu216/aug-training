package qapitol.demo.app;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import qapitol.demo.app.controllers.DemoController;

public class IndexTest {
	private WebDriver driver;
	private DemoController demo;

	@BeforeClass
	public void first() {
		System.setProperty("webdriver.chrome.driver", "/home/sourabh/Documents/chromedriver");
		ChromeOptions o = new ChromeOptions();
		driver = new ChromeDriver(o);
		driver.get("http://localhost:8080/index.html");

	}

	@Test(description = "testing the title and url", priority = 1)
	public void testTitle() {
		assertEquals(driver.getTitle(), "Students");
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/index.html");

	}

//positive test case for creating
	@Test(description = "creating a new student", priority = 2)
	public void testNewStudent() throws InterruptedException {

		Thread.sleep(1000);
		driver.findElement(By.xpath("//b[contains(text(),'NEW STUDENT')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/form.html");
		Thread.sleep(1000);
		driver.findElement(By.id("std-name")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("std-name")).sendKeys("demo");
		Thread.sleep(1000);
		driver.findElement(By.id("std-age")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("std-age")).sendKeys("21");
		Thread.sleep(1000);
		driver.findElement(By.id("std-email")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("std-email")).sendKeys("demo@ksd.com");
		Thread.sleep(1000);
		driver.findElement(By.id("save")).click();
		Thread.sleep(1000);
		assertEquals(driver.switchTo().alert().getText(), "student Updated Succesfully");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();

	}

//negative test case for creating 
	@Test(description = "submitting empty form", priority = 3)
	public void testEmpty() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//b[contains(text(),'NEW STUDENT')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/form.html");
		Thread.sleep(1000);
		assertEquals(driver.findElement(By.id("std-name")).getText(), "");
		Thread.sleep(1000);
		assertEquals(driver.findElement(By.id("std-age")).getText(), "");
		Thread.sleep(1000);
		assertEquals(driver.findElement(By.id("std-email")).getText(), "");
		Thread.sleep(1000);
		driver.findElement(By.id("save")).click();
		Thread.sleep(1000);
		assertEquals(driver.switchTo().alert().getText(), "All fields are mandatory");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		driver.navigate().back();
	}

//positive test case for deleting 
	@Test(description = "deleting a record", priority = 4)
	public void delete() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tr[1]//td[5]//a[2]")).click();
		Thread.sleep(1000);
		assertEquals(driver.switchTo().alert().getText(), "record has been deleted");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		demo = new DemoController();
//		assertNull(demo.getById(6).getStatusCodeValue());

	}

//	positive test case for editing 
	@Test(description = "editing a record", priority = 5)
	public void edit() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tr[1]//td[5]//a[1]")).click();
		assertTrue(driver.getCurrentUrl().contains("form.html"));
		Thread.sleep(1000);
		driver.findElement(By.id("std-name")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("std-name")).sendKeys("new name");
		Thread.sleep(1000);
		driver.findElement(By.id("save")).click();
		Thread.sleep(1000);
		assertEquals(driver.switchTo().alert().getText(), "student Updated Succesfully");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//b[contains(text(),'ALL STUDENT')]")).click();

	}

	// negative test case for edit
	@Test(description = "editing a record passing empty values", priority = 6)
	public void editByEmptyValues() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tr[1]//td[5]//a[1]")).click();
		assertTrue(driver.getCurrentUrl().contains("form.html"));
		Thread.sleep(1000);
		driver.findElement(By.id("std-name")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("save")).click();
		Thread.sleep(1000);
		assertEquals(driver.switchTo().alert().getText(), "All fields are mandatory");
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//b[contains(text(),'ALL STUDENT')]")).click();
	}

	@AfterClass
	public void tearDown() {
		driver.close();

	}

}
