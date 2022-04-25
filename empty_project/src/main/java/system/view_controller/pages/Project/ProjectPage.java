package system.view_controller.pages.Project;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;

import system.view_controller.widgets.Button;
import system.view_controller.widgets.ProjectButton;
import system.view_controller.widgets.SubHeader;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.AsignProjectDeveloperAction;
import system.view_controller.actions.CreateNewProjectActivityAction;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ProjectButtonAction;
import system.view_controller.actions.SubmitProjectChangersAction;
import system.view_controller.widgets.Header;
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
        BoxPanel.setPreferredSize(new Dimension(400,800));
        BoxPanel.setBorder(new EmptyBorder(25,25,25,25));

        new SubHeader("Project Details:", constants.backgroundColor, BoxPanel);

        JPanel InformationPanel = new JPanel(new GridLayout(0,2,10,5));
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
        TextField endWeekTextField = new TextField("Project endWeek", String.valueOf(project.getEndYear()), constants.boxColor).getTextField();
        InformationPanel.add(endWeekTextField.textField);

        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(250, 300));
        BoxPanel.add(InformationScrollPanel);

        AbstractAction submitChangesAction = new SubmitProjectChangersAction("Submit Changes", projectNameTextField.textField, projectLeaderTextField.textField, startYearTextField.textField, startWeekTextField.textField, endYearTextField.textField, endWeekTextField.textField, project, previousPage, main);
        JPanel submitChangesButtonPanel = new Button("Submit Changes", constants.backgroundColor, "small", submitChangesAction).getButton();
        BoxPanel.add(submitChangesButtonPanel);



        
        new SubHeader("Project Activities:", constants.backgroundColor, BoxPanel);

        JPanel ActivityPanel = new JPanel();
        ActivityPanel.setLayout(new BoxLayout(ActivityPanel, BoxLayout.Y_AXIS));
        ActivityPanel.setBorder(new EmptyBorder(10,10,10,10));
        ActivityPanel.setBackground(constants.boxColor);

        ArrayList<Activity> activities = project.getActivities();
        
        if (activities.size() < 1) {
            new SubHeader("No activities yet.", constants.boxColor, ActivityPanel);
        } else {
            for (int i = 0; i < activities.size(); i++) {
                Activity activity = activities.get(i);
                new SubHeader(activity.getName(), constants.backgroundColor, ActivityPanel);
            }
        }

        new SubHeader("Create New Activity:", constants.boxColor, ActivityPanel);
        TextField addActivityTextField = new TextField("Activity Name", "activity name...", constants.boxColor).getTextField();
        ActivityPanel.add(addActivityTextField.textField);

        AbstractAction addActivityAction = new CreateNewProjectActivityAction("create new activity", previousPage, addActivityTextField.textField, project, main);
        JPanel addActivityButtonPanel = new Button("Asign Developer", constants.boxColor, "small", addActivityAction).getButton();
        ActivityPanel.add(addActivityButtonPanel);

        JScrollPane ActivityScrollPanel = new JScrollPane(ActivityPanel);
        ActivityScrollPanel.setPreferredSize(new Dimension(250, 200));
        BoxPanel.add(ActivityScrollPanel);





        new SubHeader("Project Developers:", constants.backgroundColor, BoxPanel);

        JPanel DeveloperPanel = new JPanel();
        DeveloperPanel.setLayout(new BoxLayout(DeveloperPanel, BoxLayout.Y_AXIS));
        DeveloperPanel.setBorder(new EmptyBorder(10,10,10,10));
        DeveloperPanel.setBackground(constants.boxColor);

        ArrayList<Developer> developers = project.getDevelopers();
        
        if (developers.size() < 1) {
            new SubHeader("No developers yet.", constants.boxColor, DeveloperPanel);
        } else {
            for (int i = 0; i < developers.size(); i++) {
                Developer developer = developers.get(i);
                new SubHeader(developer.getInitials(), constants.backgroundColor, DeveloperPanel);
            }
        }

        new SubHeader("Asign New Developer:", constants.boxColor, DeveloperPanel);
        TextField addDeveloperTextField = new TextField("Project startYear", "initials...", constants.boxColor).getTextField();
        DeveloperPanel.add(addDeveloperTextField.textField);

        AbstractAction addDeveloperAction = new AsignProjectDeveloperAction("Asign Developer", previousPage, addDeveloperTextField.textField, project, main);
        JPanel addDeveloperButtonPanel = new Button("Asign Developer", constants.boxColor, "small", addDeveloperAction).getButton();
        DeveloperPanel.add(addDeveloperButtonPanel);

        JScrollPane DeveloperScrollPanel = new JScrollPane(DeveloperPanel);
        DeveloperScrollPanel.setPreferredSize(new Dimension(250, 200));
        BoxPanel.add(DeveloperScrollPanel);




        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", previousPage, main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}