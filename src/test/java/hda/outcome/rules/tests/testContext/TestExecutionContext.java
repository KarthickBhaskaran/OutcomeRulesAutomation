package hda.outcome.rules.tests.testContext;


import cucumber.api.Scenario;
import hda.outcome.rules.tests.cotrollers.DriverFacade;
import hda.outcome.rules.tests.cotrollers.PageObjectFacade;

public class TestExecutionContext {

    private DriverFacade driverFacade;
    private PageObjectFacade pageObjectFacade;
    private Scenario currentScenario;

    public TestExecutionContext() {
        driverFacade = new DriverFacade();
        pageObjectFacade = new PageObjectFacade();
    }

    public DriverFacade getDriverFacade() {
        return driverFacade;
    }

    public PageObjectFacade getPageObjectFacade() {
        return pageObjectFacade;
    }

    public Scenario getScenario() {
        return currentScenario;
    }

    public void setScenario(Scenario scenario) {
        currentScenario = scenario;
    }
}
