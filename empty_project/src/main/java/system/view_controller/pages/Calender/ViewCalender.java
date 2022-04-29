package system.view_controller.pages.Calender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import system.view_controller.widgets.Button;
import system.view_controller.widgets.ProjectButton;
import system.view_controller.widgets.SubHeader;
import system.view_controller.actions.MainMenuAction;
import system.view_controller.actions.ProjectButtonAction;
import system.view_controller.widgets.Header;
import system.view_controller.pages.Main;
import system.view_controller.widgets.BoxPanel;
import system.view_controller.widgets.Container;
import system.view_controller.constants.Constants;


import system.model.domain.Project;
import java.util.*;

public class ViewCalender {

    JFrame frame;
    Main main;
    Constants constants = new Constants();
    
    public ViewCalender(JFrame frame, Main main) {
        this.frame = frame;
        this.main = main;
    }

    public JPanel draw() {
        JPanel BoxPanel = new BoxPanel().getPanel();
        BoxPanel.setPreferredSize(new Dimension(350, 700));

        new Header("Project Overview", BoxPanel);


        AbstractAction backToManageProjectsAction = new MainMenuAction("Back", "Navigator", main);
        JPanel backToManageProjectsButtonPanel = new Button("Back", constants.backgroundColor, "small", backToManageProjectsAction).getButton();
        BoxPanel.add(backToManageProjectsButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }

}
