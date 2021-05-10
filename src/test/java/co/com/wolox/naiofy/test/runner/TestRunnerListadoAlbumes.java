package co.com.wolox.naiofy.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/listadoAlbumes.feature"}
        , glue = {"co/com/wolox/naiofy/test/stepdefinitions"}
        , plugin = {"pretty", "html:target/cucumberReport.html", "json:target/TestRunnerListadoAlbumes.json"
})
public class TestRunnerListadoAlbumes {
}
