package EC_testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\Swaraj Sonawane\\eclipse-workspace\\Cucumber_Freamwork\\src\\test\\java\\FeatureFiles\\Products.feature",
glue= {"EC_StepDeifination"},
monochrome = true,
dryRun = false,
plugin = {"pretty","html:target/HTML/Result.html",   //preety folder karto for report 
		"junit:target/XML/Result.xml",
		"json:target/jsonReports/cucumber-report.json"
/*"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  --->compile---> test---> verify	*/})
public class Test_Runner {

}
