package system.view_controller.pages.TimeRegistration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import system.model.domain.Activity;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.RegisterTimeAction;
import system.view_controller.constants.Constants;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import system.view_controller.widgets.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.TextField;


// Register Time Page // Responsible - Mads Ringsted - (s204144)
public class RegisterTimePage {

    JFrame frame;
    Main main;
    String activityName;
    String projectNumber;
    Constants constants = new Constants();

    public RegisterTimePage(JFrame frame, String activityName, String projectNumber,  Main main) {
        this.frame = frame;
        this.main = main;
        this.activityName = activityName;
        this.projectNumber = projectNumber;
    }

    public JPanel draw() {

        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(500,700));
        BoxPanel.setBorder(new EmptyBorder(25,25,25,25));


        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(constants.backgroundColor);
        JLabel header = new JLabel("Register Time on " + this.activityName);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBorder(new EmptyBorder(75,0,25,0));
        subHeaderPanel.add(header);
        BoxPanel.add(subHeaderPanel);


        JPanel InformationPanel = new JPanel(new GridLayout(0,2,5,2));
        InformationPanel.setBorder(new EmptyBorder(10,10,10,10));
        InformationPanel.setBackground(constants.boxColor);


        InformationPanel.add(new JLabel("Activity"));
        InformationPanel.add(new JLabel(this.activityName));


        Project assignedProject;
        try {
            assignedProject = main.app.getProject(projectNumber);

            InformationPanel.add(new JLabel("Assigned Project"));
            if (assignedProject.isNonWorkActivityProject()){
                InformationPanel.add(new JLabel(assignedProject.getName()));
            } else {
                InformationPanel.add(new JLabel(assignedProject.getName() + " (" + assignedProject.getProjectNumber() + ")"));
            }


            Activity activity = assignedProject.getActivity(activityName);
            if (!(assignedProject.isNonWorkActivityProject())) {
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
                
            }
        } catch (OperationNotAllowedException error) {
            ErrorWindow errorWindow = new ErrorWindow(error.getMessage());
            errorWindow.showMessage();
        }





        InformationPanel.add(new JLabel("Number of Hours"));
        TextField numberOfHoursTextField = new TextField("Number of Hours", "", constants.boxColor).getTextField();
        InformationPanel.add(numberOfHoursTextField.textField);

        InformationPanel.add(new JLabel("Day"));
        TextField dayTextField = new TextField("Day", "", constants.boxColor).getTextField();
        InformationPanel.add(dayTextField.textField);

        InformationPanel.add(new JLabel("Week"));
        TextField weekTextField = new TextField("Week", "", constants.boxColor).getTextField();
        InformationPanel.add(weekTextField.textField);
        
        InformationPanel.add(new JLabel("Year"));
        TextField yearTextField = new TextField("Year", "", constants.boxColor).getTextField();
        InformationPanel.add(yearTextField.textField);


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 600));

        BoxPanel.add(InformationScrollPanel);


        JPanel registerHoursPanel = new JPanel();
        registerHoursPanel.setBackground(constants.secondBoxColor);
        registerHoursPanel.setBorder(new EmptyBorder(20,0,20,0));
        AbstractAction registerHoursAction = new RegisterTimeAction(this.projectNumber, this.activityName, numberOfHoursTextField.textField, dayTextField.textField, weekTextField.textField, yearTextField.textField, main);
        JPanel registerHoursButtonPanel= new Button("Register Hours", constants.secondBoxColor, "small", registerHoursAction).getButton();
        registerHoursPanel.add(registerHoursButtonPanel);
        BoxPanel.add(registerHoursPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Time Registration", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        backToMainButtonPanel.setBorder(new EmptyBorder(10,0,10,0));
        BoxPanel.add(backToMainButtonPanel);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);
        
        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
}
