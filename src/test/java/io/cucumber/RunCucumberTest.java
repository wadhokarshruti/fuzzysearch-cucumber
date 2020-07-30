package io.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Shruti Wadhokar
 */
@RunWith(Cucumber.class)

//@CucumberOptions(plugin = {"usage"})//step execution duration
//@CucumberOptions(plugin = {"pretty"})//readable console output
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports"}
        )
public class RunCucumberTest {
}