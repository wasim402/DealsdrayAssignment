package AutomationUITest;

import org.testng.annotations.Test;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class AutomationUI2 {
	@Test
	public void test() throws Exception {
		List<Dimension> resolutions = new ArrayList<Dimension>();
		resolutions.add(new Dimension(1920, 1080));
		resolutions.add(new Dimension(1366, 768));
		resolutions.add(new Dimension(1536, 864));
		resolutions.add(new Dimension(360, 640));
		resolutions.add(new Dimension(414, 896));
		resolutions.add(new Dimension(375, 667));

		List<String> browsers = new ArrayList<String>();
		browsers.add("chrome");
		browsers.add("firefox");
		browsers.add("edge");

		WebDriver driver = null;
		for (String browser : browsers) {
			if (browser.equals("chrome")) {
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(opt);
			} else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equals("edge")) {
				EdgeOptions opt = new EdgeOptions();
				opt.addArguments("--remote-allow-origins=*");
				driver = new EdgeDriver(opt);
			} else {
				throw new Exception("invalid browser");
			}

			for (Dimension resolution : resolutions) {
				driver.manage().window().setSize(resolution);

				String folderName = browser + "_" + resolution.getWidth() + "X" + resolution.getHeight();
				File folder = new File(folderName);
				if (!folder.exists()) {
					folder.mkdir();
				}
				driver.get("https://www.getcalley.com/");

				SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
				String date = sdf.format(new Date());
				TakesScreenshot ts = (TakesScreenshot) driver;
				File src = ts.getScreenshotAs(OutputType.FILE);
				File trg = new File("./" + folderName + "/name_" + date + ".png");
				FileUtils.copyFile(src, trg);

			}
			driver.quit();
		}

	}

}