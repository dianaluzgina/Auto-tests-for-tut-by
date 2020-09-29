package screens;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OnlineCinemaPage extends BasePage {

  private static final String GENRE_BUTTON_FILMS_XPATH = "//div[@id='tab-films']//button[@title='Жанры']";
  private static final String GENRE_BUTTON_SERIES_XPATH = "//div[@id='tab-tv-series']//button[@title='Жанры']";
  private static final String GENRE_BUTTON_CARTOONS_XPATH = "//div[@id='tab-cartoons']//button[@title='Жанры']";
  private static final String PATTERN_FOR_GENRE_IN_FILMS_XPATH = "//div[@id='tab-films']//span[contains(text(),'%s')]";
  private static final String PATTERN_FOR_GENRE_IN_SERIES_XPATH = "//div[@id='tab-tv-series']//span[contains(text(),'%s')]";
  private static final String PATTERN_FOR_GENRE_IN_CARTOONS_XPATH = "//div[@id='tab-cartoons']//span[contains(text(),'%s')]";
  private static final String ELEMENTS_DESCRIPTION_XPATH = "//li[@class='lists__li ']//p";
  private static final String SERIES_XPATH = "//a[@href='https://afisha.tut.by/online-cinema/serial/']";
  private static final String CARTOONS_XPATH = "//a[@href='https://afisha.tut.by/online-cinema/animation/']";
  private static final String NEXT_PAGE_BUTTON_XPATH = "//a[@title='Следующая страница →']";

  public OnlineCinemaPage clickGenreButtonFilmsCategory() {
    browser.waitForVisibility(By.xpath(GENRE_BUTTON_FILMS_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(GENRE_BUTTON_FILMS_XPATH));
    return this;
  }

  public OnlineCinemaPage selectGenreInFilmsByName(String genre) {
    browser.waitForVisibility(By.xpath(format(PATTERN_FOR_GENRE_IN_FILMS_XPATH, genre)),
        browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(format(PATTERN_FOR_GENRE_IN_FILMS_XPATH, genre)));
    return this;
  }

  private List<WebElement> getElementsDescriptions() {
    return browser.findElements(By.xpath(ELEMENTS_DESCRIPTION_XPATH));
  }

  public List<String> getTextFromElementsDescriptions() {
    List<WebElement> elements = getElementsDescriptions();
    List<String> textsOfElementsDescriptions = new ArrayList<>();
    for (int i = 0; i < elements.size(); i++) {
      textsOfElementsDescriptions.add(i, elements.get(i).getText());

    }
    return textsOfElementsDescriptions;
  }

  private List<WebElement> getNextPageButton() {
    return browser.findElements(By.xpath(NEXT_PAGE_BUTTON_XPATH));
  }

  public boolean ifNextPageExist(){
    return getNextPageButton().size()>0;
  }

  public OnlineCinemaPage clickNextPageButton() {
    browser.waitForVisibility(By.xpath(NEXT_PAGE_BUTTON_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(NEXT_PAGE_BUTTON_XPATH));
    return this;
  }

  public OnlineCinemaPage clickSeriesButton() {
    browser.waitForVisibility(By.xpath(SERIES_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(SERIES_XPATH));
    return this;
  }

  public OnlineCinemaPage clickGenreButtonSeriesCategory() {
    browser.waitForVisibility(By.xpath(GENRE_BUTTON_SERIES_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(GENRE_BUTTON_SERIES_XPATH));
    return this;
  }

  public OnlineCinemaPage selectGenreInSeriesByName(String genre) {
    browser.waitForVisibility(By.xpath(format(PATTERN_FOR_GENRE_IN_SERIES_XPATH, genre)),
        browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(format(PATTERN_FOR_GENRE_IN_SERIES_XPATH, genre)));
    return this;
  }

  public OnlineCinemaPage clickCartoonsButton() {
    browser.waitForVisibility(By.xpath(CARTOONS_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(CARTOONS_XPATH));
    return this;
  }

  public OnlineCinemaPage clickGenreButtonCartoonsCategory() {
    browser.waitForVisibility(By.xpath(GENRE_BUTTON_CARTOONS_XPATH), browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(GENRE_BUTTON_CARTOONS_XPATH));
    return this;
  }

  public OnlineCinemaPage selectGenreInCartoonsByName(String genre) {
    browser.waitForVisibility(By.xpath(format(PATTERN_FOR_GENRE_IN_CARTOONS_XPATH, genre)),
        browser.DEFAULT_TIMEOUT);
    browser.clickElement(By.xpath(format(PATTERN_FOR_GENRE_IN_CARTOONS_XPATH, genre)));
    return this;
  }
}
