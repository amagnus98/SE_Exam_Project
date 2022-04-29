package system.view_controller.pages.TimeRegistration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import system.model.domain.Activity;
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



        JLabel nonWorkActivitiesHeader = new JLabel("Non-Work Activities");
        nonWorkActivitiesHeader.setFont(new Font("Arial", Font.BOLD, 20));
        nonWorkActivitiesHeader.setBorder(new EmptyBorder(30,0,10,0));
        InformationPanel.add(nonWorkActivitiesHeader);
        nonWorkActivitiesHeader.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
        nonWorkActivitiesHeader.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);

        String nonWorkActivitiesProjectNumber = main.app.getnonWorkActivitiesProjectNumber();
        ArrayList<String> nonWorkActivities = main.app.getNonWorkActivities();
        
        for (int i = 0; i < nonWorkActivities.size(); i++) {
            String nonWorkActivity = nonWorkActivities.get(i);
            AbstractAction registerTimeOnActivityAction = new RegisterTimeOnActivityAction(nonWorkActivitiesProjectNumber, nonWorkActivity, main);
            JPanel nonWorkActivityButton = new Button(nonWorkActivity, constants.boxColor, "micro", registerTimeOnActivityAction).getButton();
            InformationPanel.add(nonWorkActivityButton);
        }


        ArrayList<Project> projects = main.app.getProjects();

        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);

            String projectName = "Unnamed";
            if (!projects.get(i).getName().equals("")) {
                projectName = project.getName();
            }

            JLabel projectHeader = new JLabel(projectName + " (" + project.getProjectNumber() + ")");
            projectHeader.setFont(new Font("Arial", Font.BOLD, 20));
            projectHeader.setBorder(new EmptyBorder(30,0,10,0));
            InformationPanel.add(projectHeader);
            projectHeader.setAlignmentX(InformationPanel.CENTER_ALIGNMENT);
            projectHeader.setAlignmentY(InformationPanel.CENTER_ALIGNMENT);
            
            ArrayList<Activity> activitites = project.getActivities();
            for (int j = 0; j < activitites.size(); j++) {
                Activity activity = activitites.get(j);

                AbstractAction registerTimeOnActivityAction = new RegisterTimeOnActivityAction(activity.getParentProjectNumber(), activity.getName(), main);
                JPanel projectActivityButton = new Button(activity.getName(), constants.boxColor, "micro", registerTimeOnActivityAction).getButton();
                InformationPanel.add(projectActivityButton);
            }
        }

        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(350, 700));
        BoxPanel.add(InformationScrollPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        BoxPanel.add(backToMainButtonPanel);


        JPanel container = new Container(BoxPanel).getContainer();
        return container;

    }
    
}