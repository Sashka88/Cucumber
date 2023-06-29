package onliner.projectOnlinerCucumber.page;

import java.util.List;
import java.util.Locale;

import onliner.framework.baseElement.*;
import onliner.framework.BasePage;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;

import static onliner.framework.baseElement.BaseElement.waitUntilElementToBeInvisible;
import static onliner.framework.baseElement.BaseElement.waitUntilVisibilityOfAllElementsLocated;
import static onliner.projectOnlinerCucumber.testrunner.RunnerCucumber.softAssert;

public class TvPage extends BasePage {

  private String cbxMaker = "//ul//input[@type='checkbox' and @value='%s']";
  private String fieldPrice = "//input[@placeholder='до']";
  private String cbxResolution = "//input[@type='checkbox' and @value='%s']";
  private By fieldMinDiagonal =
          By.xpath("//select[contains(@data-bind, 'value: facet.value.from')]");
  private By fieldMaxDiagonal = By.xpath("//select[contains(@data-bind, 'value: facet.value.to')]");
  private By searchResults =
          By.xpath("//span[contains(@data-bind, 'html: product.extended_name || product.full_name')]");
  private By animation =
          By.xpath(
                  "//div[contains(@class, 'schema-filter-button__state_control schema-filter-button__state_animated']");
  private Label makerResults =
          new Label(
                  By.xpath(
                          "//span[contains(@data-bind, 'html: product.extended_name || product.full_name')]"));
  private Label priceResults =
          new Label(
                  By.xpath(
                          "//a[contains(@data-bind, 'attr: {href: $data.prices.html_url_with_region}')]/span"));
  private Label descriptionResults =
          new Label(By.xpath("//span[contains(@data-bind, 'html: product.description')]"));
  private Label lblPage =
          new Label(By.xpath("//h1[@class='schema-header__title js-schema-header_title']"));

  public TvPage(String title) {
    super();
    checkPageElementIsDisplayed(lblPage);
    checkPageTitle(title, lblPage);
  }

  public TvPage selectMaker(String tvMaker) {
    Checkbox tvMakerCheckbox = new Checkbox(By.xpath(String.format(cbxMaker, tvMaker.toLowerCase(Locale.ROOT))));
    tvMakerCheckbox.jsClick();
    waitUntilElementToBeInvisible(animation);
    return this;
  }

  public TvPage writePrice(String tvPrice) {
    TextBox txtBox = new TextBox(By.xpath(fieldPrice));
    txtBox.sendKeys(tvPrice);
    waitUntilElementToBeInvisible(animation);
    return this;
  }

  public TvPage selectResolution(String resolution) {
    Checkbox resolutionCheckbox = new Checkbox(By.xpath(String.format(cbxResolution, resolution)));
    resolutionCheckbox.jsClick();
    waitUntilElementToBeInvisible(animation);
    return this;
  }

  public TvPage selectDiagonal(String minDiagonal, String maxDiagonal) {
    DropdownMenu minDiagonalMenu = new DropdownMenu(fieldMinDiagonal);
    minDiagonalMenu.sendKeys(minDiagonal);
    DropdownMenu maxDiagonalMenu = new DropdownMenu(fieldMaxDiagonal);
    maxDiagonalMenu.sendKeys(maxDiagonal);
    waitUntilElementToBeInvisible(animation);
    return this;
  }

  public TvPage vailidateMaker(String tvMaker) {
    waitUntilVisibilityOfAllElementsLocated(searchResults);
    waitUntilElementToBeInvisible(animation);
    List<Label> currentTvMakers = makerResults.getElementsList();
    softAssert.assertTrue(currentTvMakers.stream().allMatch(currentTvMaker -> currentTvMaker.getText().contains(tvMaker)), "TvMaker is incorrect");
    return this;
  }

  public TvPage vailidatePrice(String price) {
    waitUntilElementToBeInvisible(animation);
    List<Label> productPrices = priceResults.getElementsList();
    softAssert.assertTrue(productPrices.stream().allMatch(productPrice -> Double.parseDouble(productPrice.getText().substring(0, 7).replaceAll(",", ".")) <= Double.parseDouble(price)),
            "Price is more than " + Double.parseDouble(price));
    return this;
  }

  public TvPage vailidateDiagonal(String minDiagonal, String maxDiagonal) {
    waitUntilElementToBeInvisible(animation);
    List<Label> productDescriptions = descriptionResults.getElementsList();
    softAssert.assertTrue(productDescriptions.stream().allMatch(productDescription -> Integer.parseInt(productDescription.getText().substring(0, 2)) <= Integer.parseInt(maxDiagonal)), "Max diagonal is incorrect");
    softAssert.assertTrue(productDescriptions.stream().allMatch(productDescription -> Integer.parseInt(productDescription.getText().substring(0, 2)) >= Integer.parseInt(minDiagonal)), "Min diagonal is incorrect");
    return this;
  }

  public TvPage vailidateResolution(String resolution) {
    waitUntilElementToBeInvisible(animation);
    List<Label> productDescriptions = descriptionResults.getElementsList();
    softAssert.assertTrue(productDescriptions.stream().allMatch(productDescription -> productDescription.getText().contains(resolution)), "Tv resolution is incorrect");
    return this;
  }
}