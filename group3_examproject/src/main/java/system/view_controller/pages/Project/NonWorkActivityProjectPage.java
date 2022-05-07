package system.view_controller.pages.Project;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import system.view_controller.widgets.Button;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.ActivityButtonAction;
import system.view_controller.actions.CreateNewProjectActivityAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.SubHeader;
import system.view_controller.constants.Constants;
import system.model.domain.Activity;
import system.model.domain.Project;

public class NonWorkActivityProjectPage {

    JFrame frame;
    Main main;
    Project project;
    String previousPage;
    Constants constants = new Constants();
    
    public NonWorkActivityProjectPage(JFrame frame, Main main, String previousPage, Project project) {
        this.frame = frame;
        this.main = main;
        this.project = project;
        this.previousPage = previousPage;
    }

    public JPanel draw() {

        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(550,750));
        BoxPanel.setBorder(new EmptyBorder(25,25,25,25));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(constants.backgroundColor);
        JLabel header = new JLabel("Non Work Activities");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBorder(new EmptyBorder(10,10,10,10));
        headerPanel.add(header);
        BoxPanel.add(headerPanel);

        JPanel InformationPanel = new JPanel(new GridLayout(0,2,5,2));
        InformationPanel.setBorder(new EmptyBorder(10,10,10,10));
        InformationPanel.setBackground(constants.boxColor);

        InformationPanel.add(new JLabel("Non Work Activities"));
        
        ArrayList<Activity> activities = project.getActivities();
        
        if (activities.size() < 1) {
            InformationPanel.add(new JLabel("No activities yet."));
            InformationPanel.add(new JLabel(""));
        } else {
            for (int i = 0; i < activities.size(); i++) {
                Activity activity = activities.get(i);
                JLabel activityLabel = new JLabel(activity.getName());
                if (activity.getName().equals("")) {
                    activityLabel = new JLabel("Unnamed");
                }
                activityLabel.setFont(new Font("Arial", Font.BOLD, 12));
                JPanel devePanel = new JPanel(new GridBagLayout());
                devePanel.add(activityLabel);
                devePanel.setBackground(constants.boxColor);
                InformationPanel.add(devePanel);
                InformationPanel.add(new JLabel(""));
            }
        }

        TextField addActivityTextField = new TextField("Activity Name", "Non work activity name...", constants.boxColor).getTextField();
        InformationPanel.add(addActivityTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction addActivityAction = new CreateNewProjectActivityAction("Create new activity", previousPage, addActivityTextField.textField, project, main);
        JPanel addActivityButtonPanel = new Button("Create new activity", constants.boxColor, "micro", addActivityAction).getButton();
        InformationPanel.add(addActivityButtonPanel);



        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel(""));



        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 600));

        BoxPanel.add(InformationScrollPanel);


        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", previousPage, main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);


        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);


        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}