package system.view_controller.pages.Project;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import system.view_controller.widgets.Button;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.ActivityButtonAction;
import system.view_controller.actions.AssignProjectDeveloperAction;
import system.view_controller.actions.CreateNewProjectActivityAction;
import system.view_controller.actions.GetProjectReportAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.SetEstimatedWorkHoursProjectAction;
import system.view_controller.actions.SubmitProjectInformationAction;
import system.view_controller.actions.SetProjectTimeHorizonAction;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.Header;
import system.view_controller.widgets.SubHeader;
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


        JLabel setProjectInformationHeader = new JLabel("Project information");
        setProjectInformationHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setProjectInformationHeader);
        InformationPanel.add(new JLabel(""));

        InformationPanel.add(new JLabel("Project Number"));
        InformationPanel.add(new JLabel(project.getProjectNumber()));

        InformationPanel.add(new JLabel("Project Name"));
        if (project.getName().equals("")) {
            InformationPanel.add(new JLabel("Undefined"));
        } else {
            InformationPanel.add(new JLabel(project.getName()));
        }
        
        InformationPanel.add(new JLabel("Project Leader"));
        if (project.hasProjectLeader()) {
            InformationPanel.add(new JLabel(project.getProjectLeader().getInitials()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }

        InformationPanel.add(new JLabel("Edit Project Name"));
        TextField projectNameTextField = new TextField("Project Name", "", constants.boxColor).getTextField();
        InformationPanel.add(projectNameTextField.textField);

        InformationPanel.add(new JLabel("Assign Project Leader"));
        TextField projectLeaderTextField = new TextField("Project Leader", "", constants.boxColor).getTextField();
        InformationPanel.add(projectLeaderTextField.textField);

        InformationPanel.add(new JLabel(""));
        AbstractAction submitProjectInformationAction = new SubmitProjectInformationAction("Submit changes", projectNameTextField.textField, projectLeaderTextField.textField, project, previousPage, main);
        JPanel submitProjectInformationButtonPanel = new Button("Submit changes", constants.boxColor, "small", submitProjectInformationAction).getButton();
        InformationPanel.add(submitProjectInformationButtonPanel);

        JLabel setEstimatedWorkHoursHeader = new JLabel("Hour information");
        setEstimatedWorkHoursHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setEstimatedWorkHoursHeader);

        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel("Total hours registered"));
        InformationPanel.add(new JLabel(String.valueOf(project.getTotalHoursRegistered())));
        InformationPanel.add(new JLabel("Estimated work hours"));
        InformationPanel.add(new JLabel(String.valueOf(project.getEstimatedWorkHours())));

        InformationPanel.add(new JLabel("Set estimated work hours"));
        TextField estimatedWorkHoursTextField = new TextField("Set Estimated Work Hours", "", constants.boxColor).getTextField();
        InformationPanel.add(estimatedWorkHoursTextField.textField);

        InformationPanel.add(new JLabel(""));
        AbstractAction setEstimatedWorkHoursAction = new SetEstimatedWorkHoursProjectAction("Set estimated hours", projectNameTextField.textField, projectLeaderTextField.textField, estimatedWorkHoursTextField.textField,project, previousPage, main);
        JPanel setEstimatedWorkHoursButtonPanel = new Button("Set estimated hours", constants.boxColor, "small", setEstimatedWorkHoursAction).getButton();
        InformationPanel.add(setEstimatedWorkHoursButtonPanel);

        JLabel setTimeHeader = new JLabel("Set project time horizon");
        setTimeHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setTimeHeader);
        InformationPanel.add(new JLabel(""));

        InformationPanel.add(new JLabel("Project Start Time"));
        if (project.isTimeHorizonDefined()){
            InformationPanel.add(new JLabel("Week: " + project.getStartWeek() + ", Year: " + project.getStartYear()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }
        InformationPanel.add(new JLabel("Project End Time"));
        if (project.isTimeHorizonDefined()){
            InformationPanel.add(new JLabel("Week: " + project.getEndWeek() + ", Year: " + project.getEndYear()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }

        InformationPanel.add(new JLabel("Set Project Start Week"));
        TextField startWeekTextField = new TextField("Project startWeek", "", constants.boxColor).getTextField();
        InformationPanel.add(startWeekTextField.textField);
        InformationPanel.add(new JLabel("Set Project Start Year"));
        TextField startYearTextField = new TextField("Project startYear","", constants.boxColor).getTextField();
        InformationPanel.add(startYearTextField.textField);
        
        InformationPanel.add(new JLabel("Set Project End Week"));
        TextField endWeekTextField = new TextField("Project endWeek", "", constants.boxColor).getTextField();
        InformationPanel.add(endWeekTextField.textField);
        InformationPanel.add(new JLabel("Set Project End Year"));
        TextField endYearTextField = new TextField("Project endYear", "", constants.boxColor).getTextField();
        InformationPanel.add(endYearTextField.textField);
        

        InformationPanel.add(new JLabel(""));
        AbstractAction setProjectTimeHorizonAction = new SetProjectTimeHorizonAction("Set time horizon", projectNameTextField.textField, projectLeaderTextField.textField, startYearTextField.textField, startWeekTextField.textField, endYearTextField.textField, endWeekTextField.textField, estimatedWorkHoursTextField.textField, project, previousPage, main);
        JPanel setProjectTimeHorizonButtonPanel = new Button("Set time horizon", constants.boxColor, "small", setProjectTimeHorizonAction).getButton();
        InformationPanel.add(setProjectTimeHorizonButtonPanel);



        JLabel setActivityOverviewHeader = new JLabel("Activity overview");
        setActivityOverviewHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setActivityOverviewHeader);
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
                if (i == 0){
                    JLabel activityInfoText = new JLabel("Press on an activity to edit it");
                    activityInfoText.setFont(new Font("Arial", Font.PLAIN, 12));
                    InformationPanel.add(activityInfoText);
                } else {
                    InformationPanel.add(new JLabel(""));
                }
            }
        }

        TextField addActivityTextField = new TextField("Activity Name", "activity name...", constants.boxColor).getTextField();
        InformationPanel.add(addActivityTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction addActivityAction = new CreateNewProjectActivityAction("Create new activity", previousPage, addActivityTextField.textField, project, main);
        JPanel addActivityButtonPanel = new Button("Create new activity", constants.boxColor, "micro", addActivityAction).getButton();
        InformationPanel.add(addActivityButtonPanel);


        JLabel setDeveloperOverviewHeader = new JLabel("Developer overview");
        setDeveloperOverviewHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setDeveloperOverviewHeader);
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
        AbstractAction addDeveloperAction = new AssignProjectDeveloperAction("Assign developer", previousPage, addDeveloperTextField.textField, project, main);
        JPanel addDeveloperButtonPanel = new Button("Assign developer", constants.boxColor, "micro", addDeveloperAction).getButton();
        InformationPanel.add(addDeveloperButtonPanel);


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 600));

        BoxPanel.add(InformationScrollPanel);

        

        JPanel buttonPanel = new JPanel(new GridLayout(0,1,5,2));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        buttonPanel.setBackground(constants.secondBoxColor);

        AbstractAction getProjectReportAction = new GetProjectReportAction("Generate report", project, main);
        JPanel getProjectReportButtonPanel = new Button("Generate report", constants.secondBoxColor, "small", getProjectReportAction).getButton();
        getProjectReportButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        buttonPanel.add(getProjectReportButtonPanel);

        BoxPanel.add(buttonPanel);

        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", "Project View", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        backToManageProjectsButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        BoxPanel.add(backToManageProjectsButtonPanel);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}