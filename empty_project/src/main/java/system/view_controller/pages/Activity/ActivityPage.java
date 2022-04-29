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
import system.view_controller.actions.AsignActivityDeveloperAction;
import system.view_controller.actions.AsignProjectDeveloperAction;
import system.view_controller.actions.CreateNewProjectActivityAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ProjectButtonAction;
import system.view_controller.actions.RequestAssistanceAction;
import system.view_controller.actions.SubmitActivityChangesAction;
import system.view_controller.actions.SubmitProjectChangersAction;
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

        InformationPanel.add(new JLabel("Activity Name"));
        TextField activityNameTextField = new TextField("Project Name", activity.getName(), constants.boxColor).getTextField();
        InformationPanel.add(activityNameTextField.textField);

        InformationPanel.add(new JLabel("Parent Project"));
        Project parentProject;
        try {
            parentProject = main.app.getProject(activity.getParentProjectNumber());
            if (parentProject.getName().equals("")) {
                InformationPanel.add(new JLabel("Unnamed (" + parentProject.getProjectNumber() + ")"));
            } else {
                InformationPanel.add(new JLabel(parentProject.getName() + " (" + parentProject.getProjectNumber() + ")"));
            }
        } catch (OperationNotAllowedException error) {
            InformationPanel.add(new JLabel("???"));
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
        }

        InformationPanel.add(new JLabel("Activity Start Year"));
        TextField startYearTextField = new TextField("Activity startYear", String.valueOf(activity.getStartYear()), constants.boxColor).getTextField();
        InformationPanel.add(startYearTextField.textField);
        InformationPanel.add(new JLabel("Activity Start Week"));
        TextField startWeekTextField = new TextField("Activity startWeek", String.valueOf(activity.getStartWeek()), constants.boxColor).getTextField();
        InformationPanel.add(startWeekTextField.textField);

        InformationPanel.add(new JLabel("Activity End Year"));
        TextField endYearTextField = new TextField("Activity endYear", String.valueOf(activity.getEndYear()), constants.boxColor).getTextField();
        InformationPanel.add(endYearTextField.textField);
        InformationPanel.add(new JLabel("Activity End Week"));
        TextField endWeekTextField = new TextField("Activity endWeek", String.valueOf(activity.getEndWeek()), constants.boxColor).getTextField();
        InformationPanel.add(endWeekTextField.textField);


        InformationPanel.add(new JLabel("Estimated Work Hours"));
        TextField estimatedWorkHoursTextField = new TextField("Estimated Work Hours", String.valueOf(activity.getEstimatedWorkHours()), constants.boxColor).getTextField();
        InformationPanel.add(estimatedWorkHoursTextField.textField);


        InformationPanel.add(new JLabel(""));
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
        AbstractAction addDeveloperAction = new AsignActivityDeveloperAction("Asign Developer", previousProject, addDeveloperTextField.textField, activity, main);
        JPanel addDeveloperButtonPanel = new Button("Asign Developer", constants.boxColor, "micro", addDeveloperAction).getButton();
        InformationPanel.add(addDeveloperButtonPanel);


        InformationPanel.add(new JLabel(""));
        InformationPanel.add(new JLabel(""));


        InformationPanel.add(new JLabel("Request Assistance"));
        TextField requestAssistanceTextField = new TextField("Initials", "initials...", constants.boxColor).getTextField();
        InformationPanel.add(requestAssistanceTextField.textField);
        InformationPanel.add(new JLabel(""));
        AbstractAction requestAssistanceAction = new RequestAssistanceAction("Submit Request", previousProject, requestAssistanceTextField.textField, activity, main);
        JPanel requestAssistanceButtonPanel = new Button("Submit Request", constants.boxColor, "micro", requestAssistanceAction).getButton();
        InformationPanel.add(requestAssistanceButtonPanel);
    


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 650));

        BoxPanel.add(InformationScrollPanel);

        
        JPanel submitChangesPanel = new JPanel();
        submitChangesPanel.setBackground(constants.secondBoxColor);
        submitChangesPanel.setBorder(new EmptyBorder(10,0,10,0));
        AbstractAction submitChangesAction = new SubmitActivityChangesAction("Submit Changes", activityNameTextField.textField, startYearTextField.textField, startWeekTextField.textField, endYearTextField.textField, endWeekTextField.textField, estimatedWorkHoursTextField.textField, activity, previousProject, main);
        JPanel submitChangesButtonPanel = new Button("Submit Changes", constants.secondBoxColor, "small", submitChangesAction).getButton();
        submitChangesPanel.add(submitChangesButtonPanel);
        BoxPanel.add(submitChangesPanel);

        JPanel backPanel = new JPanel();
        backPanel.setBackground(constants.backgroundColor);
        AbstractAction backToManageProjectsAction = new ProjectButtonAction(previousProject.getProjectNumber(), "back to ", previousProject, "Project View", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        backPanel.add(backToManageProjectsButtonPanel);
        BoxPanel.add(backPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}