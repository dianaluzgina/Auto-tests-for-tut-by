package browser;


import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import logger.Log;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {

  private static final String PATTERN_PATH_TO_SCREENSHOT_FILE = "test-output/screenshots/%s.png";
  public static final int LONG_TIMEOUT = 20;
  public static final int DEFAULT_TIMEOUT = 10;

  private WebDriver driver;
  private static Browser browser;

  private Browser() {
    System.setProperty("webdriver.chrome.driver",
        "./src/main/resources/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    driver.manage().timeouts().pageLoadTimeout(20000, TimeUnit.MILLISECONDS);
  }

  public static Browser getInstance() {
    if (browser == null) {
      return browser = new Browser();
    } else {
      return browser;
    }
  }

  public void closeBrowser() {
    driver.quit();
    browser = null;
    Log.logCloseBrowser();
  }

  public Set<String> getWindowHandles() {
    Log.logGetWindowHandles();
    return driver.getWindowHandles();
  }

  public void switchToTab(String nameOfTab) {
    Log.logSwitchToTab(nameOfTab);
    driver.switchTo().window(nameOfTab);
  }


  public void getUrl(String url) {
    Log.logGetUrl(url);
    driver.get(url);
    driver.manage().window().maximize();
  }

  public void clickElement(By by) {
    Log.logClick(by);
    driver.findElement(by).click();
  }

  public void waitForVisibility(By by, int seconds) {
    Log.logWaitForVisibility(by, seconds);
    new WebDriverWait(driver, seconds)
        .until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  public void takeScreenshotOnThePage(String fileName) {
    File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File destinationFile = new File(
        (format(PATTERN_PATH_TO_SCREENSHOT_FILE, fileName)));
    try {
      FileUtils.copyFile(screenFile, destinationFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Log.logTakeScreenshotOnThePage(destinationFile.getAbsolutePath());
  }
}
