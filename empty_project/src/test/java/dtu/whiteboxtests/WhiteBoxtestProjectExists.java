package dtu.whiteboxtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import system.model.domain.App;
import system.model.domain.OperationNotAllowedException;



public class WhiteBoxtestProjectExists {
  
    App app = new App();

  
    @BeforeEach
    public void setUp() throws OperationNotAllowedException, Exception {
        System.out.println("Executing setup");
        app.logIn("kape");
    }

    // No project exists, returns false
    @Test
    public void testProjectExistsA(){
      // Asserts that no projects exists initially
      assertEquals(app.getProjectCount(),0);

      // Then the method should return False
      assertFalse(app.projectExists("22001"));
    }  
    
    // A single project exists and has same project number as the input. Returns True
    @Test
    public void testProjectExistsB(){
      app.addProject();

      assertTrue(app.projectExists("22001"));
    }

    // A single project exists and does not have same project number as the input. Returns false
    @Test
    public void testProjectExistsC(){
      app.addProject();

      assertFalse(app.projectExists("22002"));
    }
    
    // 2 projects exists in and the second project has the same project number as input project number. 
    // returns true
    @Test
    public void testProjectExistsD(){
      app.addProject();
      app.addProject();

      assertTrue(app.projectExists("22002"));
    }
  }