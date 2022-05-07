package system.view_controller.pages.DeveloperOverview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import system.model.domain.Activity;
import system.model.domain.Developer;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.constants.Constants;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.SubHeader;
import system.view_controller.widgets.Button;

public class DeveloperOverviewPage {

    JFrame frame;
    Main main;
    int startWeek;
    int startYear;
    int endWeek;
    int endYear;
    Constants constants = new Constants();
    HashMap<Developer, HashMap<Project, ArrayList<Activity>>> developerActivitiesInPeriod;

    public DeveloperOverviewPage(JFrame frame, int startWeek, int startYear, int endWeek, int endYear,HashMap<Developer, HashMap<Project, ArrayList<Activity>>> developerActivitiesInPeriod, Main main) {
        this.frame = frame;
        this.main = main;
        this.startWeek = startWeek;
        this.startYear = startYear;
        this.endWeek = endWeek;
        this.endYear = endYear;
        this.developerActivitiesInPeriod = developerActivitiesInPeriod;
    }

    public JPanel draw() {

        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(350,700));
        
        JLabel subHeader = new JLabel("Developer Overview");
        subHeader.setFont(new Font("Arial", Font.BOLD, 25));
        subHeader.setBorder(new EmptyBorder(50,0,10,0));
        BoxPanel.add(subHeader);
        subHeader.setAlignmentX(BoxPanel.CENTER_ALIGNMENT);
        subHeader.setAlignmentY(BoxPanel.CENTER_ALIGNMENT);

        JLabel subSubHeaderStart = new JLabel("Start year: " + startYear + ", Start week: " + startWeek);
        subSubHeaderStart.setFont(new Font("Arial", Font.BOLD, 18));
        subSubHeaderStart.setBorder(new EmptyBorder(0,0,25,0));
        BoxPanel.add(subSubHeaderStart);
        subSubHeaderStart.setAlignmentX(BoxPanel.CENTER_ALIGNMENT);
        subSubHeaderStart.setAlignmentY(BoxPanel.CENTER_ALIGNMENT);

        JLabel subSubHeaderEnd = new JLabel("End year: " + endYear + ", End week: " + endWeek);
        subSubHeaderEnd.setFont(new Font("Arial", Font.BOLD, 18));
        subSubHeaderEnd.setBorder(new EmptyBorder(0,0,25,0));
        BoxPanel.add(subSubHeaderEnd);
        subSubHeaderEnd.setAlignmentX(BoxPanel.CENTER_ALIGNMENT);
        subSubHeaderEnd.setAlignmentY(BoxPanel.CENTER_ALIGNMENT);

        JPanel InformationPanel = new JPanel();
        InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
        InformationPanel.setBackground(constants.boxColor);


        for (Developer d : developerActivitiesInPeriod.keySet()) {

            JPanel DeveloperPanelBorder = new JPanel();
            DeveloperPanelBorder.setLayout(new BoxLayout(DeveloperPanelBorder, BoxLayout.Y_AXIS));
            DeveloperPanelBorder.setBackground(constants.boxColor);
            DeveloperPanelBorder.setBorder(new EmptyBorder(20,10,10,10));

            JPanel DeveloperPanel = new JPanel();
            DeveloperPanel.setLayout(new BoxLayout(DeveloperPanel, BoxLayout.Y_AXIS));
            DeveloperPanel.setBackground(constants.boxColor);
            DeveloperPanel.setBorder(new EmptyBorder(10,10,10,10));

            JLabel developerHeader = new JLabel(d.getInitials());
            developerHeader.setFont(new Font("Arial", Font.BOLD, 25));
            developerHeader.setBorder(new EmptyBorder(10,0,0,0));
            developerHeader.setBackground(constants.boxColor);
            DeveloperPanel.add(developerHeader);
            developerHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
            developerHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);

            if (developerActivitiesInPeriod.get(d).keySet().size() < 1) {
                JLabel noActivitiesHeader = new JLabel("No Assigned Activities.");
                noActivitiesHeader.setFont(new Font("Arial", Font.BOLD, 12));
                noActivitiesHeader.setBorder(new EmptyBorder(10,0,0,0));
                noActivitiesHeader.setBackground(constants.boxColor);
                DeveloperPanel.add(noActivitiesHeader);
                noActivitiesHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
                noActivitiesHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);
            }

            for (Project project : developerActivitiesInPeriod.get(d).keySet()) {
                JLabel projectHeader = new JLabel("Project: " + project.getName() + " (" + project.getProjectNumber() + ")");
                if (project.getName().equals("")) {
                    projectHeader = new JLabel("Unnamed" + " (" + project.getProjectNumber() + ")");
                } else if (project.isNonWorkActivityProject()){
                    projectHeader = new JLabel(project.getName());
                }
                projectHeader.setFont(new Font("Arial", Font.BOLD, 17));
                projectHeader.setBorder(new EmptyBorder(10,0,0,0));
                projectHeader.setBackground(constants.boxColor);
                DeveloperPanel.add(projectHeader);
                projectHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
                projectHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);

                JLabel activitiesHeader = new JLabel("Activities:");
                activitiesHeader.setFont(new Font("Arial", Font.BOLD, 12));
                activitiesHeader.setBorder(new EmptyBorder(10,0,0,0));
                activitiesHeader.setBackground(constants.boxColor);
                DeveloperPanel.add(activitiesHeader);
                activitiesHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
                activitiesHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);

                for (Activity activity : developerActivitiesInPeriod.get(d).get(project)) {
                    JLabel activityHeader = new JLabel(activity.getName());
                    if (activity.getName().equals("")) {
                        activityHeader = new JLabel("Unnamed");
                    }
                    activityHeader.setFont(new Font("Arial", Font.BOLD, 12));
                    activityHeader.setBorder(new EmptyBorder(10,0,0,0));
                    activityHeader.setBackground(constants.boxColor);
                    DeveloperPanel.add(activityHeader);
                    activityHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
                    activityHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);
                }
            }

            DeveloperPanelBorder.add(DeveloperPanel);
            InformationPanel.add(DeveloperPanelBorder);
        }

        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(350, 700));
        BoxPanel.add(InformationScrollPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Developer Overview", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        backToMainButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        BoxPanel.add(backToMainButtonPanel);


        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);


        JPanel container = new Container(BoxPanel).getContainer();
        return container;

    }
    
}