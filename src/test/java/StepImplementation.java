import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

import static com.thoughtworks.gauge.datastore.DataStoreFactory.*;
import static org.assertj.core.api.Assertions.assertThat;


public class StepImplementation {


    @Step("Go to Hepsiburada homepage")
    public void implementation1() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        //assertThat(Driver.webDriver.getTitle()).contains("Gauge");
    }

    @Step("Go to Lely homepage")
    public void goToHomePage() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        //assertThat(Driver.webDriver.getTitle()).contains("Gauge");
    }

    @Step("Search product using keyword <keyword>")
    public void searchKeyword(String srcKeyword) {
        MainPage mainPage = new MainPage(Driver.webDriver);
        DataStore suiteStore = getSuiteDataStore();
        suiteStore.put("search_keyword", srcKeyword);
        mainPage.clickSearchButton();
        mainPage.checkIfSearchAreaVisible();
        mainPage.searchKeyword(srcKeyword);
    }

    @Step("Check all results contains searched keyword")
    public void checkAllResultContainsKeyword() {
        SearchPage searchPage = new SearchPage(Driver.webDriver);
        String searchKeyword = (String) getSuiteDataStore().get("search_keyword");
        searchPage.checkAllResultsContainsKeyword(searchKeyword);
    }


    @Step("Go to Lely TechDocs")
    public void goToTechDocs() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.goToTechDocs();
    }


    @Step("Select <product> from machine list")
    public void addBasketForSelectedSeller(String name) {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.selectMachine();
    }

    @Step("Check product catalog is displayed")
    public void checkProductCatalogIsDisplayed() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.isProductCatalogDisplayed();
    }

    @Step("View document on the list")
    public void viewDocument() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.viewDocument();
    }

    @Step("Check valid document is opened in new tab")
    public void checkValidDocumentIsOpenedNewTab() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.checkValidDocument();
    }

    @Step("Return main tab")
    public void returnMainTab() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.changeTabByTitle("Technical documents");
    }

    @Step("Download product catalog")
    public void downloadProductCatalog() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.downloadProductCatalog();
    }

    @Step("Check catalog is downloaded")
    public void checkCatalogIsDownloaded() {
        TechDocsPage techDocsPage = new TechDocsPage(Driver.webDriver);
        techDocsPage.isFileDownloaded("D-S006VT_-.pdf");
    }


}
