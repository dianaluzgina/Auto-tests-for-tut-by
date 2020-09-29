package services;

import logger.Log;
import screens.TutByPage;

public class GoToSectionsService {

  private static final int TAB_INDEX = 2;

  public static void goToOnlineCinemaSection() {
    Log.logInfo("Go to online cinema section");
    TutByPage page = new TutByPage();
    page.load()
        .clickSectionsButton()
        .clickAllSectionsButton()
        .clickOnlineCinemaLink()
        .switchToTheTabByIndex(TAB_INDEX);
  }
}
