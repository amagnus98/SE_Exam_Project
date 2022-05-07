package system.view_controller.pages.TimeRegistration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import system.model.domain.Activity;
import system.model.domain.Developer;
import system.model.domain.Project;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.RegisterTimeOnActivityAction;
import system.view_controller.constants.Constants;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.Header;
import system.view_controller.widgets.SubHeader;
import system.view_controller.widgets.Button;

public class ChooseActivityPage {

    JFrame frame;
    Main main;
    Constants constants = new Constants();

    public ChooseActivityPage(JFrame frame, Main main) {
        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {

        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(350,700));
        
        JLabel subHeader = new JLabel("Choose Activity");
        subHeader.setFont(new Font("Arial", Font.BOLD, 25));
        subHeader.setBorder(new EmptyBorder(75,0,25,0));
        BoxPanel.add(subHeader);
        subHeader.setAlignmentX(BoxPanel.CENTER_ALIGNMENT);
        subHeader.setAlignmentY(BoxPanel.CENTER_ALIGNMENT);

        JPanel InformationPanel = new JPanel();
        InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
        InformationPanel.setBackground(constants.boxColor);


        ArrayList<Project> projects = main.app.getProjects();
        ArrayList<Activity> requestedActivities = new ArrayList<>();

        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            
            if (project.isNonWorkActivityProject()){
                String projectName = "Unnamed";
                if (!project.getName().equals("")) {
                    projectName = project.getName();
                }
                JLabel projectHeader = new JLabel(projectName + " (" + project.getProjectNumber() + ")");
                projectHeader = new JLabel(projectName);
            
                projectHeader.setFont(new Font("Arial", Font.BOLD, 20));
                projectHeader.setBorder(new EmptyBorder(30,0,10,0));
                InformationPanel.add(projectHeader);
                projectHeader.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
                projectHeader.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);
                
                ArrayList<Activity> allActivitites = project.getActivities();

                for (Activity activity : allActivitites) {
                    AbstractAction registerTimeOnActivityAction = new RegisterTimeOnActivityAction(activity.getParentProject().getProjectNumber(), activity.getName(), main);
                    JPanel projectActivityButton = new Button(activity.getName(), constants.boxColor, "micro", registerTimeOnActivityAction).getButton();
                    InformationPanel.add(projectActivityButton);
                }
            } else if (project.getDevelopers().contains(main.app.getCurrentUser())){
                String projectName = "Unnamed";
                if (!project.getName().equals("")) {
                    projectName = project.getName();
                }
                JLabel projectHeader = new JLabel(projectName + " (" + project.getProjectNumber() + ")");
                
                projectHeader.setFont(new Font("Arial", Font.BOLD, 20));
                projectHeader.setBorder(new EmptyBorder(30,0,10,0));
                InformationPanel.add(projectHeader);
                projectHeader.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
                projectHeader.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);
                
                ArrayList<Activity> allActivitites = project.getActivities();
                ArrayList<Activity> activities = new ArrayList<>();

                // only include activities that the current user is assigned to
                for (Activity activity : allActivitites) {
                    if (activity.getDevelopers().contains(main.app.getCurrentUser())) {
                        activities.add(activity);
                    }
                }

                if (activities.size() < 1) {
                    JLabel noActivitiesText = new JLabel("No assigned activities.");
                    noActivitiesText.setFont(new Font("Arial", Font.PLAIN, 12));
                    InformationPanel.add(noActivitiesText);
                    noActivitiesText.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
                    noActivitiesText.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);
                } else {
                    for (Activity activity : activities) {
                        if (activity.isDeveloperAssignedByProjectLeader(main.app.getCurrentUser())){
                            AbstractAction registerTimeOnActivityAction = new RegisterTimeOnActivityAction(activity.getParentProject().getProjectNumber(), activity.getName(), main);
                            JPanel projectActivityButton = new Button(activity.getName(), constants.boxColor, "micro", registerTimeOnActivityAction).getButton();
                            InformationPanel.add(projectActivityButton);
                        } else {
                            requestedActivities.add(activity);
                        }
                    }
                }
            } else {
                ArrayList<Activity> activities = project.getActivities();
                for (Activity activity : activities) {
                    if (activity.isDeveloperRequested(main.app.getCurrentUser())) {
                        requestedActivities.add(activity);
                    }
                }
            }
        }
        JLabel requestedHeader = new JLabel("Requested Activities");
            requestedHeader.setFont(new Font("Arial", Font.BOLD, 20));
            requestedHeader.setBorder(new EmptyBorder(30,0,10,0));
            InformationPanel.add(requestedHeader);
            requestedHeader.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
            requestedHeader.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);

            if (requestedActivities.size() < 1) {
                JLabel noActivitiesText = new JLabel("No requsted activities.");
                noActivitiesText.setFont(new Font("Arial", Font.PLAIN, 12));
                InformationPanel.add(noActivitiesText);
                noActivitiesText.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
                noActivitiesText.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);
            } else {
                for (Activity activity : requestedActivities) {
                    AbstractAction registerTimeOnActivityAction = new RegisterTimeOnActivityAction(activity.getParentProject().getProjectNumber(), activity.getName(), main);
                    JPanel projectActivityButton = new Button(activity.getName(), constants.boxColor, "micro", registerTimeOnActivityAction).getButton();
                    InformationPanel.add(projectActivityButton);
                }
            }

        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(350, 700));
        BoxPanel.add(InformationScrollPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        backToMainButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        BoxPanel.add(backToMainButtonPanel);


        JPanel container = new Container(BoxPanel).getContainer();
        return container;

    }
    
}