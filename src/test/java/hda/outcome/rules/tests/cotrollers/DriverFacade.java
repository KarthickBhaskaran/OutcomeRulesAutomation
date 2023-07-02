package hda.outcome.rules.tests.cotrollers;

import cucumber.api.Scenario;
import hda.outcome.rules.tests.General.BrowserTypes;
import hda.outcome.rules.tests.utilities.TestUtilities;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

// This class defines all basic operations for WebDriver which is used by all page objects
public class DriverFacade extends WebdriverHelper {

    WebDriverWait webDriverWait;

    Scenario currentScenario;
    String currentWebElementToText;
    private WebDriver webDriver;
    private WebElement currentWebElement;
    private Boolean executionResult;
    private TestUtilities utilities = new TestUtilities();

    public boolean isTestCaseFailure() {
        return testCaseFailure;
    }

    public void setTestCaseFailure(boolean testCaseFailure) {
        this.testCaseFailure = testCaseFailure;
    }

    private boolean testCaseFailure = false;

    // Use this variable if required. Setting it just to make this class aware of
    // the current page its operating on
    // at any given step
    private BasePage currentPage;

    private String currentPageName;

    public String UACsCovered = "";

    public String currentUACNumber = "";

    public SoftAssertions softAssertions;

    public String PNR_Num;

    public String QUOTE_Num;
    public String Foreign_Booking_Ref_Num;

    public String BKG_Num;

