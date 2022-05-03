package system.view_controller.pages.Navigator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import system.view_controller.widgets.Header;
import system.view_controller.widgets.SubHeader;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.Container;

import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.LogOutAction;
import system.view_controller.constants.Constants;

public class NavigatorPage {

    JFrame frame;
    Main main;
    Constants constants = new Constants();
    
    public NavigatorPage(JFrame frame, Main main) {
        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {
        
        JPanel BoxPanel = new BoxPanel().getPanel();

        new Header("Main Menu", BoxPanel);

        AbstractAction manageProjectsAction = new MainMenuAction("Manage Projects", "Project View", main);
        JPanel manageProjectsButtonPanel = new Button("Manage Projects", constants.backgroundColor, "big", manageProjectsAction).getButton();
        BoxPanel.add(manageProjectsButtonPanel);

        AbstractAction developerOverviewAction = new MainMenuAction("Developer Overview", "Developer Overview", main);
        JPanel developerOverviewButtonPanel = new Button("Developer Overview", constants.backgroundColor, "big", developerOverviewAction).getButton();
        BoxPanel.add(developerOverviewButtonPanel);

        AbstractAction timeRegistrationAction = new MainMenuAction("Time Registration", "Time Registration", main);
        JPanel timeRegistrationButtonPanel = new Button("Time Registration", constants.backgroundColor, "big", timeRegistrationAction).getButton();
        BoxPanel.add(timeRegistrationButtonPanel);

        AbstractAction accessCalenderAction = new MainMenuAction("My Calender", "View Calender", main);
        JPanel accessCalenderButtonPanel = new Button("My Calender", constants.backgroundColor, "big", accessCalenderAction).getButton();
        BoxPanel.add(accessCalenderButtonPanel);

        JLabel spacer = new JLabel("");
        spacer.setBorder(new EmptyBorder(20,20,20,20));
        BoxPanel.add(spacer);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);

        AbstractAction logOutAction = new LogOutAction("Log out", main);
        JPanel logOutButtonPanel = new Button("Log out", constants.backgroundColor, "small", logOutAction).getButton();
        BoxPanel.add(logOutButtonPanel);
        
        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }


}