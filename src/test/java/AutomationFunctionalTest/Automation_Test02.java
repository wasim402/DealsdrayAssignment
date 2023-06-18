package AutomationFunctionalTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class Automation_Test02 {
	
	@Test
	public static void Order() throws IOException, InterruptedException {
		
		EdgeOptions opt = new EdgeOptions();
		opt.addArguments("--remote-allow-origins=*");
		WebDriver driver = new EdgeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.dealsdray.com/");
		driver.findElement(By.cssSelector("[name='username']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.cssSelector("[name='password']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.xpath("//span[text()='Order']")).click();
		driver.findElement(By.xpath("//div[@class='expansion-panel submenu']//span[text()='Orders']")).click();
		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();
		driver.findElement(By.className("MuiOutlinedInput-input")).sendKeys("C:\\Users\\akhta\\Desktop\\selenium69\\com.DealsDry\\src\\test\\resources\\NewTestData\\demo-data.xlsx");
		driver.findElement(By.xpath("//button[text()='Import']")).click();
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("./Screenshot/data.png");
		FileUtils.copyFile(src, trg);
		driver.quit();
	
	}

}