    public DriverFacade() {
        softAssertions = new SoftAssertions();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setCurrentScenario(Scenario scenario) {
        this.currentScenario = scenario;
    }

    public void setUACNumber(String UAC) {
        this.currentUACNumber = UAC;

    }

    public String Environment = ReadConfigProperty2("Env");
    //public String Environment;

    public String ReadConfigProperty2(String propertyName) {
        Path root = Paths.get(".").normalize().toAbsolutePath();
        String pathToConfigFile = root.toString() + "/src/test/resources/config.properties";
        try (InputStream input = new FileInputStream(pathToConfigFile)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            return prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ERROR";
        }
    }


    public void SetEnv(String envName) {
        Environment = envName;
    }

    public void setCurrentPage(BasePage basePage) {
        this.currentPage = basePage;
        this.currentPageName = basePage.getClass().getName();
    }

    // this method is triggered from the @before cucumber hoook in the
    // 'CucumberHooks' class
    public void setUpWebDriverController(BrowserTypes browserTypes) {
        if (browserTypes == BrowserTypes.CHROME) {
            //System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            //System.setProperty("webdriver.chrome.driver", FetchDriverPath(BrowserTypes.CHROME));
            System.setProperty("webdriver.chrome.driver","C:\\Users\\karthick.bhaskaran\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
            prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
            prefs.put("profile.default_content_setting_values.geolocation", 2);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);prefs.put("profile.password_manager_enabled", false);
            prefs.put("autofill.profile_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
            //options.addArguments("start-maximized");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-single-click-autofill");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("useAutomationExtension", "false");
            options.addArguments("disable-popup-blocking", "true");




            //options.addArguments(
                    //"--headless",
                    //   options.addArguments("--disable-gpu",
                    //"--window-size=1920,1200",
             //       "--window-size=1680,1050", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
            this.webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(FetchGlobalImplicitWaitTimeout(), TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        } else if (browserTypes == BrowserTypes.FIREFOX) {

            webDriver.manage().timeouts().implicitlyWait(FetchGlobalImplicitWaitTimeout(), TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        } // put an 'else if' after this for Mobile user agents etc
    }

    public void EstablishDataTableConnection() {
        //System.out.println(date() + " - Establish connection to Test Data Excel");
        utilities.EstablishConnectionWithDataTable();
    }

    public void EstablishResultTableConnection() {
        // System.out.println(date() + " - Establish connection to Test Result Excel");
        utilities.EstablishConnectionWithResultTable();
    }

    public String ReadDataFromDatatable(String refID, String field) {
        return utilities.ReadData(refID, field, "PassengerDetails");
    }

    public String ReadDataFromExcel(String refID, String field, String table) {
        return utilities.ReadData(refID, field, table);
    }

    public void WriteDatatoExcel(String tcnum, String field, String table, String value) {
        utilities.WriteData(tcnum, field, table, value);
    }

    public void WriteData(String tcnum, String field, String table, String value) {
        utilities.WriteData(tcnum, field, table, value);
    }


    public String ReadDataFromBookingDetailsHotelTab(String refID, String field) {
        return utilities.ReadData(refID, field, "BookingDetails_Hotel");
    }

    public String getUserName() {
        return GetUserName();
    }

    public String getPassword() {
        return GetPassword();
    }

    public String DMCgetUserName() {
        return DMCGetUserName();
    }

    public String DMCgetPassword() {
        return DMCGetPassword();
    }

    public String TravolutionarygetUserName() {
        return TravolutionaryGetUserName();
    }

    public String TravolutionarygetPassword() {
        return TravolutionaryGetPassword();
    }

    public String ECAgetUserName() {
        return ECAGetUserName();
    }

    public String ECAgetPassword() {
        return ECAGetPassword();
    }

    public String TripsgetUserName() {
        return TripsGetUserName();
    }

    public String TripsgetPassword() {
        return TripsGetPassword();
    }


    public String getSecretquespwd() {
        return GetSecretquespwd();
    }

    public String getURL() {
        return GetURL();
    }

    public boolean getStepResult() {
        return this.executionResult;
    }

    private void setStepResult(Boolean result) {
        this.executionResult = result;
    }

    public DriverFacade VerifyTitle(String pageTitle) {
        if (webDriver.getTitle().equals(pageTitle)) {
            setStepResult(true);
        } else {
            setStepResult(false);
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade assertEqual(String expected, String actual) {
        if (expected.equals(actual)) setStepResult(true);
        else setStepResult(false);

        ReportOnFailure("Expected Value - " + expected + " Actual Value - " + actual);
        return this;
    }

    public DriverFacade assertEqual(int expected, int actual) {
        if (expected == actual) setStepResult(true);
        else setStepResult(false);
        ReportOnFailure("Expected Value - " + expected + " Actual Value - " + actual);
        return this;
    }

    public DriverFacade assertEqual(Boolean expected, Boolean actual) {
        if (expected.equals(actual)) setStepResult(true);
        else setStepResult(false);

        ReportOnFailure("Expected Value - " + expected + " Actual Value - " + actual);
        return this;
    }

    public DriverFacade assertEqual(float expected, float actual) {
        if (expected == actual) setStepResult(true);
        else setStepResult(false);

        ReportOnFailure("Expected Value - " + expected + " Actual Value - " + actual);
        return this;
    }

    public DriverFacade assertEqual(boolean status) {
        Assert.assertTrue("Soft assertion", false);
        return this;
    }

    /*
     * public DriverFacade JSExec(){
     * JavascriptExecutor js= (JavascriptExecutor) webDriver;
     * js.
     * executeScript("document.getElementById('shiftTabField').value='4293 1891 0000 0008'"
     * );
     *
     * return this;
     * }
     */

    public DriverFacade assertSoft(String expected, String actual) {

        // softAssertions.assertThat(Expected).isEqualTo(Actual);
        if (expected.equals(actual)) {
            setStepResult(true);
        } else {
            // softAssertions.fail("failed");
            // softAssertions.assertThat(actual).isEqualTo(expected).is;
            // softAssertions.assertThat(actual.equals(expected)).isTrue();

            // softAssertions.assertThat(false).isTrue();
            // softAssertions.assertThat(actual.equals(expected));
            // softAssertions.assertThat(softAssertions.equals(expected.equalsIgnoreCase(actual)));
            // softAssertions.equals(expected.equalsIgnoreCase(actual));
            // softAssertions.assertAll();
            this.setTestCaseFailure(true);
            ReportOnFailureWithSoftAssertion("Expected Value - " + expected + " Actual Value - " + actual);
            // setStepResult(false);
        }

        return this;
    }

    public void assertSoft(boolean Expected, boolean Actual) {
        softAssertions.assertThat(Expected).isEqualTo(Actual);
    }

    public DriverFacade navigateToUrl(String url) {
        webDriver.get(url);
        return this;
    }

    public String tcID;

    public void setTcID(String tcNo) {
        tcID = tcNo;
        System.out.println(date() + " - Execution started for " + tcNo);
    }

    public DriverFacade PageloadTime(long time) {
        webDriver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
        return this;
    }

    public String date() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public WaitConditions waitFor() {
        WebDriverWait wait = new WebDriverWait(webDriver, FetchGlobalExplicitWaitTimeout());
        webDriverWait = wait;
        return this.new WaitConditions();
    }

    public WaitConditions waitFor(long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        webDriverWait = wait;
        return this.new WaitConditions();
    }

    public DriverFacade switchtab() {
        String currentHandle = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String actual : handles) {

            if (!actual.equalsIgnoreCase(currentHandle)) {
                // switching to the opened tab
                webDriver.switchTo().window(actual);

                // opening the URL saved.
                // webDriver.get(urlToClick);
            }
        }

        return this;
    }

    public DriverFacade justclick(By bySelector) {
        webDriver.findElement(bySelector).click();
        return this;
    }

    public DriverFacade selectOptional(By passengerTitleField, String valueToBeSelected) {
        findElementoptional(passengerTitleField);
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                sel.selectByValue(valueToBeSelected);
                setStepResult(true);
            } catch (Exception e) {
            }
        }
        return this;
    }

    public DriverFacade findElement(By bySelector) {
        currentWebElement = null;
        currentWebElementToText = "";
        try {
            waitFor().WebElementToBePresent(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;
            setStepResult(true);
            JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            currentWebElementToText = bySelector.toString();
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade findElementoptional(By bySelector) {
        currentWebElement = null;
        currentWebElementToText = "";
        HardWait(1000);
        try {
            // waitFor().WebElementToBePresentOptional(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;
            setStepResult(true);
            //JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            currentWebElementToText = bySelector.toString();
        }

        return this;
    }

    public DriverFacade findElementoptionalWithwait(By bySelector) {
        currentWebElement = null;
        currentWebElementToText = "";
        HardWait(1000);
        try {
            waitFor().WebElementToBePresentOptional(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;
            setStepResult(true);
            //JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            currentWebElementToText = bySelector.toString();
        }

        return this;
    }

    public DriverFacade sendKeysOptional(String text) {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();

            switch (text.toUpperCase()) {
                case "ENTER":
                    webElement.sendKeys(Keys.ENTER);
                    break;

                case "TAB":
                    webElement.sendKeys(Keys.TAB);
                    break;

                case "CTRL+A":
                    webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    break;

                case "SHIFT+C":
                    webElement.sendKeys(Keys.chord(Keys.SHIFT, "c"));
                    break;

                case "SHIFT+T":
                    // webElement.sendKeys(Keys.chord(Keys.SHIFT, "t"));
                    Actions action = new Actions(webDriver);
                    action.keyDown(Keys.SHIFT).sendKeys("t").keyUp(Keys.SHIFT).perform();
                    break;
            }
            setStepResult(true);
        }

        return this;
    }

    public Boolean checkElementIsEnabled() {
        Boolean status = false;
        if (getStepResult()) {

            try {
                status = getElement().isEnabled();
                //System.out.println("inside func" + status);
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return status;
    }

    public DriverFacade setTextOptional(String text) {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();
            webElement.click();
            webElement.sendKeys(text);
            // SendkeysWrapper(webElement, text);
            setStepResult(true);
        }

        return this;
    }

    public Boolean checkelementoptional(By bySelector) {
        currentWebElement = null;
        currentWebElementToText = "";
        try {
            // waitFor().WebElementToBePresentOptional(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
    }

    public DriverFacade SwitchTo(By bySelector) {
        webDriver.switchTo().frame(webDriver.findElement(bySelector));
        return this;
    }




    public DriverFacade SwitchToDefaultContext() {
        webDriver.switchTo().defaultContent();
        return this;
    }

    public DriverFacade findElementWithoutHighlight(By bySelector) {
        currentWebElement = null;
        currentWebElementToText = "";
        try {
            // waitFor().WebElementToBePresent(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;
            setStepResult(true);
            // JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            currentWebElementToText = bySelector.toString();
        }
        ReportOnFailure();
        return this;
    }

    public String getCssValue(By bySelector, String propertyName) {
        String value = "";
        currentWebElement = null;
        currentWebElementToText = "";
        try {
            //waitFor().WebElementToBePresent(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;
            value = webElement.getCssValue(propertyName);
            setStepResult(true);
            JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            currentWebElementToText = bySelector.toString();
        }
        ReportOnFailure();
        return value;
    }

    public DriverFacade get_text_color(By bySelector, String colors) {
        currentWebElement = null;
        currentWebElementToText = "";
        try {
            //waitFor().WebElementToBePresent(bySelector);
            WebElement webElement = webDriver.findElement(bySelector);
            currentWebElement = webElement;

            String cssValue = webDriver.findElement(bySelector).getCssValue("color");
            System.out.println("RGB_Color: " + cssValue);

            // RGB to HEX
            String hex = Color.fromString(cssValue).asHex();
            System.out.println("HEX_Color: " + hex);

            switch (colors) {
                case "red":

                    "ef".equals(hex.contains("ef"));
                    writeInReport("Data From Application -->" + hex);
                    // Assert.assertTrue(hex.contains("ef"));
                    break;

                case "white":
                    "ff".equals(hex.contains("ff"));
                    writeInReport("Data From Application -->" + hex);
                    // Assert.assertTrue(hex.contains("ff"));
                    break;
                case "grey":
                    "#a9a9a9".equals(hex.contains("#a9a9a9"));
                    writeInReport("Data From Application -->" + hex);
                    // Assert.assertTrue(hex.contains("ff"));
                    break;
            }
            setStepResult(true);
            JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            currentWebElementToText = bySelector.toString();
        }
        ReportOnFailure();
        return this;
    }

    public List<WebElement> findElements(By bySelector) {

        List<WebElement> webElements = null;
        try {
            // waitFor().WebElementToBePresent(bySelector);
            webElements = webDriver.findElements(bySelector);
            // currentWebElement = webElement;
            setStepResult(true);
            // JSExecutorToHighlightElelemt();
        } catch (NoSuchElementException e) {
            setStepResult(false);
            // currentWebElementToText = bySelector.toString();
        }
        ReportOnFailure();
        return webElements;
    }

    public Boolean verifyWebElementIsPresent(By bySelector) {

        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> elements = webDriver.findElements(bySelector);
        if (elements.size() == 0) {
            webDriver.manage().timeouts().implicitlyWait(FetchGlobalImplicitWaitTimeout(), TimeUnit.SECONDS);
            return false;
        } else {
            webDriver.manage().timeouts().implicitlyWait(FetchGlobalImplicitWaitTimeout(), TimeUnit.SECONDS);
            return true;
        }
    }

    public Boolean verifyWebElementIsPresentWithWait(By bySelector) {

        List<WebElement> elements = webDriver.findElements(bySelector);
        if (elements.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String getWebPageURL() {

        return webDriver.getCurrentUrl();
    }

    public DriverFacade JSExecutor(String text) {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red')", currentWebElement);
        js.executeScript("arguments[0].setAttribute('value', 'Saddam')", currentWebElement);

        ReportOnFailure();
        return this;

    }

    public DriverFacade scrollDown() {
        JavascriptExecutor jse2 = (JavascriptExecutor) getWebDriver();
        jse2.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    public DriverFacade exitpage() {
        webDriver.close();
        return this;
    }

    public DriverFacade scrollToElement() {
        /*
         * JavascriptExecutor jse2 = (JavascriptExecutor) getWebDriver();
         * jse2.executeScript("arguments[0].scrollIntoView(true);", getElement());
         * return this;
         */

        JavascriptExecutor jse2 = (JavascriptExecutor) getWebDriver();
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" + "var elementTop = arguments[0].getBoundingClientRect().top;" + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        jse2.executeScript(scrollElementIntoMiddle, getElement());
        return this;
    }

    public WebElement getCurrentWebElement() {
        return currentWebElement;
    }

    public DriverFacade JSExecutorToHighlightElelemt() {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        for (int i = 0; i <= 100; i++) {
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red')", currentWebElement);

        }
        js.executeScript("arguments[0].style.border=''", currentWebElement);
        ReportOnFailure();
        return this;
    }

    public DriverFacade JSExecutorToZoom(String zoomLevel) {
        JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
        executor.executeScript("document.body.style.zoom = '" + zoomLevel + "'");
        ReportOnFailure();
        return this;
    }

    public DriverFacade clickWebElement() {
        if (getStepResult()) {
            try {
                getElement().click();
                setStepResult(true);
            } catch (ElementClickInterceptedException e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }


    public DriverFacade clickWebElementOptional() {
        if (getStepResult()) {
            try {
                getElement().click();
                setStepResult(true);
            } catch (ElementClickInterceptedException e) {

            }
        }

        return this;
    }

    public DriverFacade sendKeystOptional(String value) {
        if (getStepResult()) {
            try {
                getElement().sendKeys(value);
                setStepResult(true);
            } catch (ElementClickInterceptedException e) {

            }
        }

        return this;
    }

    public DriverFacade clickWebElementWithoutReport() {
        if (getStepResult()) {
            try {
                getElement().click();
                setStepResult(true);
            } catch (ElementClickInterceptedException e) {
                setStepResult(false);
            }
        }
        return this;
    }

    public String getWebElementTextOptional() {
        String elementText = "";
        if (getStepResult()) {

            try {
                elementText = getElement().getText();
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        // ReportOnFailure();
        return elementText;
    }

    public String getWebElementText() {
        String elementText = "";
        if (getStepResult()) {

            try {
                elementText = getElement().getText();
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return elementText;
    }

    public DriverFacade IsWebElementEnabled() {
        if (getStepResult()) {
            try {
                if (getElement().isEnabled()) setStepResult(true);
                else setStepResult(false);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure("Element is not Enabled");
        return this;
    }

    public Boolean checkElementIsSelected() {
        Boolean status = false;
        if (getStepResult()) {

            try {
                status = getElement().isSelected();
                System.out.println("inside func" + status);
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return status;
    }

    public DriverFacade IsWeblElementDisabled(boolean value) {
        if (getStepResult()) {
            try {
                Assert.assertEquals(value, getElement().isEnabled());
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public Boolean IsWebElementDisplayed() {
        Boolean status = false;
        if (getStepResult()) {

            try {
                status = getElement().isDisplayed();
                // System.out.println("inside func"+status);
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return status;
    }

    public Boolean IsWebElementDisabled() {
        Boolean status = null;
        if (getStepResult()) {

            try {
                status = getElement().isEnabled();
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return status;
    }

    public Boolean checkWebElementEnabled() {
        Boolean status = null;
        if (getStepResult()) {

            try {
                status = getElement().isEnabled();
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return status;
    }

    public String getAttribute(String atribute) {
        String elementText = "";
        if (getStepResult()) {

            try {
                elementText = getElement().getAttribute(atribute);
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return elementText;
    }

    public DriverFacade webElementIsSelected() {
        String UACNumber = "PBD-196-003";
        Boolean bFlag = false;
        if (getStepResult()) {
            try {
                bFlag = getElement().isSelected();
                Assert.assertEquals(true, bFlag);
                setStepResult(true);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade clearText() {
        HardWait(200);
        WebDriver driver = getWebDriver();
        if (getStepResult()) {
            WebElement webElement = getElement();
            webElement.click();
            webElement.clear();
            setStepResult(true);
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade setText(String text) {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();
            webElement.click();
            webElement.sendKeys(text);
            HardWait(200);
            // SendkeysWrapper(webElement, text);
            setStepResult(true);
        }
        ReportOnFailure();
        return this;
    }



    public DriverFacade newsendKeys(By bySelector, String value) {
        webDriver.findElement(bySelector).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        webDriver.findElement(bySelector).sendKeys(value);
        return this;
    }

    public DriverFacade newclickElement(By bySelector) {
        webDriver.findElement(bySelector).click();
        return this;
    }

    public DriverFacade clickAndSetText(String text) {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();
            Actions act = new Actions(webDriver);
            act.click(webElement).sendKeys(webElement, text).build().perform();

            setStepResult(true);
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade moveToExpectedElement() {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();
            Actions act = new Actions(webDriver);
            act.moveToElement(webElement).perform();
            HardWait(2000);
            setStepResult(true);
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade moveToExpectedElementAndClickShiftC() {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();
            Actions act = new Actions(webDriver);
            act.moveToElement(webElement).perform();
            act.sendKeys(Keys.chord(Keys.SHIFT, "C")).perform();
            HardWait(2000);
            setStepResult(true);
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade sendKeys(String text) {
        HardWait(100);
        if (getStepResult()) {
            WebElement webElement = getElement();

            switch (text.toUpperCase()) {
                case "ENTER":
                    webElement.sendKeys(Keys.ENTER);
                    break;
                case "PatientLookup":
                    webElement.sendKeys(Keys.ARROW_DOWN, Keys.TAB, Keys.TAB, Keys.ENTER);
                    break;
                case "DownArrow":
                    webElement.sendKeys(Keys.ARROW_DOWN);
                    break;
                case "UpArrow":
                    webElement.sendKeys(Keys.ARROW_UP);
                    break;
                case "TAB":
                    webElement.sendKeys(Keys.TAB);
                    break;
                case "PageDown":
                    webElement.sendKeys(Keys.PAGE_DOWN);
                    break;
                case "PageUp":
                    webElement.sendKeys(Keys.PAGE_UP);
                    break;
                case "CTRL+A":
                    webElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
                    break;

                case "SHIFT+C":
                    webElement.sendKeys(Keys.chord(Keys.SHIFT, "c"));
                    break;

                case "SHIFT+T":
                    // webElement.sendKeys(Keys.chord(Keys.SHIFT, "t"));
                    Actions action = new Actions(webDriver);
                    action.keyDown(Keys.SHIFT).sendKeys("t").keyUp(Keys.SHIFT).perform();
                    break;
            }
            setStepResult(true);
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade verifyWebElementText(String text) {
        if (getStepResult()) {
            WebElement webElement = getElement();
            System.out.println(webElement.getText());
            if (webElement.getText().equals(text)) {
                setStepResult(true);
            } else {
                setStepResult(false);

                ReportOnFailure("Expected text on field was" + "'" + text + "'.But found '" + webElement.getText() + "'");
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade verifyWebElementAttribute(String attribute, String text) {
        if (getStepResult()) {
            WebElement webElement = getElement();
            // System.out.println(webElement.getText());
            if (webElement.getAttribute(attribute).equals(text)) {
                setStepResult(true);
            } else {
                setStepResult(false);

                ReportOnFailure("Expected text on field was" + "'" + text + "'.But found '" + webElement.getText() + "'");
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade verifySelectedWebElementText(String text) {
        String combobox_text;
        if (getStepResult()) {
            WebElement webElement = getElement();
            Select comboBox = new Select(webElement);
            combobox_text = comboBox.getFirstSelectedOption().getAttribute("value");
            System.out.println(combobox_text);
            if (combobox_text.equals(text)) {
                setStepResult(true);
            } else {
                setStepResult(false);

                ReportOnFailure("Expected text on field was" + "'" + text + "'.But found '" + combobox_text + "'");
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade verifySelectedWebElementTextIgnoingCase(String actual, String expected) {
        if (getStepResult()) {
            if (actual.toLowerCase().contains(expected.toLowerCase())) {
                setStepResult(true);
            } else {
                setStepResult(false);
                ReportOnFailure("Expected text on field was" + "'" + expected.toLowerCase() + "'.But found '" + actual.toLowerCase() + "'");
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade verifySelectedWebElementTextasText(String text) {
        String combobox_text;
        if (getStepResult()) {
            WebElement webElement = getElement();
            Select comboBox = new Select(webElement);
            combobox_text = comboBox.getFirstSelectedOption().getText();
            System.out.println(combobox_text);
            if (combobox_text.equals(text)) {
                setStepResult(true);
            } else {
                setStepResult(false);

                ReportOnFailure("Expected text on field was" + "'" + text + "'.But found '" + combobox_text + "'");
            }
        }
        ReportOnFailure();
        return this;
    }

    public String getSelectedWebElementText(By bySelector) {
        findElement(bySelector);
        String combobox_text = null;

        WebElement webElement = getElement();
        Select comboBox = new Select(webElement);
        combobox_text = comboBox.getFirstSelectedOption().getText();

        return combobox_text;
    }

    private void SendkeysWrapper(WebElement webElement, String text) {
        for (int i = 0; i < text.length(); i++) {
            webElement.sendKeys(Character.toString(text.charAt(i)));
            HardWait(100);
        }
    }

    // Always use this function to get the webelement rather than directly using
    // 'currentWebElement' in all methods
    // in this class
    private WebElement getElement() {
        try {
            // this is an additional null pointer safety check.
            currentWebElement.getLocation();
        } catch (NullPointerException e) {
            System.out.println("'CurrentWebElement' property of DriverFacade is null");
            setStepResult(false);
            ReportOnFailure("'CurrentWebElement' property of DriverFacade is null");
        }
        return currentWebElement;
    }

    public void ReportOnFailure() {
        String imageName = "";
        if (!getStepResult()) {
            if (currentUACNumber != "") writeUACNumberToScenario(currentUACNumber, "FAILED");
            imageName = "TravelBox_" + System.currentTimeMillis();
            utilities.TakeScreenshot(getWebDriver(), imageName);
            currentScenario.write("Screenshot goes here" + imageName);
            System.out.println(date() + " - Error Screenshot: " + imageName);

            if (ReadConfigProperty("exitOnFailure").equals("true")) {
                EstablishResultTableConnection();
                if (Environment.equals("UAT")) {
                    WriteDatatoExcel(tcID, "UAT_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "UAT_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                if (Environment.equals("QA")) {
                    WriteDatatoExcel(tcID, "QA_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "QA_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                if (Environment.equals("PreProd")) {
                    WriteDatatoExcel(tcID, "Preprod_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "Preprod_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                EstablishDataTableConnection();
                currentScenario.write(" Action associated with'" + currentWebElementToText + "' on " + currentPageName + " had an error");
                Assert.assertTrue("Execution failed on " + currentScenario.getName(), false);

            }
        }
    }

    public void refreshScreen() {
        webDriver.navigate().refresh();

    }

    public void take_screenShot() {
        String imageName = "";
        imageName = "OutcomeTest_" + System.currentTimeMillis();
        utilities.TakeScreenshot(getWebDriver(), imageName);
        currentScenario.write("Screenshot goes here" + imageName);
    }

    public void ReportOnFailure(String businessError) {
        String imageName = "";
        if (!getStepResult()) {
            if (currentUACNumber != "") writeUACNumberToScenario(currentUACNumber, "FAILED");

            imageName = "TravelBox_" + System.currentTimeMillis();
            utilities.TakeScreenshot(getWebDriver(), imageName);
            currentScenario.write("Screenshot goes here" + imageName);

            if (ReadConfigProperty("exitOnFailure").equals("true")) {
                EstablishResultTableConnection();
                if (Environment.equals("UAT")) {
                    WriteDatatoExcel(tcID, "UAT_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "UAT_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                if (Environment.equals("QA")) {
                    WriteDatatoExcel(tcID, "QA_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "QA_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                if (Environment.equals("PreProd")) {
                    WriteDatatoExcel(tcID, "Preprod_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "Preprod_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                EstablishDataTableConnection();
                currentScenario.write(" Action associated with'" + currentWebElementToText + "' on " + currentPageName + " had an error");
                Assert.assertTrue(businessError, false);
            } else {
                EstablishResultTableConnection();
                if (Environment.equals("UAT")) {
                    WriteDatatoExcel(tcID, "UAT_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "UAT_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                if (Environment.equals("QA")) {
                    WriteDatatoExcel(tcID, "QA_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "QA_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                if (Environment.equals("PreProd")) {
                    WriteDatatoExcel(tcID, "Preprod_Status", "TestResults", "Fail");
                    WriteDatatoExcel(tcID, "Preprod_LastCheckDate", "TestResults", date());
                    System.out.println(date() + " - Status of " + tcID + " : Failed");
                }
                EstablishDataTableConnection();
                currentScenario.write(" Action associated with'" + currentWebElementToText + "' on " + currentPageName + " had an error");
                currentScenario.write(businessError);
                softAssertions.assertThat(true).isFalse();
                // softAssertions.assertAll();
            }
        }
    }


    public void ReportOnFailureWithSoftAssertion(String businessError) {
        String imageName = "";
        if (this.isTestCaseFailure()) {
            if (currentUACNumber != "") writeUACNumberToScenario(currentUACNumber, "FAILED");

            imageName = "TravelBox_" + System.currentTimeMillis();
            utilities.TakeScreenshot(getWebDriver(), imageName);
            currentScenario.write("Screenshot goes here" + imageName);
            EstablishResultTableConnection();
            if (Environment.equals("UAT")) {
                WriteDatatoExcel(tcID, "UAT_Status", "TestResults", "Fail");
                WriteDatatoExcel(tcID, "UAT_LastCheckDate", "TestResults", date());
                System.out.println(date() + " - Status of " + tcID + " : Failed");
            }
            if (Environment.equals("QA")) {
                WriteDatatoExcel(tcID, "QA_Status", "TestResults", "Fail");
                WriteDatatoExcel(tcID, "QA_LastCheckDate", "TestResults", date());
                System.out.println(date() + " - Status of " + tcID + " : Failed");
            }
            if (Environment.equals("PreProd")) {
                WriteDatatoExcel(tcID, "Preprod_Status", "TestResults", "Fail");
                WriteDatatoExcel(tcID, "Preprod_LastCheckDate", "TestResults", date());
                System.out.println(date() + " - Status of " + tcID + " : Failed");
            }
            EstablishDataTableConnection();
            currentScenario.write(" Action associated with'" + currentWebElementToText + "' on " + currentPageName + " had an error");
            currentScenario.write(businessError);
        }
    }

    public void writeUACNumberToScenario(String sUACNumber, String sStatus) {
        if (UACsCovered.equals("")) UACsCovered = sUACNumber + " - " + sStatus;
        else UACsCovered = UACsCovered + ";" + sUACNumber + " - " + sStatus;
    }

    public void writeInReport(String sText) {
        currentScenario.write(sText);
    }

    public void writeInReportRed(String stext) {
        currentScenario.write("<span style='Color: red'>" + stext + "</span>");
    }

    public void writeInReportGreen(String stext) {
        currentScenario.write("<span style='Color: green'>" + stext + "</span>");
    }
    public void writeInReportBlue(String stext) {
        currentScenario.write("<span style='Color: blue'>" + stext + "</span>");
    }

    public boolean isCurrentScenarioFailed() {
        return currentScenario.isFailed();
    }

    public String getFirstSelectDropdownValue(By byselector, String valueToBeSelected) {
        findElement(byselector);
        String text = null;
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                sel.selectByValue(valueToBeSelected);
                text = sel.getFirstSelectedOption().getText();
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return text;
    }

    public String getFirstSelectDrodownValue(By byselector) {
        findElement(byselector);
        String text = null;
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                text = sel.getFirstSelectedOption().getText();
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return text;
    }

    public DriverFacade selectWithoutTab(By byselector, String valueToBeSelected) {
        findElement(byselector);
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);

                sel.selectByValue(valueToBeSelected);
                // currentWebElement.sendKeys(Keys.TAB);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade select(By byselector, String valueToBeSelected) {
        findElement(byselector);
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);

                sel.selectByValue(valueToBeSelected);
                // currentWebElement.sendKeys(Keys.TAB);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public List<WebElement> listOfValueFromDropdown(By byselector) {

        findElement(byselector);
        List<WebElement> list = null;
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                list = sel.getOptions();
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return list;
    }

    public DriverFacade selectByVisibleText(By byselector, String valueToBeSelected) {
        findElement(byselector);
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                sel.selectByVisibleText(valueToBeSelected);
                // currentWebElement.sendKeys(Keys.TAB);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade newselectByVisibleText(By byselector, String valueToBeSelected) {
        findElement(byselector);
        Select sel = new Select(currentWebElement);
        sel.selectByVisibleText(valueToBeSelected);
        // webDriver.findElement(byselector).
        return this;
    }

    public DriverFacade clickAndSelectByVisibleText(By byselector, String valueToBeSelected) {
        findElement(byselector);
        if (getStepResult()) {
            try {
                currentWebElement.click();
                Thread.sleep(2000);

                Select sel = new Select(currentWebElement);
                sel.selectByVisibleText(valueToBeSelected);

                currentWebElement.sendKeys(Keys.TAB);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public DriverFacade clickSelectByVisibleText(By byselector, String valueToBeSelected) {
        findElement(byselector);
        if (getStepResult()) {
            try {
                currentWebElement.click();
                Thread.sleep(2000);

                Select sel = new Select(currentWebElement);
                sel.selectByVisibleText(valueToBeSelected);

                currentWebElement.sendKeys(Keys.TAB);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    public boolean checkDropdownType(By byselector) {
        findElement(byselector);
        boolean status = true;
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                status = sel.isMultiple();
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return status;
    }

    public DriverFacade selectWithoutTabbing(By byselector, String valueToBeSelected) {
        findElement(byselector);
        if (getStepResult()) {
            try {
                Select sel = new Select(currentWebElement);
                sel.selectByValue(valueToBeSelected);
            } catch (Exception e) {
                setStepResult(false);
            }
        }
        ReportOnFailure();
        return this;
    }

    @Override
    protected void SetUserAgent() {
    }

    public class WaitConditions {
        public DriverFacade WebElementToBeclickable(By bySelector) {
            currentWebElement = null;
            currentWebElementToText = "";
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(bySelector));
                HardWait(100);
                currentWebElement = webElement;
                setStepResult(true);
            } catch (TimeoutException e) {
                currentWebElement = null;
                setStepResult(false);
                // currentWebElementToText = bySelector.toString();
            }
            currentWebElementToText = bySelector.toString();
            ReportOnFailure();
            return DriverFacade.this;
        }

        public Boolean checkElementIsEnabled() {
            Boolean status = false;
            if (getStepResult()) {

                try {
                    status = getElement().isEnabled();
                    //System.out.println("inside func" + status);
                    setStepResult(true);
                } catch (Exception e) {
                    setStepResult(false);
                }
            }
            ReportOnFailure();
            return status;
        }

        public DriverFacade WebElementToBePresent(By bySelector) {
            currentWebElement = null;
            currentWebElementToText = "";
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySelector));
                currentWebElement = webElement;
                setStepResult(true);
            } catch (TimeoutException e) {
                currentWebElement = null;
                setStepResult(false);
            }
            currentWebElementToText = bySelector.toString();
            ReportOnFailure();
            return DriverFacade.this;
        }

        public DriverFacade WebElementToBePresentOptional(By bySelector) {
            currentWebElement = null;
            currentWebElementToText = "";
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bySelector));
                currentWebElement = webElement;
                setStepResult(true);
            } catch (TimeoutException e) {
                currentWebElement = null;
                setStepResult(false);
            }
            currentWebElementToText = bySelector.toString();

            return DriverFacade.this;
        }


        public DriverFacade TitleToBePresent(String title) {
            currentWebElement = null;
            currentWebElementToText = "";
            try {
                webDriverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
                setStepResult(true);
            } catch (TimeoutException e) {
                currentWebElement = null;
                setStepResult(false);
            }
            ReportOnFailure();
            return DriverFacade.this;
        }

        public DriverFacade WebElementToBePresentWithoutFailing(By bySelector) {
            currentWebElement = null;
            currentWebElementToText = "";
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(bySelector));
                currentWebElement = webElement;
                setStepResult(true);
            } catch (TimeoutException e) {
                currentWebElement = null;
                setStepResult(false);
            }
            return DriverFacade.this;
        }

        public DriverFacade WebElementToBePresentWithoutFailing(By bySelector, int milliSeconds) {
            currentWebElement = null;
            currentWebElementToText = "";
            try {
                WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(bySelector));
                currentWebElement = webElement;
                setStepResult(true);
            } catch (TimeoutException e) {
                currentWebElement = null;
                setStepResult(false);
            }
            return DriverFacade.this;
        }

    }

}
