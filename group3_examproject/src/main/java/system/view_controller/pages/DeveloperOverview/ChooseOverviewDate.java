package system.view_controller.pages.DeveloperOverview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import system.model.domain.Activity;
import system.model.domain.OperationNotAllowedException;
import system.model.domain.Project;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.RegisterTimeAction;
import system.view_controller.actions.ViewDeveloperOverviewAction;
import system.view_controller.constants.Constants;
import system.view_controller.messageWindows.ErrorWindow;
import system.view_controller.pages.Main;
import system.view_controller.widgets.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.TextField;

public class ChooseOverviewDate {

    JFrame frame;
    Main main;
    Constants constants = new Constants();

    public ChooseOverviewDate(JFrame frame,  Main main) {
        this.frame = frame;
        this.main = main;
        
    }

    public JPanel draw() {

        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(500,700));
        BoxPanel.setBorder(new EmptyBorder(25,25,25,25));


        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(constants.backgroundColor);
        JLabel header = new JLabel("Choose Time of Overview");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBorder(new EmptyBorder(75,0,25,0));
        subHeaderPanel.add(header);
        BoxPanel.add(subHeaderPanel);


        JPanel InformationPanel = new JPanel(new GridLayout(0,2,5,2));
        InformationPanel.setBorder(new EmptyBorder(10,10,10,10));
        InformationPanel.setBackground(constants.boxColor);

        InformationPanel.add(new JLabel("Week"));
        TextField weekTextField = new TextField("Week", "", constants.boxColor).getTextField();
        InformationPanel.add(weekTextField.textField);

        InformationPanel.add(new JLabel("Year"));
        TextField yearTextField = new TextField("Year", "", constants.boxColor).getTextField();
        InformationPanel.add(yearTextField.textField);

        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 300));

        BoxPanel.add(InformationScrollPanel);


        JPanel getOverviewPanel = new JPanel();
        getOverviewPanel.setBackground(constants.secondBoxColor);
        getOverviewPanel.setBorder(new EmptyBorder(20,0,20,0));

        AbstractAction getOverviewAction = new ViewDeveloperOverviewAction(weekTextField.textField, yearTextField.textField, main);
        JPanel registerHoursButtonPanel= new Button("Get Overview", constants.secondBoxColor, "small", getOverviewAction).getButton();
        getOverviewPanel.add(registerHoursButtonPanel);
        BoxPanel.add(getOverviewPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        backToMainButtonPanel.setBorder(new EmptyBorder(0,0,200,0));
        BoxPanel.add(backToMainButtonPanel);
        
        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
}
