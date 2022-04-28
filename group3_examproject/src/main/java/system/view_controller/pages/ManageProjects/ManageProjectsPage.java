package system.view_controller.pages.ManageProjects;
import javax.swing.*;
import java.awt.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Header;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.constants.Constants;

public class ManageProjectsPage {

    JFrame frame;
    Main main;
    Constants constants = new Constants();
    
    public ManageProjectsPage(JFrame frame, Main main) {
        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {
        JPanel BoxPanel = new BoxPanel().getPanel();

        new Header("Manage Projects", BoxPanel);

        AbstractAction projectViewAction = new MainMenuAction("Project Overview", "Project View", main);
        JPanel projectViewButtonPanel = new Button("Project Overview", constants.backgroundColor, "big", projectViewAction).getButton();
        BoxPanel.add(projectViewButtonPanel);

        AbstractAction createNewProjectAction = new MainMenuAction("Create new Project", "Create new Project", main);
        JPanel createNewProjectButtonPanel = new Button("Create new Project", constants.backgroundColor, "big", createNewProjectAction).getButton();
        BoxPanel.add(createNewProjectButtonPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        BoxPanel.add(backToMainButtonPanel);
        
        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
}
