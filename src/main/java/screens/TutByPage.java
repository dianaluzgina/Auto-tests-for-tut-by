package screens;

import java.util.ArrayList;
import org.openqa.selenium.By;

public class TutByPage extends BasePage{
  private static final String URL = "https://www.tut.by/";

  private static final String SECTIONS_XPATH = "//a[@href='https://www.tut.by/resource/'][@class='topbar-burger']";
  private static final String ALL_SECTIONS_XPATH = "//a[@href='https://www.tut.by/resource/'][@class='topbar__link']";
  private static final String ONLINE_CINEMA_XPATH = "//a[@href='https://afisha.tut.by/online-cinema/']";

  public TutByPage load() {
    browser.getUrl(URL);
    return this;
  }

  public TutByPage clickSectionsButton() {
    browser.waitForVisibility(By.xpath(SECTIONS_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(SECTIONS_XPATH));
    return this;
  }

  public TutByPage clickAllSectionsButton() {
    browser.waitForVisibility(By.xpath(ALL_SECTIONS_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(ALL_SECTIONS_XPATH));
    return this;
  }

  public TutByPage clickOnlineCinemaLink() {
    browser.waitForVisibility(By.xpath(ONLINE_CINEMA_XPATH), browser.LONG_TIMEOUT);
    browser.clickElement(By.xpath(ONLINE_CINEMA_XPATH));
    return this;
  }

  public TutByPage switchToTheTabByIndex(int index) {
    ArrayList<String> tabs = new ArrayList<>(browser.getWindowHandles());
    browser.switchToTab(tabs.get(index - 1));
    return this;
  }
}
