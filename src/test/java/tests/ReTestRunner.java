package tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"@target\\realtestfailed.txt"},
				 monochrome=true,
				 glue={"glue"},
				 plugin={"pretty","html:target\\retestresults","rerun:target\\retestfailed.txt"}
				)
public class ReTestRunner
{
}
