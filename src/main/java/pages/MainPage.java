package pages;


import base.BasePageMethod;
import com.thoughtworks.gauge.Gauge;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePageMethod {

	public MainPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private WebDriver driver;
	private By btnSearch = By.xpath("//div[@data-do='trigger-search']//*[name()='svg']");
	private By frmSearch = By.xpath("//form[@data-do='global-search-dropdown']");
	private By txtSearch = By.xpath("//input[@id='global-search']");
	private By btnInnerSearch = By.xpath("//form[@data-do='global-search-dropdown']//button[text()='Search']");


	public void clickSearchButton() {
		try {
			super.clickWebElement(btnSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkIfSearchAreaVisible() {
		try {
			super.checkElementIsDisplayedByLocator(frmSearch);
			Gauge.writeMessage("Search area is displayed");
		} catch (Exception e) {
			e.printStackTrace();
			Gauge.writeMessage("Search area is not displayed");
		}
	}

	public void searchKeyword(String keyword) {
		try {
			super.sendKeysToElement(txtSearch,keyword);
			super.clickWebElement(btnInnerSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
