package hda.outcome.rules.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:test-output/failedrerun.txt"},

       monochrome = true, tags = {"@Outcome"}, dryRun = false)
//monochrome = true, tags = {"@CRMCaseCreation"}, dryRun = false)

public class RunnerTest {

}
