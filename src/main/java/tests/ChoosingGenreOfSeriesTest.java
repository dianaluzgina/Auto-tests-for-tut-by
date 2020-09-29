package tests;

import java.util.List;
import listener.TestListener;
import logger.Log;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import screens.OnlineCinemaPage;
import services.GoToSectionsService;

@Listeners({TestListener.class})
public class ChoosingGenreOfSeriesTest extends BaseTest {

  private OnlineCinemaPage onlineCinemaPage;
  private List<String> textsFromElementDescription;
  private static final String GENRE = "Фантастика";

  @BeforeMethod
  public void setUp() {
    Log.logInfo("Test started");
    GoToSectionsService.goToOnlineCinemaSection();
  }

  @Test
  public void choosingGenreOfSeries() {
    onlineCinemaPage = new OnlineCinemaPage();
    onlineCinemaPage.clickSeriesButton()
        .clickGenreButtonSeriesCategory()
        .selectGenreInSeriesByName(GENRE);
    boolean ifSeriesOfThisGenreExist = false;
    boolean ifSeriesContainsGenre = true;
    while (true) {
      try {
        textsFromElementDescription = onlineCinemaPage.getTextFromElementsDescriptions();
      } catch (StaleElementReferenceException e) {
        textsFromElementDescription = onlineCinemaPage.getTextFromElementsDescriptions();
      }
      if (textsFromElementDescription.size() > 0) {
        ifSeriesOfThisGenreExist = true;
        for (int i = 0; i < textsFromElementDescription.size(); i++) {
          if (!textsFromElementDescription.get(i).contains(GENRE)) {
            ifSeriesContainsGenre = false;
          }
        }
      }
      if (onlineCinemaPage.ifNextPageExist()) {
        onlineCinemaPage.clickNextPageButton();
      } else {
        break;
      }
    }
    SoftAssert anAssert = new SoftAssert();
    anAssert.assertEquals(ifSeriesOfThisGenreExist, true,
        "Series of this genre don't exist.");
    anAssert
        .assertEquals(ifSeriesContainsGenre, true,
            "There is the series which doesn't belong to genre.");
    anAssert.assertAll();
  }

}
