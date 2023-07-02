package hda.outcome.rules.tests.cotrollers;

import hda.outcome.rules.tests.testContext.TestExecutionContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;


public abstract class BasePage {

    public TestExecutionContext TEC;
    public DriverFacade driverFacade;


    //All constant xpaths to be used across pages
    public static final String CalendarXpath = "//span[contains(text(),'MonthName')]/ancestor::div[@class='o-calendar']/div[2]/div/span[text()='DateValue'][1]";
    public static final String logout_icon = "//div[contains(@class,'c-global-header__nav-item--user')]";
    public static final String logout_icon_dropdown = "//div[@class='m-form-field__main']/ul/li[4]/span";
    public static final String logout_icon_alert = "//div[@id='logoutMessage']//button[contains(text(),'Logout')]";
    public static final String logout_icon_cancel = "//div[@id='logoutMessage']//button[contains(text(),'Cancel')]";
    public static final String logout_icon_alert_verify = "//div[@class='o-modal__body surf-msg-box__content']/div[3]/button[contains(text(),'Logout')]";
    public static final String login_text_varify = "//div[@class='l-wrapper login-page__login']/section/div/h1";

    By breadcrumb_item(int item_no) {
        return By.xpath("//surf-breadcrumb/ul/li[" + item_no + "]");
    }

    public BasePage(TestExecutionContext testExecutionContext) {
        this.driverFacade = testExecutionContext.getDriverFacade();
        this.TEC = testExecutionContext;
        driverFacade.setCurrentScenario(TEC.getScenario());
        driverFacade.setCurrentPage(this);
    }

    public abstract void VerifyPage(String businessError);

    public void i_verify_BreadCrumb(String page_name) {

        String class_name = "";
        switch (page_name) {

            case "Flight Result Page":
                class_name = driverFacade.waitFor().WebElementToBePresent(breadcrumb_item(2)).getAttribute("class");
                break;
        }
        System.out.println(class_name);
        driverFacade.assertEqual(true, class_name.contains("is-active"));
    }


    public void open_URL_In_Another_Tab() {

        String url = driverFacade.getWebPageURL();

        ((JavascriptExecutor) (driverFacade.getWebDriver())).executeScript("window.open('" + url + "','_blank');");
        ArrayList<String> tabs = new ArrayList<String>(driverFacade.getWebDriver().getWindowHandles());
        driverFacade.getWebDriver().switchTo().window(tabs.get(1));
    }

    public void type_Shift_C() {
        driverFacade.moveToExpectedElementAndClickShiftC();
    }

}
