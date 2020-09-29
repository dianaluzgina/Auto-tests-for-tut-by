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
public class ChoosingGenreOfCartoonTest extends BaseTest {

  private OnlineCinemaPage onlineCinemaPage;
  private List<String> textsFromElementDescription;
  private static final String GENRE = "Комедия";

  @BeforeMethod
  public void setUp() {
    Log.logInfo("Test started");
    GoToSectionsService.goToOnlineCinemaSection();
  }

  @Test
  public void choosingGenreOfCartoons() {
    onlineCinemaPage = new OnlineCinemaPage();
    onlineCinemaPage.clickCartoonsButton()
        .clickGenreButtonCartoonsCategory()
        .selectGenreInCartoonsByName(GENRE);
    boolean ifCartoonsOfThisGenreExist = false;
    boolean ifCartoonContainsGenre = true;
    while (true) {
      try {
        textsFromElementDescription = onlineCinemaPage.getTextFromElementsDescriptions();
      } catch (StaleElementReferenceException e) {
        textsFromElementDescription = onlineCinemaPage.getTextFromElementsDescriptions();
      }
      if (textsFromElementDescription.size() > 0) {
        ifCartoonsOfThisGenreExist = true;
        for (int i = 0; i < textsFromElementDescription.size(); i++) {
          if (!textsFromElementDescription.get(i).contains(GENRE)) {
            ifCartoonContainsGenre = false;
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
    anAssert.assertEquals(ifCartoonsOfThisGenreExist, true,
        "Cartoons of this genre don't exist.");
    anAssert
        .assertEquals(ifCartoonContainsGenre, true, "There is cartoon which doesn't belong to genre.");
    anAssert.assertAll();
  }
}
