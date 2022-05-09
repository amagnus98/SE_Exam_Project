package system.view_controller.pages.DeveloperOverview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ViewDeveloperOverviewAction;
import system.view_controller.constants.Constants;
import system.view_controller.pages.Main;
import system.view_controller.widgets.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.TextField;

// Choose Developer Overview Date // Responsible - Kasper Petersen (s203294)
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

        InformationPanel.add(new JLabel("Start Week"));
        TextField startWeekTextField = new TextField("Start Week", "", constants.boxColor).getTextField();
        InformationPanel.add(startWeekTextField.textField);

        InformationPanel.add(new JLabel("Start Year"));
        TextField startYearTextField = new TextField("Start Year", "", constants.boxColor).getTextField();
        InformationPanel.add(startYearTextField.textField);

        InformationPanel.add(new JLabel("End Week"));
        TextField endWeekTextField = new TextField("End Week", "", constants.boxColor).getTextField();
        InformationPanel.add(endWeekTextField.textField);

        InformationPanel.add(new JLabel("End Year"));
        TextField endYearTextField = new TextField("End Year", "", constants.boxColor).getTextField();
        InformationPanel.add(endYearTextField.textField);


        JScrollPane InformationScrollPanel = new JScrollPane(InformationPanel);
        InformationScrollPanel.setPreferredSize(new Dimension(550, 500));

        BoxPanel.add(InformationScrollPanel);


        JPanel getOverviewPanel = new JPanel();
        getOverviewPanel.setBackground(constants.secondBoxColor);
        getOverviewPanel.setBorder(new EmptyBorder(20,0,20,0));

        AbstractAction getOverviewAction = new ViewDeveloperOverviewAction(startWeekTextField.textField, startYearTextField.textField, endWeekTextField.textField, endYearTextField.textField, main);
        JPanel registerHoursButtonPanel= new Button("Get Overview", constants.secondBoxColor, "small", getOverviewAction).getButton();
        getOverviewPanel.add(registerHoursButtonPanel);
        BoxPanel.add(getOverviewPanel);

        AbstractAction backToMainAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToMainButtonPanel = new Button("Back", constants.backgroundColor, "small", backToMainAction).getButton();
        backToMainButtonPanel.setBorder(new EmptyBorder(0,0,50,0));
        BoxPanel.add(backToMainButtonPanel);

        
        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);
        
        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
}
