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
public class ChoosingGenreOfFilmsTest extends BaseTest {

  private OnlineCinemaPage onlineCinemaPage;
  private List<String> textsFromElementDescription;
  private static final String GENRE = "Фэнтези";

  @BeforeMethod
  public void setUp() {
    Log.logInfo("Test started");
    GoToSectionsService.goToOnlineCinemaSection();
  }

  @Test
  public void choosingGenreOfFilms() {
    onlineCinemaPage = new OnlineCinemaPage();
    onlineCinemaPage.clickGenreButtonFilmsCategory()
        .selectGenreInFilmsByName(GENRE);
    boolean ifFilmsOfThisGenreExist = false;
    boolean ifFilmContainsGenre = true;
    while (true) {
      try {
        textsFromElementDescription = onlineCinemaPage.getTextFromElementsDescriptions();
      } catch (StaleElementReferenceException e) {
        textsFromElementDescription = onlineCinemaPage.getTextFromElementsDescriptions();
      }
      if (textsFromElementDescription.size() > 0) {
        ifFilmsOfThisGenreExist = true;
        for (int i = 0; i < textsFromElementDescription.size(); i++) {
          if (!textsFromElementDescription.get(i).contains(GENRE)) {
            ifFilmContainsGenre = false;
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
    anAssert.assertEquals(ifFilmsOfThisGenreExist, true,
        "Films of this genre don't exist.");
    anAssert
        .assertEquals(ifFilmContainsGenre, true, "There is film which doesn't belong to genre.");
    anAssert.assertAll();
  }
}
