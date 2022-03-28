package dtu.calculator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	@When("I do nothing")
	public void i_do_nothing() {
	    
	}

	@Then("everything is okay")
	public void everything_is_okay() {
	    assertTrue(true);
	}

}

