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
import system.view_controller.widgets.Button;

public class DeveloperOverviewPage {

    JFrame frame;
    Main main;
    int week;
    int year;
    Constants constants = new Constants();

    public DeveloperOverviewPage(JFrame frame, int week, int year, Main main) {
        this.frame = frame;
        this.main = main;
        this.week = week;
        this.year = year;
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

        JLabel subSubHeader = new JLabel("Year: " + year + ", Week: " + week);
        subSubHeader.setFont(new Font("Arial", Font.BOLD, 18));
        subSubHeader.setBorder(new EmptyBorder(0,0,25,0));
        BoxPanel.add(subSubHeader);
        subSubHeader.setAlignmentX(BoxPanel.CENTER_ALIGNMENT);
        subSubHeader.setAlignmentY(BoxPanel.CENTER_ALIGNMENT);

        JPanel InformationPanel = new JPanel();
        InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
        InformationPanel.setBackground(constants.boxColor);


        HashMap<Developer, HashMap<Project, ArrayList<Activity>>> currentDeveloperActivities;
        try {
            
            currentDeveloperActivities = main.app.getCurrentDeveloperActivities(week, year);
            
            for (Developer d : currentDeveloperActivities.keySet()) {

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

                if (currentDeveloperActivities.get(d).keySet().size() < 1) {
                    JLabel noActivitiesHeader = new JLabel("No Assigned Activities.");
                    noActivitiesHeader.setFont(new Font("Arial", Font.BOLD, 12));
                    noActivitiesHeader.setBorder(new EmptyBorder(10,0,0,0));
                    noActivitiesHeader.setBackground(constants.boxColor);
                    DeveloperPanel.add(noActivitiesHeader);
                    noActivitiesHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
                    noActivitiesHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);
                }

                for (Project project : currentDeveloperActivities.get(d).keySet()) {
                    JLabel projectHeader = new JLabel(project.getName() + " (" + project.getProjectNumber() + ")");
                    if (project.getName().equals("")) {
                        projectHeader = new JLabel("Unnamed" + " (" + project.getProjectNumber() + ")");
                    }
                    projectHeader.setFont(new Font("Arial", Font.BOLD, 20));
                    projectHeader.setBorder(new EmptyBorder(10,0,0,0));
                    projectHeader.setBackground(constants.boxColor);
                    DeveloperPanel.add(projectHeader);
                    projectHeader.setAlignmentX(DeveloperPanel.CENTER_ALIGNMENT);
                    projectHeader.setAlignmentY(DeveloperPanel.CENTER_ALIGNMENT);

                    for (Activity activity : currentDeveloperActivities.get(d).get(project)) {
                        JLabel activityHeader = new JLabel(activity.getName());
                        if (activity.getName().equals("")) {
                            activityHeader = new JLabel("Unnamed");
                        }
                        activityHeader.setFont(new Font("Arial", Font.BOLD, 15));
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

            
        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
        }


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(350, 700));
        BoxPanel.add(InformationScrollPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Developer Overview", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        BoxPanel.add(backToMainButtonPanel);


        JPanel container = new Container(BoxPanel).getContainer();
        return container;

    }
    
}