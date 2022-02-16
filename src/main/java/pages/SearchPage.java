package pages;


import base.BasePageMethod;
import com.thoughtworks.gauge.Gauge;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchPage extends BasePageMethod {

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private WebDriver driver;


	public void checkAllResultsContainsKeyword(String keyword){
		try {
			List<WebElement> resultList = driver.findElements(By.xpath("//ul[@class='item-list search-results-list']/li//p[@class='item-description']"));
			for (final WebElement result : resultList) { ;
				Assert.assertTrue(result.getText().toLowerCase().contains(keyword.toLowerCase()));
			}
			Gauge.writeMessage("All results contains searched keyword");
		} catch (Exception e) {
			e.printStackTrace();
			Gauge.writeMessage("One or more results not contains searched keyword");
		}
	}

}
