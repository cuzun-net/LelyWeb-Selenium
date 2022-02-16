package base;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

public class BasePageMethod {
    private WebDriverWait webDriverWait;
    private JavascriptExecutor jsExec;
    private WebElement lastElement = null;
    private String lastBorder = null;
    private WebDriver driver;


    public BasePageMethod(WebDriver driver) {
        this.driver = driver;
        jsExec = (JavascriptExecutor) driver;
    }


    protected void sendKeysToElement(By locator, String text) {
        this.unHighLightLastElement();
        if (this.checkElementIsDisplayedByLocator(locator)) {
            WebElement element = this.waitUntilVisibleByLocator(locator);
            this.highlightElement(element);
            element = this.waitUntilPresenceOfElementByLocator(locator);
            element.clear();
            element.sendKeys(text);
            //this.waitUntilJQueryReadyAndJSReady();
        }
    }


    public void clickWebElement(By locator) {
        this.unHighLightLastElement();
        waitUntilVisibleByLocator(locator);
        if (this.checkElementIsDisplayedByLocator(locator)) {
            WebElement element = this.waitUntilVisibleByLocator(locator);
            this.scrollTo(element, 175);
            this.waitUntilClickableByListOfElement(element);
            this.highlightElement(locator);
            this.waitUntilClickableByListOfElement(element).click();
            //this.waitUntilJQueryReadyAndJSReady();
        }
    }


    protected void clickWebElement(WebElement element) {
        this.scrollTo(element, 100);
        this.waitUntilClickableByListOfElement(element).click();
        //this.waitUntilJQueryReadyAndJSReady();
    }


    protected boolean checkElementIsDisplayedByLocator(By locator) {
        try {
            return this.waitUntilPresenceOfElementByLocator(locator).isDisplayed();
        } catch (Exception var3) {
            return false;
        }
    }

    protected WebElement waitUntilPresenceOfElementByLocator(By locator) {
        this.waitMs(2000L);
        WebElement element = null;
        try {
            FluentWait wait = (new FluentWait(driver)).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception var4) {
        }
        return element;
    }

    protected WebElement waitUntilClickableByListOfElement(WebElement webElement) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception var4) {
        }

        return element;
    }

    protected void waitMs(long milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }
    }



    private void highlightElement(WebElement element) {
        this.lastElement = element;
        this.lastBorder = element.getCssValue("border");
        this.jsExec.executeScript("arguments[0].style.border = '3px solid red'", element);
    }

    private void highlightElement(By byLocator) {
        this.highlightElement(this.waitUntilVisibleByLocator(byLocator));
    }


    protected void unHighLightLastElement() {
        if (this.lastElement != null && StringUtils.isNotEmpty(this.lastBorder)) {
            try {
                if (this.lastElement.isDisplayed()) {
                    this.jsExec.executeScript("arguments[0].style.border = '" + this.lastBorder + "'", this.lastElement);
                }
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException
                    | org.openqa.selenium.TimeoutException | ElementNotVisibleException ignore) {
            }
        }
    }


    public WebElement waitUntilVisibleByLocator(By locator) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }


    protected void switchTab(String title) {
        Set<String> allTabs = driver.getWindowHandles();
        for (String eachTab : allTabs) {
            driver.switchTo().window(eachTab);
            if (driver.getTitle().contains(title)) {
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.waitMs(1000L);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();
        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                return flag=true;
        }
        return flag;
    }

    protected void scrollTo(WebElement element, int margin) {
        this.scrollTo(element.getLocation().x, element.getLocation().y - margin);
    }

    protected void scrollTo(int x, int y) {
        this.jsExec.executeScript("scrollTo(" + x + "," + y + ");", new Object[0]);
    }


}
