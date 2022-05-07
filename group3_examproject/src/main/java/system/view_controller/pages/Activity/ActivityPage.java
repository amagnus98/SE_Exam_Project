package system.view_controller.pages.Activity;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;

import system.view_controller.widgets.Button;
import system.view_controller.widgets.ProjectButton;
import system.view_controller.widgets.SubHeader;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.AssignActivityDeveloperAction;
import system.view_controller.actions.AssignProjectDeveloperAction;
import system.view_controller.actions.CreateNewProjectActivityAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ProjectButtonAction;
import system.view_controller.actions.RequestAssistanceAction;
import system.view_controller.actions.SubmitActivityInformationAction;
import system.view_controller.actions.SetEstimatedWorkHoursActivityAction;
import system.view_controller.actions.SetActivityTimeHorizonAction;
import system.view_controller.widgets.Header;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.constants.Constants;
import system.view_controller.messageWindows.ErrorWindow;
import system.model.domain.Activity;
import system.model.domain.Developer;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;

public class ActivityPage {

    JFrame frame;
    Main main;
    Activity activity;
    Project previousProject;
    String previousPreviousPage;
    Constants constants = new Constants();
    
    public ActivityPage(JFrame frame, Main main, Activity activity, Project previousProject) {
        this.frame = frame;
        this.main = main;
        this.activity = activity;
        this.previousProject = previousProject;
    }

    public JPanel draw() {

        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(550,750));
        BoxPanel.setBorder(new EmptyBorder(25,25,25,25));


        JLabel header = new JLabel("Activity Details");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBorder(new EmptyBorder(10,10,10,10));
        BoxPanel.add(header);

        JPanel InformationPanel = new JPanel(new GridLayout(0,2,5,2));
        InformationPanel.setBorder(new EmptyBorder(10,10,10,10));
        InformationPanel.setBackground(constants.boxColor);

        JLabel informationHeader = new JLabel("Activity Information");
        informationHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(informationHeader);
        InformationPanel.add(new JLabel(""));

        InformationPanel.add(new JLabel("Assigned Project"));
        Project parentProject = activity.getParentProject();

        if (parentProject.getName().equals("")) {
            InformationPanel.add(new JLabel("Unnamed (" + parentProject.getProjectNumber() + ")"));
        } else {
            InformationPanel.add(new JLabel(parentProject.getName() + " (" + parentProject.getProjectNumber() + ")"));
        }

        InformationPanel.add(new JLabel("Assigned Project Leader"));
        InformationPanel.add(new JLabel(parentProject.getProjectLeader().getInitials()));

        InformationPanel.add(new JLabel("Activity Name"));
        InformationPanel.add(new JLabel(activity.getName()));

        InformationPanel.add(new JLabel("Edit Activity Name"));
        TextField activityNameTextField = new TextField("Edit activity Name", "", constants.boxColor).getTextField();
        InformationPanel.add(activityNameTextField.textField);

        InformationPanel.add(new JLabel(""));
        AbstractAction submitActivityInformationAction = new SubmitActivityInformationAction("Submit changes", activityNameTextField.textField, activity, previousProject, main);;
        JPanel submitActivityInformationButtonPanel = new Button("Submit changes", constants.boxColor, "small", submitActivityInformationAction).getButton();
        InformationPanel.add(submitActivityInformationButtonPanel);


