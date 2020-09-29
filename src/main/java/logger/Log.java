package logger;

import static java.lang.String.format;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Log {

  private static Logger logger = Logger.getLogger("MyLogger");

  public static void logInfo(String message) {
    logger.info(message);
  }

  public static void logClick(By by) {
    logger.debug(format("Click element located: [%s]", by));
  }

  public static void logGetUrl(String url) {
    logger.debug(format("Open page by URL: [%s], maximize page", url));
  }

  public static void logCloseBrowser() {
    logger.debug("Close browser");
  }

  public static void logGetWindowHandles() {
    logger.debug("Find all open tabs in browser");
  }

  public static void logSwitchToTab(String nameOfTab) {
    logger.debug(format("Switch to tab with name [%s]", nameOfTab));
  }

  public static void logWaitForVisibility(By by, int seconds) {
    logger.debug(format("Wait for visibility of element located [%s] for [%s] seconds",
        by, seconds));
  }

  public static void logTakeScreenshotOnThePage(String filePath) {
    logger.debug(format("Take screenshot on the page to the file [%s]", filePath));
  }

  public static void logTestFail(Throwable throwable) {
    logger.error(throwable.getMessage());
  }
}