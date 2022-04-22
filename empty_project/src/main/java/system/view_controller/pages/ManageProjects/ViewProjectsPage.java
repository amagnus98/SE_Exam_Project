package system.view_controller.pages.ManageProjects;
import javax.swing.*;

import java.awt.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.ProjectButton;
import system.view_controller.widgets.SubHeader;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ProjectButtonAction;
import system.view_controller.widgets.Header;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.constants.Constants;


import system.model.domain.Project;
import java.util.*;

public class ViewProjectsPage {

    JFrame frame;
    Main main;
    Constants constants = new Constants();
    
    public ViewProjectsPage(JFrame frame, Main main) {
        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {
        JPanel BoxPanel = new BoxPanel().getPanel();

        new Header("Project Overview", BoxPanel);

        ArrayList<Project> projects = main.app.getProjects();

        if (projects.size() < 1) {
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new GridBagLayout());
            JLabel label = new JLabel("<html>There are no projects to view yet. To create a new project, click \'Create New Project\' on the Manage Projects page.</html>");
            label.setPreferredSize(new Dimension(250, 200));
            labelPanel.setBackground(constants.boxColor);
            labelPanel.add(label);
            labelPanel.setMaximumSize(new Dimension(300, 200));
            BoxPanel.add(labelPanel);

        } else {

            JPanel ProjectViewPanel = new JPanel();
            ProjectViewPanel.setLayout(new BoxLayout(ProjectViewPanel, BoxLayout.Y_AXIS));
            ProjectViewPanel.setBackground(constants.boxColor);

            for (int i = projects.size()-1; i >= 0; i--) {
                Project project = projects.get(i);
                AbstractAction projectButtonAction = new ProjectButtonAction(project.getProjectNumber(), project.getName(), project, "Project View", main);
                JPanel projectButtonButtonPanel = new ProjectButton(project.getProjectNumber(), project.getName(), constants.boxColor, projectButtonAction).getButton();
                ProjectViewPanel.add(projectButtonButtonPanel);
            }

            JScrollPane scrollPanel = new JScrollPane(ProjectViewPanel);
            scrollPanel.setBackground(constants.boxColor);
            scrollPanel.setPreferredSize(new Dimension(250, 500));
            BoxPanel.add(scrollPanel);
        }

        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", "Manage Projects", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}
