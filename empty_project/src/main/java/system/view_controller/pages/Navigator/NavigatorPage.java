package system.view_controller.pages.Navigator;
import javax.swing.*;
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

        AbstractAction manageProjectsAction = new MainMenuAction("Manage Projects", "Manage Projects", main);
        JPanel manageProjectsButtonPanel = new Button("Manage Projects", constants.backgroundColor, "big", manageProjectsAction).getButton();
        BoxPanel.add(manageProjectsButtonPanel);

        AbstractAction timeRegistrationAction = new MainMenuAction("Time Registration", "Time Registration", main);
        JPanel timeRegistrationButtonPanel = new Button("Time Registration", constants.backgroundColor, "big", timeRegistrationAction).getButton();
        BoxPanel.add(timeRegistrationButtonPanel);

        AbstractAction accessCalenderAction = new MainMenuAction("Access Calender", "Access Calender", main);
        JPanel accessCalenderButtonPanel = new Button("Access Calender", constants.backgroundColor, "big", accessCalenderAction).getButton();
        BoxPanel.add(accessCalenderButtonPanel);

        new SubHeader("Logged in as: " + main.app.getCurrentUser().getInitials(), constants.backgroundColor, BoxPanel);

        AbstractAction logOutAction = new LogOutAction("Log Out", main);
        JPanel logOutButtonPanel = new Button("Log Out", constants.backgroundColor, "small", logOutAction).getButton();
        BoxPanel.add(logOutButtonPanel);
        
        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }


}