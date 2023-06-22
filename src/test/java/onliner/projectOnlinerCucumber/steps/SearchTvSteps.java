package onliner.projectOnlinerCucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.projectOnlinerCucumber.page.CatalogPage;
import onliner.projectOnlinerCucumber.page.TvPage;
import onliner.projectOnlinerCucumber.page.MainPage;

import static onliner.framework.browser.Browser.softAssert;


public class SearchTvSteps {

    private static MainPage mainPage;
    private static CatalogPage catalogPage;
    private static TvPage tvPage;

    @Given("Onliner page is open")
    public void onlinerPageIsOpen() {
        mainPage = new MainPage();
    }

    @When("user navigates to menu section {string}")
    public void navigateMenuSection(String menuSection) {
        mainPage.navigateSection(menuSection);
    }


    @And("user on {string} page navigates from catalog section {string} to subSection {string} and chooses {string}")
    public void selectCatalogItems(String currentPageName, String menuName, String subMenuName,
                                   String nextPageName) {
        catalogPage = new CatalogPage(currentPageName);
        catalogPage.navigateMenu(menuName);
        catalogPage.navigateSubMenu(subMenuName);
        catalogPage.navigatePage(nextPageName);
    }

    @And("user on {string} page selects {string}, {string}, {string}, {string} and {string}")
    public void selectTvParameters(String currentPageName, String tvMaker, String price, String resolution,
                                   String minDiagonal, String maxDiagonal) {
        tvPage = new TvPage(currentPageName);
        tvPage.selectMaker(tvMaker);
        tvPage.writePrice(price);
        tvPage.selectResolution(resolution);
        tvPage.selectDiagonal(minDiagonal, maxDiagonal);
    }

    @Then("results must match the requirements of {string}, {string}, {string}, {string} and {string}")
    public void validateParameters(String tvMaker, String price, String resolution,
                                   String minDiagonal, String maxDiagonal) {
        tvPage.vailidateMaker(tvMaker);
        tvPage.vailidatePrice(price);
        tvPage.vailidateResolution(resolution);
        tvPage.vailidateDiagonal(minDiagonal, maxDiagonal);
        softAssert.assertAll();
    }
}
