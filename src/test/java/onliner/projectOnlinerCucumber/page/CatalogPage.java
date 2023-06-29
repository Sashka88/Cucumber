package onliner.projectOnlinerCucumber.page;

import onliner.framework.BasePage;
import onliner.framework.baseElement.Label;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {

  private String lblMenuName = "//span[contains(text(), '%s')]";
  private String lblSubMenuName = "//div[@class='catalog-navigation-list__aside-title'][contains(text(), '%s')]";
  private String lblNextPage = "//div[contains(@class, 'catalog-navigation-list__aside-item_active')]//span[contains(text(),'%s')]";
  private Label lblPage = new Label(By.xpath("//div[@class = 'catalog-navigation__title']"));

  public CatalogPage(String title) {
    super();
    checkPageElementIsDisplayed(lblPage);
    checkPageTitle(title, lblPage);
  }

  public CatalogPage navigateMenu(String menuName) {
    Label lblMenu = new Label(By.xpath(String.format(lblMenuName, menuName)));
    lblMenu.checkIsDisplayedWithWait();
    lblMenu.click();
    return this;
  }

  public CatalogPage navigateSubMenu(String subMenuName) {
    Label lblSubMenu = new Label(By.xpath(String.format(lblSubMenuName, subMenuName)));
    lblSubMenu.checkIsDisplayedWithWait();
    lblSubMenu.click();
    return this;
  }

  public CatalogPage navigatePage(String pageName) {
    Label lblPage = new Label(By.xpath(String.format(lblNextPage, pageName)));
    lblPage.checkIsDisplayedWithWait();
    lblPage.click();
    return this;
  }
}