        JLabel setEstimatedWorkHoursHeader = new JLabel("Hour information");
        setEstimatedWorkHoursHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setEstimatedWorkHoursHeader);

        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel("Total hours registered"));
        InformationPanel.add(new JLabel(String.valueOf(activity.getTotalHoursRegistered())));

        InformationPanel.add(new JLabel("Estimated work hours"));
        InformationPanel.add(new JLabel(String.valueOf(activity.getEstimatedWorkHours())));

        InformationPanel.add(new JLabel("Set estimated work hours"));
        TextField estimatedWorkHoursTextField = new TextField("Estimated Work Hours", "", constants.boxColor).getTextField();
        InformationPanel.add(estimatedWorkHoursTextField.textField);

        InformationPanel.add(new JLabel(""));
        AbstractAction setEstimatedWorkHoursAction = new SetEstimatedWorkHoursActivityAction("Set estimated hours", activityNameTextField.textField, estimatedWorkHoursTextField.textField, activity, previousProject, main);
        JPanel setEstimatedWorkHoursButtonPanel = new Button("Set estimated hours", constants.boxColor, "small", setEstimatedWorkHoursAction).getButton();
        InformationPanel.add(setEstimatedWorkHoursButtonPanel);


        JLabel setTimeHeader = new JLabel("Set activity time horizon");
        setTimeHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setTimeHeader);
        InformationPanel.add(new JLabel(""));
        
        InformationPanel.add(new JLabel("Assigned Project Start Time"));
        if (parentProject.isTimeHorizonDefined()){
            InformationPanel.add(new JLabel("Week: " + parentProject.getStartWeek() + ", Year: " + parentProject.getStartYear()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }
        InformationPanel.add(new JLabel("Assigned Project End Time"));
        if (parentProject.isTimeHorizonDefined()){
            InformationPanel.add(new JLabel("Week: " + parentProject.getEndWeek() + ", Year: " + parentProject.getEndYear()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }

        InformationPanel.add(new JLabel("Activity Start Time"));
        if (activity.isTimeHorizonDefined()){
            InformationPanel.add(new JLabel("Week: " + activity.getStartWeek() + ", Year: " + activity.getStartYear()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }
        InformationPanel.add(new JLabel("Activity End Time"));
        if (activity.isTimeHorizonDefined()){
            InformationPanel.add(new JLabel("Week: " + activity.getEndWeek() + ", Year: " + activity.getEndYear()));
        } else {
            InformationPanel.add(new JLabel("Undefined"));
        }
        
        InformationPanel.add(new JLabel("Activity Start Week"));
        TextField startWeekTextField = new TextField("Activity startWeek", "", constants.boxColor).getTextField();
        InformationPanel.add(startWeekTextField.textField);
        InformationPanel.add(new JLabel("Activity Start Year"));
        TextField startYearTextField = new TextField("Activity startYear", "", constants.boxColor).getTextField();
        InformationPanel.add(startYearTextField.textField);
        
        InformationPanel.add(new JLabel("Activity End Week"));
        TextField endWeekTextField = new TextField("Activity endWeek", "", constants.boxColor).getTextField();
        InformationPanel.add(endWeekTextField.textField);
        InformationPanel.add(new JLabel("Activity End Year"));
        TextField endYearTextField = new TextField("Activity endYear", "", constants.boxColor).getTextField();
        InformationPanel.add(endYearTextField.textField);
        

        InformationPanel.add(new JLabel(""));
        AbstractAction setActivityTimeHorizonAction = new SetActivityTimeHorizonAction("Set time horizon", activityNameTextField.textField, startYearTextField.textField, startWeekTextField.textField, endYearTextField.textField, endWeekTextField.textField, activity, previousProject, main);
        JPanel setActivityTimeHorizonButtonPanel = new Button("Set time horizon", constants.boxColor, "small", setActivityTimeHorizonAction).getButton();
        InformationPanel.add(setActivityTimeHorizonButtonPanel);


        JLabel setDeveloperOverviewHeader = new JLabel("Developer overview");
        setDeveloperOverviewHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setDeveloperOverviewHeader);
        InformationPanel.add(new JLabel(""));

        InformationPanel.add(new JLabel("Activity Developers"));

        ArrayList<Developer> developers = activity.getDevelopers();
        
        if (developers.size() < 1) {
            InformationPanel.add(new JLabel("No developers yet."));
            InformationPanel.add(new JLabel(""));
        } else {
            for (int i = 0; i < developers.size(); i++) {
                Developer developer = developers.get(i);
                JLabel developerLabel = new JLabel(developer.getInitials());
                if (!activity.isDeveloperAssignedByProjectLeader(developer)) {
                    developerLabel = new JLabel(developer.getInitials() + " (requested)");
                }
                developerLabel.setFont(new Font("Arial", Font.BOLD, 15));
                JPanel devePanel = new JPanel(new GridBagLayout());
                devePanel.add(developerLabel);
                devePanel.setBackground(constants.backgroundColor);
                InformationPanel.add(devePanel);
                InformationPanel.add(new JLabel(""));
            }
        }


        TextField addDeveloperTextField = new TextField("Initials", "initials...", constants.boxColor).getTextField();
        InformationPanel.add(addDeveloperTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction addDeveloperAction = new AssignActivityDeveloperAction("Assign developer", previousProject, addDeveloperTextField.textField, activity, main);
        JPanel addDeveloperButtonPanel = new Button("Assign Developer", constants.boxColor, "micro", addDeveloperAction).getButton();
        InformationPanel.add(addDeveloperButtonPanel);


        JLabel setRequestAssistanceHeader = new JLabel("Request assistance");
        setRequestAssistanceHeader.setFont(new Font("Arial", Font.BOLD, 18));
        InformationPanel.add(setRequestAssistanceHeader);
        InformationPanel.add(new JLabel(""));


        InformationPanel.add(new JLabel("Request developer"));
        TextField requestAssistanceTextField = new TextField("Initials", "initials...", constants.boxColor).getTextField();
        InformationPanel.add(requestAssistanceTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction requestAssistanceAction = new RequestAssistanceAction("Submit request", previousProject, requestAssistanceTextField.textField, activity, main);
        JPanel requestAssistanceButtonPanel = new Button("Submit Request", constants.boxColor, "micro", requestAssistanceAction).getButton();
        InformationPanel.add(requestAssistanceButtonPanel);
    


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 650));

        BoxPanel.add(InformationScrollPanel);

        JPanel backPanel = new JPanel();
        backPanel.setBackground(constants.backgroundColor);
        AbstractAction backToManageProjectsAction = new ProjectButtonAction(previousProject.getProjectNumber(), "Back to ", previousProject, "Project View", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        backPanel.add(backToManageProjectsButtonPanel);
        BoxPanel.add(backPanel);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}