package behaviorDrivenTesting.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".\\src\\test\\java\\behaviorDrivenTesting\\features\\",
        glue = "behaviorDrivenTesting\\stepDefinitions",
        plugin ={"pretty", "html:target\\cucumber-reports",
                "json:target\\cucumber-reports\\Cucumber.json"},
        monochrome = true,
        strict = true
)
public class WillhabenLoginTestRunner {}
