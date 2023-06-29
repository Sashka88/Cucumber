package onliner.projectOnlinerCucumber.page;

import onliner.framework.BasePage;
import onliner.framework.baseElement.Label;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

  private String lblNavigation = "//span[@class='b-main-navigation__text'][contains(text(), '%s')]";
  private Label lblPage = new Label(By.xpath("//nav[@class='b-top-navigation']"));

  public MainPage() {
    super();
    checkPageElementIsDisplayed(lblPage);
  }

  public MainPage navigateSection(String sectionName) {
    Label lblSection = new Label(By.xpath(String.format(lblNavigation, sectionName)));
    lblSection.checkIsDisplayedWithWait();
    lblSection.click();
    return this;
  }
}