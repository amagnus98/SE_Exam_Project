package system.view_controller.pages.ManageProjects;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.ProjectButton;
import system.view_controller.widgets.SubHeader;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ProjectButtonAction;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.constants.Constants;


import system.model.domain.Project;
import java.util.*;


// View Projects Page // Responsible - Kasper Petersen (s203294)
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
        BoxPanel.setPreferredSize(new Dimension(350, 700));

        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(constants.backgroundColor);
        JLabel subHeader = new JLabel("Project Overview");
        subHeader.setFont(new Font("Arial", Font.BOLD, 25));
        subHeader.setBorder(new EmptyBorder(75,0,25,0));
        subHeaderPanel.add(subHeader);
        BoxPanel.add(subHeaderPanel);

        ArrayList<Project> projects = main.app.getProjects();

        if (projects.size() < 1) {
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new GridBagLayout());
            JLabel label = new JLabel("<html>There are no projects to view yet. To create a new project, click on the \'Create New Project\' button below.</html>");
            label.setPreferredSize(new Dimension(250, 200));
            labelPanel.setBackground(constants.boxColor);
            labelPanel.add(label);
            labelPanel.setPreferredSize(new Dimension(350, 700));
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
                projectButtonButtonPanel.setAlignmentX(ProjectViewPanel.CENTER_ALIGNMENT);
                projectButtonButtonPanel.setAlignmentY(ProjectViewPanel.CENTER_ALIGNMENT);
            }

            JScrollPane scrollPanel = new JScrollPane(ProjectViewPanel);
            scrollPanel.setBackground(constants.boxColor);
            scrollPanel.setPreferredSize(new Dimension(350, 700));
            BoxPanel.add(scrollPanel);
            
        }

        JPanel createNewProjectPanel = new JPanel();
        createNewProjectPanel.setBackground(constants.secondBoxColor);
        createNewProjectPanel.setBorder(new EmptyBorder(10,0,10,0));
        AbstractAction createNewProjectAction = new MainMenuAction("Create new project", "Create new project", main);
        JPanel createNewProjectButtonPanel = new Button("Create new project", constants.secondBoxColor, "small", createNewProjectAction).getButton();
        createNewProjectPanel.add(createNewProjectButtonPanel);
        BoxPanel.add(createNewProjectPanel);

        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        backToManageProjectsButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        BoxPanel.add(backToManageProjectsButtonPanel);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}
