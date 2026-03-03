import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features="src/test/resources/features"
    ,glue={"stepDefinitions"}
    ,monochrome=true
    ,plugin={
        "pretty"
        ,"html:target/HtmlReports"
        ,"json:target/JSONReports/report.json"
        ,"junit:target/JUnitReports/report.xml"
    }
    ,tags="@SmokeTest1"
)
public class TestRunner {
}
