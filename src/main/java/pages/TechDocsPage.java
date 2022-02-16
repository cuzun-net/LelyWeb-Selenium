package pages;


import base.BasePageMethod;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.datastore.DataStore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static com.thoughtworks.gauge.datastore.DataStoreFactory.getSuiteDataStore;

public class TechDocsPage extends BasePageMethod {

	public TechDocsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private WebDriver driver;
	String downloadPath = "C:\\Users\\Administrator\\Downloads";
	private By sectionCatalog = By.xpath("//section[@class='tab-content is-active']");
	private By btnDownload = By.xpath("(//section[@class='result-item']//a[contains(text(),'Download')])[1]");


	public void goToTechDocs(){
		driver.get("https://www.lely.com/techdocs/");
	}

	public void selectMachine() {
		try {
			Select machineList = new Select(driver.findElement(By.xpath("//select[@data-do='enhanced-select']")));
			machineList.selectByVisibleText("Luna EUR");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isProductCatalogDisplayed() {
		try {
			super.checkElementIsDisplayedByLocator(sectionCatalog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewDocument() {
		try {
			DataStore suiteStore = getSuiteDataStore();
			WebElement linkDocument = driver.findElement(By.xpath("(//a[contains(text(),'View this document')])[1]"));
			suiteStore.put("document_url", linkDocument.getAttribute("href"));
			super.clickWebElement(linkDocument);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkValidDocument() {
		try {
			String validURL = (String) getSuiteDataStore().get("document_url");
			this.changeTabByTitle("D-S006");
			Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(validURL));
			Gauge.writeMessage("Valid document is opened in new tab");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadProductCatalog() {
		try {
			super.clickWebElement(btnDownload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isFileDownloaded(String fileName) {
		try {
			Assert.assertTrue(isFileDownloaded(downloadPath, fileName), "Failed to download Expected document");
			Gauge.writeMessage("Document is downloaded succesfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void changeTabByTitle(String title) {
		try {
			super.switchTab(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
