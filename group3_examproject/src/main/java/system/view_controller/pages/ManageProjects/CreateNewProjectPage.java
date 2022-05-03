package system.view_controller.pages.ManageProjects;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
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

        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(constants.backgroundColor);
        JLabel subHeader = new JLabel("Create New Project");
        subHeader.setFont(new Font("Arial", Font.BOLD, 25));
        subHeader.setBorder(new EmptyBorder(75,0,25,0));
        subHeaderPanel.add(subHeader);
        BoxPanel.add(subHeaderPanel);

        JPanel createPanel = new JPanel();
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.Y_AXIS));
        createPanel.setBackground(constants.boxColor);
        createPanel.setBorder(new EmptyBorder(30,10,30,10));


        new SubHeader("Project Name (optional):", constants.boxColor, createPanel);
        TextField textField = new TextField("Project Name", "", constants.boxColor).getTextField();
        createPanel.add(textField.textFieldPanel);

        AbstractAction createNewProjectAction = new CreateNewProjectAction("Create", "Create new project", textField.textField, main);
        JPanel createNewProjectButtonPanel = new Button("Create", constants.boxColor, "small", createNewProjectAction).getButton();
        createPanel.add(createNewProjectButtonPanel);
        BoxPanel.add(createPanel);

        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", "Project View", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
    
}
