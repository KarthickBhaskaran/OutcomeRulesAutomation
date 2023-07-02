package hda.outcome.rules.tests.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import hda.outcome.rules.tests.PageObjects.*;
import hda.outcome.rules.tests.testContext.TestExecutionContext;

import java.text.ParseException;

public class CartPageSteps {
    private CartPage cartPage;


    public CartPageSteps(TestExecutionContext testExecutionContext) {
        this.cartPage = testExecutionContext.getPageObjectFacade().getCartPage(testExecutionContext);

    }

    @Given("I login to CRM")
    public void ilogintocrm() {
        cartPage.logintocrm();
    }

    @And("I perform Outcome Rule Test")
    public void i_perform_outcome_ruletest() {
        cartPage.performoutcometest();
    }
    @And ("I load Manual case creation page")
    public void i_load_manual_case(){
        cartPage.loadmanualcasepage();
    }
    @Then ("I Enter Caller details")
    public void i_enter_callderdetails(){
        cartPage.entercallerdetails();
    }
    @And ("I Enter Call Source state")
    public void i_enter_callsource(){
        cartPage.ientercallsource();
    }
    @Then ("I Save case and fill identity")
    public void i_savecase(){
        cartPage.savecase();
    }
    @And ("I Enter symptoms and triage")
    public void i_enter_triage(){
        cartPage.entertriage();
    }
    @And ("I complete the case and resolve")
    public void i_complete_case(){
        cartPage.completecase();
    }
}
