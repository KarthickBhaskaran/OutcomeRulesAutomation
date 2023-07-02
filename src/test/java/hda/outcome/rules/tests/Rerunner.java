package hda.outcome.rules.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","junit:target/surefire-reports/result.xml","junit:target/cucumber/result.xml", "json:result.xml", "json:zephyrscale_result.json", "json:target/cucumber/zephyrscale_result.json"},
@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, features = "@test-output/failedrerun.txt", monochrome = true, tags = {"@Outcome"}, dryRun = false)
//@Regression,@TourRadar
// features = "@test-output/failedrerun.txt"
//"@Smoke_01,@Smoke_02,@Smoke_03,@Smoke_04,@Smoke_05,@Smoke_06,@Smoke_07,@Smoke_08,@Smoke_09,@Smoke_10,@Smoke_11,@Smoke_12,@Smoke_13,@Smoke_14,@Smoke_15,@Smoke_16,@Smoke_17,@Smoke_18,@Smoke_19,@Smoke_20,@Smoke_21,@Smoke_22,@Smoke_23,@Smoke_24,@Smoke_25,@Smoke_26,@Smoke_27,@Smoke_28,@Smoke_29,@Smoke_30,@Smoke_31,@Smoke_32,@Smoke_33,@Smoke_42,@Smoke_43,@Smoke_44,@Smoke_45,@Smoke_46,@Smoke_47,@Smoke_48,@Smoke_49,@Smoke_50,@Smoke_51,@Smoke_52"

public class Rerunner {

}
