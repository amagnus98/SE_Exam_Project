package system.view_controller.pages.LogIn;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.ProcessHandle.Info;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Text;

import system.view_controller.constants.Constants;
import system.view_controller.pages.Main;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Header;
import system.view_controller.widgets.SubHeader;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.widgets.TextField;
import system.view_controller.actions.LogInAction;

public class LogInPage {

    Main main;
    Constants constants = new Constants();

    public LogInPage(Main main) {
        this.main = main;
    }

    public JPanel draw() {

        JPanel InformationPanel = new JPanel();
        InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));
        InformationPanel.setBackground(constants.backgroundColor);

        JPanel headerPanel = new JPanel();
        new Header("Log In", headerPanel);
        headerPanel.setBackground(constants.backgroundColor);
        headerPanel.setBorder(new EmptyBorder(100,0,0,0));
        InformationPanel.add(headerPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(new EmptyBorder(30,0,30,0));
        loginPanel.setBackground(constants.boxColor);
        
        new SubHeader("Enter Initials:", constants.boxColor, loginPanel);

        TextField textField = new TextField("Initials", "", constants.boxColor).getTextField();
        loginPanel.add(textField.textFieldPanel);

        AbstractAction action = new LogInAction("Log In", textField.textField, main);
        JPanel ButtonPanel = new Button("Log In", constants.boxColor, "small", action).getButton();
        loginPanel.add(ButtonPanel);
        InformationPanel.add(loginPanel);


        JPanel container = new Container(InformationPanel).getContainer();
        InformationPanel.setAlignmentX(container.CENTER_ALIGNMENT);
        InformationPanel.setAlignmentY(container.CENTER_ALIGNMENT);
        return container;
    }
}
