package hda.outcome.rules.tests.Hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import hda.outcome.rules.tests.General.BrowserTypes;
import hda.outcome.rules.tests.cotrollers.DriverFacade;
import hda.outcome.rules.tests.testContext.TestExecutionContext;

import java.io.IOException;

public class CucumberHooks {
    DriverFacade driverFacade;
    TestExecutionContext testExecutionContext;

    public CucumberHooks(TestExecutionContext testExecutionContext) {
        this.driverFacade = testExecutionContext.getDriverFacade();
        this.testExecutionContext = testExecutionContext;
    }

    @Before
    public void before(Scenario scenario) {
        driverFacade.setUpWebDriverController(BrowserTypes.CHROME);
        testExecutionContext.setScenario(scenario);
        driverFacade.EstablishDataTableConnection();
        System.out.println(driverFacade.date() + " - Opening Broswer");

    }

    /*
     * @BeforeStep public void BeforeStep(){ driverFacade.setUACNumber(""); }
     */

    @After(order = 0)
    public void teardownWebDriverController(Scenario scenario) throws IOException {

        if (driverFacade.getWebDriver() != null) {
            driverFacade.getWebDriver().quit();
            System.out.println(driverFacade.date() + " - Quiting Browser");
            driverFacade.TerminateChromeDriver();
            //driverFacade.HardWait(2000);
            driverFacade.writeInReport(driverFacade.UACsCovered);
        }

        if (driverFacade.isTestCaseFailure()) {
            driverFacade.assertEqual(false);
        }

        //System.out.println("Generating test report");
        //Reporter.generateReport();

    }

/*
    public void AddSnapshotsToReport() throws FileNotFoundException {


        File log = new File("C:\\Project\\SurfAutomationFramework_Poc\\SurfAutomationFramework_Poc\\test-output\\HtmlReport\\ExtentHtml.html");

        File log1 = new File("C:\\Project\\SurfAutomationFramework_Poc\\SurfAutomationFramework_Poc\\test-output\\HtmlReport\\Report_TravelBox.html");
        FileReader fr = new FileReader(log);
        String s;
        String totalStr = "";

        try (BufferedReader br = new BufferedReader(fr)) {
            while ((s = br.readLine()) != null) {
                if (s.contains("Screenshot goes here")) {

                    s = s.replace("<div class=\"node-step\">Screenshot goes here", "");
                    s = s.replace("</div>", "");
                    s = s.trim();
                    s = "<div class=\"node-step\"><a href=\"C:/Project/SurfAutomationFramework_Poc/SurfAutomationFramework_Poc/test-output/screenshots/" + s + ".png\">click Here For Screenshot</a></div>";

                }
                totalStr += s;
            }
           // log.delete();
            FileWriter fw = new FileWriter(log1);
            fw.write(totalStr);
            fw.close();
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
