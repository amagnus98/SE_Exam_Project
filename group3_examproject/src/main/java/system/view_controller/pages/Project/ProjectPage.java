package system.view_controller.pages.Project;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import system.view_controller.widgets.Button;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.ActivityButtonAction;
import system.view_controller.actions.AsignProjectDeveloperAction;
import system.view_controller.actions.CreateNewProjectActivityAction;
import system.view_controller.actions.GetProjectReportAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.SubmitProjectChangersAction;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.constants.Constants;
import system.model.domain.Activity;
import system.model.domain.Developer;
import system.model.domain.Project;

public class ProjectPage {

    JFrame frame;
    Main main;
    Project project;
    String previousPage;
    Constants constants = new Constants();
    
    public ProjectPage(JFrame frame, Main main, String previousPage, Project project) {
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
        JLabel header = new JLabel("Project Details");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBorder(new EmptyBorder(10,10,10,10));
        headerPanel.add(header);
        BoxPanel.add(headerPanel);

        JPanel InformationPanel = new JPanel(new GridLayout(0,2,5,2));
        InformationPanel.setBorder(new EmptyBorder(10,10,10,10));
        InformationPanel.setBackground(constants.boxColor);


        InformationPanel.add(new JLabel("Project Number"));
        InformationPanel.add(new JLabel(project.getProjectNumber()));

        InformationPanel.add(new JLabel("Project Name"));
        TextField projectNameTextField = new TextField("Project Name", project.getName(), constants.boxColor).getTextField();
        InformationPanel.add(projectNameTextField.textField);

        InformationPanel.add(new JLabel("Project Leader"));
        TextField projectLeaderTextField = new TextField("Project Leader", "", constants.boxColor).getTextField();
        if (project.hasProjectLeader()) {
            projectLeaderTextField = new TextField("Project Leader", project.getProjectLeader().getInitials(), constants.boxColor).getTextField();
            InformationPanel.add(projectLeaderTextField.textField);
        } else {
            InformationPanel.add(projectLeaderTextField.textField);
        }

        InformationPanel.add(new JLabel("Project Start Year"));
        TextField startYearTextField = new TextField("Project startYear", String.valueOf(project.getStartYear()), constants.boxColor).getTextField();
        InformationPanel.add(startYearTextField.textField);
        InformationPanel.add(new JLabel("Project Start Week"));
        TextField startWeekTextField = new TextField("Project startWeek", String.valueOf(project.getStartWeek()), constants.boxColor).getTextField();
        InformationPanel.add(startWeekTextField.textField);

        InformationPanel.add(new JLabel("Project End Year"));
        TextField endYearTextField = new TextField("Project endYear", String.valueOf(project.getEndYear()), constants.boxColor).getTextField();
        InformationPanel.add(endYearTextField.textField);
        InformationPanel.add(new JLabel("Project End Week"));
        TextField endWeekTextField = new TextField("Project endWeek", String.valueOf(project.getEndWeek()), constants.boxColor).getTextField();
        InformationPanel.add(endWeekTextField.textField);


        InformationPanel.add(new JLabel("Estimated Work Hours"));
        TextField estimatedWorkHoursTextField = new TextField("Estimated Work Hours", String.valueOf(project.getEstimatedWorkHours()), constants.boxColor).getTextField();
        InformationPanel.add(estimatedWorkHoursTextField.textField);



        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel(""));




        InformationPanel.add(new JLabel("Project Activities"));
        
        ArrayList<Activity> activities1 = project.getActivities();
        
        if (activities1.size() < 1) {
            InformationPanel.add(new JLabel("No activities yet."));
            InformationPanel.add(new JLabel(""));
        } else {
            for (int i = 0; i < activities1.size(); i++) {
                Activity activity = activities1.get(i);
                AbstractAction viewActivityAction = new ActivityButtonAction(activity.getName(), activity, project, main);
                JPanel viewActivityButtonPanel = new Button(activity.getName(), constants.boxColor, "micro", viewActivityAction).getButton();
                InformationPanel.add(viewActivityButtonPanel);
                InformationPanel.add(new JLabel(""));
            }
        }

        TextField addActivityTextField = new TextField("Activity Name", "activity name...", constants.boxColor).getTextField();
        InformationPanel.add(addActivityTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction addActivityAction = new CreateNewProjectActivityAction("create new activity", previousPage, addActivityTextField.textField, project, main);
        JPanel addActivityButtonPanel = new Button("create new activity", constants.boxColor, "micro", addActivityAction).getButton();
        InformationPanel.add(addActivityButtonPanel);



        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel(""));



        InformationPanel.add(new JLabel("Project Developers"));

        ArrayList<Developer> developers = project.getDevelopers();
        
        if (developers.size() < 1) {
            InformationPanel.add(new JLabel("No developers yet."));
            InformationPanel.add(new JLabel(""));
        } else {
            for (int i = 0; i < developers.size(); i++) {
                Developer developer = developers.get(i);
                JLabel developerLabel = new JLabel(developer.getInitials());
                developerLabel.setFont(new Font("Arial", Font.BOLD, 15));
                JPanel devePanel = new JPanel(new GridBagLayout());
                devePanel.add(developerLabel);
                devePanel.setBackground(constants.boxColor);
                InformationPanel.add(devePanel);
                InformationPanel.add(new JLabel(""));
            }
        }

        TextField addDeveloperTextField = new TextField("Initials", "initials...", constants.boxColor).getTextField();
        InformationPanel.add(addDeveloperTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction addDeveloperAction = new AsignProjectDeveloperAction("Asign Developer", previousPage, addDeveloperTextField.textField, project, main);
        JPanel addDeveloperButtonPanel = new Button("Asign Developer", constants.boxColor, "micro", addDeveloperAction).getButton();
        InformationPanel.add(addDeveloperButtonPanel);


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 600));

        BoxPanel.add(InformationScrollPanel);

        

        JPanel buttonPanel = new JPanel(new GridLayout(0,2,5,2));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        buttonPanel.setBackground(constants.secondBoxColor);

        AbstractAction submitChangesAction = new SubmitProjectChangersAction("Submit Changes", projectNameTextField.textField, projectLeaderTextField.textField, startYearTextField.textField, startWeekTextField.textField, endYearTextField.textField, endWeekTextField.textField, estimatedWorkHoursTextField.textField, project, previousPage, main);
        JPanel submitChangesButtonPanel = new Button("Submit Changes", constants.secondBoxColor, "small", submitChangesAction).getButton();
        buttonPanel.add(submitChangesButtonPanel);

        AbstractAction getProjectReportAction = new GetProjectReportAction("Generate Report", project, main);
        JPanel getProjectReportButtonPanel = new Button("Generate Report", constants.secondBoxColor, "small", getProjectReportAction).getButton();
        buttonPanel.add(getProjectReportButtonPanel);

        BoxPanel.add(buttonPanel);


        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", previousPage, main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}