package screens;

import browser.Browser;

public class BasePage {

  protected Browser browser;

  protected BasePage() {
    browser = Browser.getInstance();
  }
}

