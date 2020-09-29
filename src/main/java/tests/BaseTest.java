package tests;

import org.testng.annotations.AfterClass;
import browser.Browser;
import logger.Log;

public class BaseTest {

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    Log.logInfo("Test finished");
    Browser.getInstance().closeBrowser();
  }

}
