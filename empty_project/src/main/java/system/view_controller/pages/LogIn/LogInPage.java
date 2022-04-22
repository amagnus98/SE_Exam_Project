package system.view_controller.pages.LogIn;
import javax.swing.*;

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

        JPanel BoxPanel = new BoxPanel().getPanel();

        new Header("Log In", BoxPanel);
        new SubHeader("Enter Initials:", constants.backgroundColor, BoxPanel);

        TextField textField = new TextField("Initials", "", constants.backgroundColor).getTextField();
        BoxPanel.add(textField.textFieldPanel);

        AbstractAction action = new LogInAction("Log In", textField.textField, main);
        JPanel ButtonPanel = new Button("Log In", constants.backgroundColor, "small", action).getButton();
        BoxPanel.add(ButtonPanel);

        JPanel container = new Container(BoxPanel).getContainer();
        return container;
    }
}
