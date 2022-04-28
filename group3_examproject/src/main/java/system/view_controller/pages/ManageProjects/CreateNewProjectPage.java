package system.view_controller.pages.ManageProjects;
import javax.swing.*;
import system.view_controller.widgets.Button;
import system.view_controller.actions.CreateNewProjectAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.widgets.Header;
import system.view_controller.widgets.SubHeader;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.TextField;
import system.view_controller.constants.Constants;

public class CreateNewProjectPage {

    JFrame frame;
    Main main;
    Constants constants = new Constants();
    
    public CreateNewProjectPage(JFrame frame, Main main) {

        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {
        JPanel BoxPanel = new BoxPanel().getPanel();

        new Header("Create New Project", BoxPanel);

        new SubHeader("Project Name (optional):", constants.backgroundColor, BoxPanel);
        TextField textField = new TextField("Project Name", "", constants.backgroundColor).getTextField();
        BoxPanel.add(textField.textFieldPanel);

        AbstractAction createNewProjectAction = new CreateNewProjectAction("Create", "Create new Project", textField.textField, main);
        JPanel createNewProjectButtonPanel = new Button("Create", constants.backgroundColor, "small", createNewProjectAction).getButton();
        BoxPanel.add(createNewProjectButtonPanel);

        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", "Manage Projects", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
    
}
