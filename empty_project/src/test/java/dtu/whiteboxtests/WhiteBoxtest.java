package dtu.whiteboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.model.domain.Activity;
import system.model.domain.App;
import system.model.domain.Developer;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;


public class WhiteBoxtest {
  
    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("kape");
    }


    @Test
    public void testProjectExistsA(){
      // Asserts that no projects exists initially
      assertEquals(app.getProjectCount(),0);

      // Then the method should return False
      assertFalse(app.projectExists("22001"));
    }  
    
    @Test
    public void testProjectExistsB(){
      app.addProject();

      assertTrue(app.projectExists("22001"));
    }

    @Test
    public void testProjectExistsC(){
      app.addProject();
      app.addProject();

      assertFalse(app.projectExists("22003"));
    }
  }